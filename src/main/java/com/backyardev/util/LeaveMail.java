package com.backyardev.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class LeaveMail {
	
	int user_id,days;
	String f_name,eCode,project,teamLead, proManager;
	String startDate,endDate;
	String name,description,leaveType = "";
	String address;
	Character sl = ' ',cl = ' ',el = ' ',ml = ' ',wh = ' ',bm = ' ',fdl = ' ',hdl = ' ';
	
	public static boolean prepareMail(LeaveReqObject obj) throws FileNotFoundException, IOException
	{
		boolean returnBool = false;
		try 
		{
			LeaveMail details = new LeaveMail();
			details.name = obj.getName();
			details.eCode = obj.getEcode();
			details.project = obj.getProjectName();
			details.teamLead = obj.getTeamLead();
			details.startDate = obj.getStartDate();
			details.endDate = obj.getEndDate();
			details.days = obj.getNumberOfDays();
			details.proManager = obj.getProjectManager();
		
			if(obj.getHalfDayLeave() == 1) {
				details.hdl = 'X';
			}
			if(obj.getFullDayLeave() == 1) {
				details.fdl = 'X';
			}
			
			String choice;
			choice = obj.getLeaveType();
			switch (choice) {
			case "Sick Leave":
				details.sl = 'X';
				details.leaveType = "Sick Leave";
				break;
			case "Casual Leave":
				details.cl = 'X';
				details.leaveType = "Casual Leave";
				break;
			case "Earned Leave":
				details.el = 'X';
				details.leaveType = "Earned Leave";
				break;
			case "Maternity Leave":
				details.ml = 'X';
				details.leaveType = "Maternity Leave";
				break;
			case "On Office Duty":
				details.wh = 'X';
				details.leaveType = "On Office Duty";
				break;
			case "Biometric Miss Out":
				details.bm = 'X';
				details.leaveType = "Biometric Miss Out";
				break;
			default:
				//Do nothing 
				
			}
			
			details.description = obj.getLeaveDesc();
			
			returnBool = sendMail(details);
		        
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return returnBool;
    }
	
	public static boolean sendMail(LeaveMail details)  throws FileNotFoundException, IOException{
		
		boolean returnBool = false;
		Properties prop = new Properties();
		
		prop.load(new FileInputStream("/home/mayanksinha/leave-servlet/servlet.properties"));
		String EMAIL_U = prop.getProperty("EMAIL_U");
		String EMAIL_P = prop.getProperty("EMAIL_P");
		
		
		// Recipient's email ID needs to be mentioned.
		   
		String to = "mayank.sinha02@gmail.com";
		
		// Sender's email ID needs to be mentioned
		String from = "mayank.sinha02@gmail.com";
		final String username = EMAIL_U;//change accordingly
		final String password = EMAIL_P;//change accordingly
		
		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		
		// Get the Session object.
		Session session = Session.getInstance(props,
		   new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(username, password);
		      }
		});

		try {
		     // Create a default MimeMessage object.
		     Message message = new MimeMessage(session);
		
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.CC,
			         InternetAddress.parse(to));
			
			// Set Subject: header field
			  
			message.setSubject("Leave Approval- "+ details.eCode +" - "+details.name + " - "+ details.startDate + " - "+ details.endDate + " - " + details.leaveType);
			   
			// Send the actual HTML message, as big as you like
			message.setContent( "<div>"
				   
				  +" <table border=1 style=\"width:80%;text-align:left;\">"
					 +"  <tr><td style = \"text-align: center; color:black;\"><b>LEAVE APPLICATION FORM</b></td><td>   </td></tr>"
					 +" <tr><th style = \"color: black;\">E-Code</th><td style = \"color: black;\">"+details.eCode+"</td></tr>"
					 +"  <tr><th style = \"color: black;\">Name</th><td style = \"color: black;\">"+details.name+"</td></tr>"
					 +"  <tr><th style = \"color: black;\">Project</th><td style = \"color: black;\">"+details.project+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Team/Project Lead Name</th><td style =\"color: black;\">"+details.teamLead+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Team/Project Manager Name</th><td style =\"color: black;\">"+details.proManager+"</td></tr>"
					 +" <tr style =\"background-color:black;\"><td style = \"color: black;\">Blank Line</td><td>   </td></tr>"
					 +" <tr><th style = \"color: black;\">Leave Start Date</th><td style = \"color: black;\">"+details.startDate+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Leave End Date</th><td style = \"color: black;\">"+details.endDate+"</td></tr>"
					 +" <tr><th style = \"color: black;\">No.of Working Days </th><td style = \"color: black;\">"+details.days+"</td></tr>"
					 +" <tr style = \"background-color:black;\"><td style = \"color: black;\">Blank Line</td><td>   </td></tr>"
					 +" <tr><th style = \"color: black;\">Leave Type (Mark X against applicable one) </th><td>   </td></tr>"
					 +" <tr><th style = \"color: black;\">Sick Leave</th><td style = \"color: black;\">"+details.sl+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Casual Leave</th><td style = \"color: black;\">"+details.cl+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Earned Leave</th><td style = \"color: black;\">"+details.el+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Maternity Leave</th><td style = \"color: black;\">"+details.ml+"</td></tr>"
					 +" <tr><th style = \"color: black;\">On Office Duty/Tour/client site/Work From Home</th><td style = \"color: black;\">"+details.wh+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Biometric Miss out</th><td style = \"color: black;\">"+details.bm+"</td></tr>"
					 +" <tr style = \"background-color:black;\"><td style = \"color: black;\">Blank Line</td><td>   </td></tr>"
					 +" <tr><th style = \"color: black;\">Mark X against applicable one</th><td>   </td></tr>"
					 +" <tr><th style = \"color: black;\">Full Day Leave</th><td style = \"color: black;\">"+details.fdl+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Half Day Leave</th><td style = \"color: black;\">"+details.hdl+"</td></tr>"
					 +" <tr><th style = \"color: black;\">Description</th><td style = \"color: black;\">"+details.description+"</td></tr>"
					 +"</table>	"
				  
				  	
				   +"</div>	",
		       "text/html");
		
			// Send message
			Transport.send(message);
			returnBool = true;
		
		} catch (MessagingException e) {
			returnBool = false;
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return returnBool;
	}
}