package com.team.maker.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet 
{
	private Gson gson = new Gson();

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        
        
//        String thisURL = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
        	resp.getWriter().println(gson.toJson(userService.getCurrentUser()));
//        	resp.sendRedirect("/");
//            resp.getWriter().println("<p>Hello, " +
//                                     req.getUserPrincipal().getName() +
//                                     "!  You can <a href=\"" +
//                                     userService.createLogoutURL(thisURL) +
//                                     "\">sign out</a>.</p>");
        } else {
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
    }
}
