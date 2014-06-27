package com.team.maker.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.datanucleus.util.StringUtils;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class PlayerUploadServlet extends HttpServlet
{
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/**
	 * Admin page to upload a CSV of players
	 * @throws ServletException 
	 */
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/upload.html");
		dispatcher.forward(req, resp);
    }
	
	/**
	 * Admin page to upload a CSV of players
	 * @throws ServletException 
	 */
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			upload.setSizeMax(50000);
			
			FileItemIterator iter = upload.getItemIterator(req);
			
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				InputStream in = item.openStream();
				Scanner scanner = new Scanner(in);
				
				try {
	                scanner.nextLine(); // skip the first line
	                while (scanner.hasNextLine()) {
	                	String line = scanner.nextLine();
	                	String[] lineArray = line.split(",");
	                	
	                	Entity player = new Entity("Player");
	                	player.setProperty("id", lineArray[0]);
	                	player.setProperty("name", lineArray[1]);
	                	player.setProperty("yearStart", Integer.valueOf(lineArray[2]));
	                	player.setProperty("yearEnd", Integer.valueOf(lineArray[3]));
	                	player.setProperty("position", lineArray[4]);
	                	player.setProperty("height", lineArray[5]);
	                	player.setProperty("weight", StringUtils.isEmpty(lineArray[6]) ? 0 : Integer.valueOf(lineArray[6]));
	                	player.setProperty("birthdate", lineArray[7]);
	                	if (lineArray.length > 8) {
	                		player.setProperty("college", lineArray[8]);
	                	}
	                	
	                	datastore.put(player);
	                }
                } catch (Exception e) {
                	throw new ServletException("Cannot parse multipart request.", e);
                } finally {
                	scanner.close();
                	in.close();
                }
			}
	    } catch (Exception e) {
	        throw new ServletException("Cannot parse multipart request.", e);
	    }
		
		resp.setContentType("text/html");
		resp.getWriter().println("<html><body>Success!</body></html>");
    }
}
