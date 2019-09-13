package com.backyardev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backyardev.util.LeaveRequestService;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		
		String returnString = LeaveRequestService.loginService(req, email, pass);
		PrintWriter out = resp.getWriter();
		
		out.write(returnString);
	}
}
