package com.backyardev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backyardev.util.CompoffReqObject;
import com.backyardev.util.LeaveRequestService;

public class CompOffServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String returnString = "null";
		String name = req.getParameter("name");
		String ecode = req.getParameter("ecode");
		String project = req.getParameter("project");
		String manager = req.getParameter("manager");
		String teamLead = req.getParameter("tLead");
		String comp_date = (req.getParameter("comp-date")).replace('/', '-');
		String desc = req.getParameter("comp-desc");
		String ticket = req.getParameter("ticket");
		String avail = req.getParameter("avail");
		String night = "0";
		
		if(comp_date.equals("yy-mm-dd") || desc == null || ticket == null || avail == null || project == null || teamLead == null || manager == null) {
			out.write(returnString);
		} else {
			if(avail.equals("avail")) {
				night = "1";
			}
			
			CompoffReqObject obj = new CompoffReqObject();
			
			obj.setEcode(ecode);
			obj.setName(name);
			obj.setProject(project);
			obj.setTeamLead(teamLead);
			obj.setManager(manager);
			obj.setCompDate(comp_date);
			obj.setDesc(desc);
			obj.setTicket(ticket);
			obj.setNightShift(night);
			
			returnString = LeaveRequestService.compOffService(obj);
			
			out.write(returnString);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		try{
			if(session.getAttribute("ecode") != null) {
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/compOff.jsp");
				rd.forward(req, resp);
			} else {
				resp.sendRedirect("/LeaveRequest");
			}
		} catch(NullPointerException ex) {
			resp.sendRedirect("/LeaveRequest");
		}
	}

}
