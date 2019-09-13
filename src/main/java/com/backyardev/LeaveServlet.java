package com.backyardev;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backyardev.util.LeaveReqObject;
import com.backyardev.util.LeaveRequestService;

public class LeaveServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		
		String returnString = "null";
		String name = req.getParameter("name");
		String ecode = req.getParameter("ecode");
		String projectName = req.getParameter("project");
		String teamLead = req.getParameter("tLead");
		String projectManager = req.getParameter("manager");
		String startDate = req.getParameter("leave-start");
		String endDate = req.getParameter("leave-end");
		String leaveType = req.getParameter("leave-type");
		String dayLeave = req.getParameter("day-leave");
		String leaveDesc = req.getParameter("leave-desc");
		int numberOfDays = Integer.parseInt(req.getParameter("number-days"));
		PrintWriter out = res.getWriter();		

		if(name == null || ecode == null || projectName == null || req.getParameter("number-days") == null || leaveDesc == null || leaveType.equals("null") || teamLead == null || projectManager == null || endDate.equals("yy-mm-dd") || startDate.equals("yy-mm-dd") || dayLeave == null) {
			out.write(returnString);
		} else {
			LeaveReqObject obj = new LeaveReqObject();
			obj.setEcode(ecode);
			obj.setName(name);
			obj.setTeamLead(teamLead);
			obj.setProjectManager(projectManager);
			obj.setProjectName(projectName);
			obj.setStartDate(startDate);
			obj.setEndDate(endDate);
			obj.setNumberOfDays(numberOfDays);
			obj.setLeaveType(leaveType);
			obj.setLeaveDesc(leaveDesc);
			if (dayLeave.equals("full-day")) {
				obj.setFullDayLeave(1);
			} else {
				obj.setHalfDayLeave(1);
			}
			returnString = LeaveRequestService.leaveService(obj);
			out.write(returnString);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		try{
			if(session.getAttribute("ecode") != null) {
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/LeaveForm.jsp");
				rd.forward(req, resp);
			} else {
				resp.sendRedirect("/LeaveRequest");
			}
		} catch(NullPointerException ex) {
			resp.sendRedirect("/LeaveRequest");
		}
	}
}