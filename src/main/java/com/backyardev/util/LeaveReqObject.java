package com.backyardev.util;

public class LeaveReqObject {

private String name;
private String ecode;
private String projectName;
private String teamLead;
private String projectManager;
private String startDate;
private String endDate;
private String leaveType;
private String leaveDesc; 
private String status;
private int id;
private int numberOfDays;
private int halfDayLeave=0; 
private int fullDayLeave=0;

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public int getHalfDayLeave() {
return halfDayLeave;
}

public void setHalfDayLeave(int halfDayLeave) {
this.halfDayLeave = halfDayLeave;
}

public int getFullDayLeave() {
return fullDayLeave;
}

public void setFullDayLeave(int fullDayLeave) {
this.fullDayLeave = fullDayLeave;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEcode() {
return ecode;
}

public void setEcode(String ecode) {
this.ecode = ecode;
}

public String getProjectName() {
return projectName;
}

public void setProjectName(String projectName) {
this.projectName = projectName;
}

public String getTeamLead() {
return teamLead;
}

public void setTeamLead(String teamLead) {
this.teamLead = teamLead;
}

public String getProjectManager() {
return projectManager;
}

public void setProjectManager(String projectManager) {
this.projectManager = projectManager;
}

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public int getNumberOfDays() {
return numberOfDays;
}

public void setNumberOfDays(int numberOfDays) {
this.numberOfDays = numberOfDays;
}

public String getLeaveType() {
return leaveType;
}

public void setLeaveType(String leaveType) {
this.leaveType = leaveType;
}

public String getLeaveDesc() {
return leaveDesc;
}

public void setLeaveDesc(String leaveDesc) {
this.leaveDesc = leaveDesc;
}

public String getDayLeave() {
	int   dayLeave = fullDayLeave;
	String stringDayLeave = String.valueOf(fullDayLeave);
	if(stringDayLeave.equals("1")){
		stringDayLeave = "Full Day";
	}
	else if(stringDayLeave.equals("0")){
		stringDayLeave = "Half Day";
	}	
return stringDayLeave;
}

public void setDayLeave(int fullDayLeave) {
this.fullDayLeave = fullDayLeave;
}

}