package com.campusnetwork.models;

public class CoursePlan extends Course {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3371831890200720889L;
	
	private int uniqueId;
	private String topicDate;
	private String topicName;
	private String assignmentName;
	private String assignmentDeadline;
	
	
	public int getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getTopicDate() {
		return topicDate;
	}
	public void setTopicDate(String topicDate) {
		this.topicDate = topicDate;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public String getAssignmentDeadline() {
		return assignmentDeadline;
	}
	public void setAssignmentDeadline(String assignmentDeadline) {
		this.assignmentDeadline = assignmentDeadline;
	}
}
