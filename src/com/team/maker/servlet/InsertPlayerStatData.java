package com.team.maker.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SerializationUtils;

import com.google.gson.Gson;
import com.team.maker.data.PMF;
import com.team.maker.model.PlayerStats;

@SuppressWarnings("serial")
public class InsertPlayerStatData extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		List<PlayerStats> playerStats = Collections.emptyList();
		if ("playerStatsUpload".equals(req.getParameter("key"))) {
			String file = "/WEB-INF/playerStats.ser";
			InputStream in = getServletContext().getResourceAsStream(file);
			playerStats = SerializationUtils.deserialize(in);
			Gson gson = new Gson();
			if (playerStats != null) {
		
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					for (PlayerStats playerStats2 : playerStats) {
						System.out.println(gson.toJson(playerStats2));
						pm.makePersistent(playerStats2);
	
					}
				} finally {
					pm.close();
				}
	
			}
		}

		resp.setContentType("text/plain");
		if (playerStats != null) {
			resp.getWriter().println(
					"added " + playerStats.size() + " players with stats");
		}
	}

}
