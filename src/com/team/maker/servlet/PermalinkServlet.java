package com.team.maker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PermalinkServlet extends HttpServlet
{
	/**
	 * Redirects to index.html for the permalink viewing
	 * @throws ServletException 
	 */
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String uri = req.getRequestURI();
		
		Long id = null;
		try {
			id = Long.valueOf(uri.substring(6)); // substring "/team/"
		} catch (Exception e) {
			resp.sendError(400);
		}
		resp.sendRedirect("/?id=" + id);
    }
}
