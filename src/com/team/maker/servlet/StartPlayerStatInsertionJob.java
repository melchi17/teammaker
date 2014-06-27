package com.team.maker.servlet;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

@SuppressWarnings("serial")
public class StartPlayerStatInsertionJob  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(withUrl("/admin/worker").param("key", "playerStatsUpload"));
		
		resp.sendRedirect("/");
	}

}

