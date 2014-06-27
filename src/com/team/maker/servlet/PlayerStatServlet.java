package com.team.maker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team.maker.data.PlayerStatRepo;
import com.team.maker.model.PlayerStats;

public class PlayerStatServlet  extends HttpServlet
{
	
	private final PlayerStatRepo playerStatRepo = new PlayerStatRepo();
	private final Gson gson = new Gson();
	
	/**
	 * Redirects to index.html for the permalink viewing
	 * @throws ServletException 
	 */
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
		String playerIdParam = req.getParameter("playerId");
		
		PlayerStats playerStats = null;
		try {
			Integer playerId = Integer.parseInt(playerIdParam);
			playerStats = playerStatRepo.getPlayerStats(playerId);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(400);
		}
		
		
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(playerStats));
    }
}