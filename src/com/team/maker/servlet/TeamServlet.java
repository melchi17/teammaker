package com.team.maker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.IoUtil;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.team.maker.model.NBATeam;

@SuppressWarnings("serial")
public class TeamServlet extends HttpServlet
{
	private UserService userService = UserServiceFactory.getUserService();
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private Gson gson = new Gson();
	
	/**
	 * Creates a team
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		NBATeam team = gson.fromJson(IoUtil.readStream(request.getInputStream()), NBATeam.class);
		
		Entity teamEntity = new Entity("Team");
		teamEntity.setProperty("name", team.getName());
		teamEntity.setProperty("pg", team.getPg());
		teamEntity.setProperty("sg", team.getSg());
		teamEntity.setProperty("sf", team.getSf());
		teamEntity.setProperty("pf", team.getPf());
		teamEntity.setProperty("c", team.getC());
		teamEntity.setProperty("hc", team.getHc());
		
		if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
			teamEntity.setProperty("owner", userService.getCurrentUser().getEmail());
		}
		
		Transaction tx = datastore.beginTransaction();
		try {
			datastore.put(teamEntity);
			tx.commit();
		}
		catch (Exception ex) {
			tx.rollback();
			throw new ServletException(ex);
		}
		
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(teamEntity));
	}
	
	/**
	 * Gets a team
	 */
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uri = req.getRequestURI();
		
		try {
			Long id = Long.valueOf(uri.substring(10)); // substring "/api/team/"
			Key key = KeyFactory.createKey("Team", id);
			
			try {
				Entity team = datastore.get(key);
				resp.setContentType("application/json");
				resp.getWriter().println(gson.toJson(team));
			} catch (EntityNotFoundException e) {
				resp.sendError(400);
			}
		} catch (Exception e) {
			resp.sendError(404);
		}
    }
}
