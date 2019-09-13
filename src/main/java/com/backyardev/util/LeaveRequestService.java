package com.backyardev.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LeaveRequestService {

	public static String loginService(HttpServletRequest req,String email, String pass) {
		
		String ecode, project, lead, manager, name, desg, hash, returnString = null;
		
		if(!validateMail(email)) {
			returnString = "invalid_mail";
		} else {
			
			ResultSet rs = DatabaseQueries.getAuth(email);
			
			try {
				
				if(!rs.next()) {
					returnString = "invalid_mail";
				} else {
					ecode = rs.getString("ecode");
					desg = rs.getString("designation");
					hash = rs.getString("pass_hash");
					name = rs.getString("name");
					project = rs.getString("project");
					lead = rs.getString("team_lead");
					manager = rs.getString("project_manager");
					if (Password.check(pass, hash)) {
						HttpSession session = req.getSession(true);
						session.setAttribute("ecode", ecode);
						session.setAttribute("email", email);
						session.setAttribute("desg", desg);
						session.setAttribute("name", name);
						session.setAttribute("project", project);
						session.setAttribute("manager", manager);
						session.setAttribute("lead", lead);
						
						returnString = "true";
					} else {
						returnString = "wrong_pass";
					}
				}
			} catch(Exception ex) {
				returnString = "500";
				ex.printStackTrace();
			}
		}
		DatabaseQueries.closeConnection();
		return returnString;
	}
	
	public static String leaveService(LeaveReqObject obj) {
		String returnString = null;
		try {
			if(DatabaseQueries.insertLeaveRequest(obj)) {
				if (LeaveMail.prepareMail(obj)){
					returnString = "true";
				} else {
					returnString = "mail_not_sent";
				}
			} else {
				returnString = "insertion_error";
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		DatabaseQueries.closeConnection();
		return returnString;
	}
	
	public static String compOffService(CompoffReqObject obj) {
		String returnString = null;
		try {
			if(DatabaseQueries.insertCompoffRequest(obj)) {
				returnString = "true";	
			} else {
				returnString = "insertion_error";
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		DatabaseQueries.closeConnection();
		return returnString;
	}

	public static ArrayList<LeaveReqObject> populateLeaveTable(String desg, String tl_name, String ecode){
		
		ArrayList<LeaveReqObject> al = new ArrayList<>();
		ResultSet rs = DatabaseQueries.getLeaveTable(desg, tl_name, ecode);
		
		try {
			while (rs.next()) {
				LeaveReqObject obj = new LeaveReqObject();
				if(rs.getInt("status")== 0) {
					obj.setStatus("Pending");
				} else if(rs.getInt("status")== 1) {
					obj.setStatus("Approved");
				} else {
					obj.setStatus("Rejected");
				}
				
				if(rs.getInt("half_day_leave") == 1) {
					obj.setHalfDayLeave(1);
				} else if(rs.getInt("full_day_leave") == 1) {
					obj.setFullDayLeave(1);
				} 
				
				obj.setId(rs.getInt("id"));
				obj.setEcode(rs.getString("ecode"));
				obj.setName(rs.getString("name"));
				obj.setTeamLead(rs.getString("team_lead"));
				obj.setProjectManager(rs.getString("project_manager"));
				obj.setProjectName(rs.getString("project"));
				obj.setStartDate(rs.getString("leave_start_date"));
				obj.setEndDate(rs.getString("leave_end_date"));
				obj.setNumberOfDays(rs.getInt("number_of_days"));
				obj.setLeaveType(rs.getString("leave_type"));
				obj.setLeaveDesc(rs.getString("leave_desc"));
				al.add(obj);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		DatabaseQueries.closeConnection();
		return al;
	}

	public static ArrayList<CompoffReqObject> populateCompoffTable(String desg, String tl_name, String ecode){
		
		ArrayList<CompoffReqObject> al = new ArrayList<>();
		ResultSet rs = DatabaseQueries.getCompoffTable(desg, tl_name, ecode);
		try {
			while(rs.next()) {
				CompoffReqObject obj = new CompoffReqObject();
				if(rs.getInt("status")== 0) {
					obj.setStatus("Pending");
				} else if(rs.getInt("status")== 1) {
					obj.setStatus("Approved");
				} else {
					obj.setStatus("Rejected");
				}
				
				obj.setId(rs.getInt("id"));
				obj.setEcode(rs.getString("ecode"));
				obj.setName(rs.getString("name"));
				obj.setTeamLead(rs.getString("team_lead"));
				obj.setCompDate(rs.getString("comp_date"));
				obj.setDesc(rs.getString("description"));
				obj.setProject(rs.getString("project"));
				obj.setManager(rs.getString("project_manager"));
				obj.setNightShift(rs.getString("night_shift"));
				obj.setTicket(rs.getString("ticket_scr"));
				al.add(obj);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		DatabaseQueries.closeConnection();
		return al;
	}
	
	private static boolean validateMail(String mail){
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
    			"[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if(mail == null) {
		return false;
		}
		if (pat.matcher(mail).matches()) {
			if(mail.indexOf("@trantorinc.com", mail.length() - "@trantorinc.com".length()) != -1){
	            return true;
	        } else {
	        	return false;
	        }
		} else {
			return false;
		}
	}
	
}