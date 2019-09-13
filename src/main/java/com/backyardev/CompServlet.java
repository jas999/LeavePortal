package com.backyardev;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CompServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		try{
			if(session.getAttribute("ecode") != null) {
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/comp.jsp");
				rd.forward(req, resp);
			} else {
				resp.sendRedirect("/LeaveRequest");
			}
		} catch(NullPointerException ex) {
			resp.sendRedirect("/LeaveRequest");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
