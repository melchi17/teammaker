package com.team.maker.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UserTeamServlet extends HttpServlet {

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	UserService userService = UserServiceFactory.getUserService();
	private Gson gson = new Gson();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = userService.getCurrentUser();
		if (user != null) {
			Query q = new Query("Team").setFilter(new FilterPredicate("owner", FilterOperator.EQUAL, user.getEmail()));
			PreparedQuery pq = datastore.prepare(q);
			resp.setContentType("application/json");
			for (Entity team : pq.asIterable()) {
				resp.getWriter().println(gson.toJson(team));
			}
		} else {
			resp.sendError(400);
		}
	}
}
