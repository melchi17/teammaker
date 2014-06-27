package com.team.maker.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class ImageUploadServlet extends HttpServlet
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	/**
	 * {@inheritDoc}
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/image.html");
		dispatcher.forward(req, resp);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			upload.setSizeMax(50000);
			
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				try (InputStream in = item.openStream()) {
					// read in the file
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					IOUtils.copy(in, out);
					
					// persist the image
                	Entity img = new Entity("PlayerImage");
                	img.setProperty("img", new Blob(out.toByteArray()));
                	datastore.put(img);
				}
				catch (Exception ex) {
	            	throw new ServletException("Unable to read multipart request.", ex);
	            }
			}
	    }
		catch (ServletException ex) {
			throw ex;
		}
		catch (Exception ex) {
	        throw new ServletException("Unable to read multipart request.", ex);
	    }
		
		response.setContentType("text/html");
		response.getWriter().println("<html><body>Success!</body></html>");
	}
	
}
