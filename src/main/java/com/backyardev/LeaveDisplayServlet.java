package com.backyardev;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backyardev.util.DatabaseConnection;
import com.backyardev.util.DatabaseQueries;

public class LeaveDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		try{
			if(session.getAttribute("ecode") != null) {
				
			
				
	
				
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/leaveDisplay.jsp");
				rd.forward(req, resp);
			} else {
				resp.sendRedirect("/LeaveRequest");
			}
		} catch(NullPointerException ex) {
			resp.sendRedirect("/LeaveRequest");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
