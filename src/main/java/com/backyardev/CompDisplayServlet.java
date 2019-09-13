package com.backyardev;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backyardev.util.DatabaseQueries;

/**
 * Servlet implementation class CompDisplayServlet
 */
public class CompDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		try{
			if(session.getAttribute("ecode") != null) {
				
				String url = req.getRequestURI().replace("/LeaveRequest/comp/", "");
				String  newUrl  =  url.replace("static/loading.gif", "");
				DatabaseQueries databaseQuery = new DatabaseQueries();
				databaseQuery.getCompLeave(newUrl);
			
				
				
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/compDisplay.jsp");
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
