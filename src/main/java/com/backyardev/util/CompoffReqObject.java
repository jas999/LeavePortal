package com.backyardev.util;

public class CompoffReqObject {

	String ecode, name, project, teamLead, manager, compDate, ticket, desc, status;
	int id;
	String nightShift = "0";
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTeamLead() {
		return teamLead;
	}
	public void setTeamLead(String teamLead) {
		this.teamLead = teamLead;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getCompDate() {
		return compDate;
	}
	public void setCompDate(String compDate) {
		this.compDate = compDate;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNightShift() {
		String nightShiftStatus;
		if(nightShift.equals("1")) {
			nightShiftStatus = "Night Shift";
		}else {
			nightShiftStatus = "No";
		}
		return nightShiftStatus;
	}
	public void setNightShift(String nightShift) {
		this.nightShift = nightShift;
	}
}
