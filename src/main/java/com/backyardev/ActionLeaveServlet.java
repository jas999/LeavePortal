package com.backyardev;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backyardev.util.DatabaseConnection;

/**
 * Servlet implementation class ActionLeaveServlet
 */
@WebServlet("/ActionLeaveServlet")
public class ActionLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("action");
		int status = 0;
		Connection conn;
		String sql;
		PreparedStatement pst;
		
		if(action.equals("approve")) {
			status = 1;
		}else if(action.equals("reject")){
			status = -1;
		}
		
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			conn = db.getConnection();	
			sql = "UPDATE LEAVE_STATUS SET STATUS = "+ status + " WHERE id =  " + id ;
			pst = conn.prepareStatement(sql);
			pst.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write("true");
	}

}
