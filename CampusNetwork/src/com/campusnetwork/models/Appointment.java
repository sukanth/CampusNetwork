package com.campusnetwork.models;

import java.io.Serializable;

public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3934924817323992666L;
	
	private int sso;
	private int instructorId;
	private String appointmentFromDate;
	private String appointmentToDate;
	private String fromTime;
	private String toTime;
	private int appointmentDuration;
	private int maxAppointments;
	private int appointmentId;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentType;
	private String desc;
	private String status;
	private String instructorName;
	private String studentName;
	
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getAppointmentFromDate() {
		return appointmentFromDate;
	}
	public void setAppointmentFromDate(String appointmentFromDate) {
		this.appointmentFromDate = appointmentFromDate;
	}
	public String getAppointmentToDate() {
		return appointmentToDate;
	}
	public void setAppointmentToDate(String appointmentToDate) {
		this.appointmentToDate = appointmentToDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public int getAppointmentDuration() {
		return appointmentDuration;
	}
	public void setAppointmentDuration(int appointmentDuration) {
		this.appointmentDuration = appointmentDuration;
	}
	public int getMaxAppointments() {
		return maxAppointments;
	}
	public void setMaxAppointments(int maxAppointments) {
		this.maxAppointments = maxAppointments;
	}
	public int getSso() {
		return sso;
	}
	public void setSso(int sso) {
		this.sso = sso;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	

}
