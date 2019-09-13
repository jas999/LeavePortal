package com.backyardev;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		HttpSession session = req.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		res.sendRedirect("/LeaveRequest");
	}
}
