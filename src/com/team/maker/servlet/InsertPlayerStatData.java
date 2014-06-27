package com.team.maker.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.SerializationUtils;

import com.google.gson.Gson;
import com.team.maker.data.PMF;
import com.team.maker.model.PlayerStats;

public class InsertPlayerStatData extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// From the example:
		// http://stackoverflow.com/questions/1513603/how-to-upload-and-store-an-image-with-google-app-engine-java
		List<PlayerStats> playerStats = Collections.emptyList();
		// try {
		// FileItemIterator iter = upload.getItemIterator(req);
		//
		// // Parse the request
		// while (iter.hasNext()) {
		// FileItemStream item = iter.next();
		// String name = item.getFieldName();
		// InputStream stream = item.openStream();
		// if (item.isFormField()) {
		// System.out.println("Form field " + name + " with value "
		// + Streams.asString(stream) + " detected.");
		// } else {
		// // Image here.
		// System.out.println("File field " + name
		// + " with file name " + item.getName()
		// + " detected.");
		// // Process the input stream
		// playerStats = SerializationUtils.deserialize(stream);
		// }
		// }
		//
		// } catch (FileUploadException e) {
		// e.printStackTrace();
		// }

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

		resp.setContentType("text/plain");
		if (playerStats != null) {
			resp.getWriter().println(
					"added " + playerStats.size() + " players with stats");
		}
	}

}
