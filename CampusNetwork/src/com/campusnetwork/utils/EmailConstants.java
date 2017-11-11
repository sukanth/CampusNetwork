package com.campusnetwork.utils;

public class EmailConstants {
	
	public static final String NOTIFICATION_APPROVED = "<div>\r\n" + 
			"<h1>Your Appointment with #Professor# on #DATE# #TIME# has been Approved.<br><br>\r\n" + 
			"Please Make yourself Available during the scheduled time.</h1><br>\r\n" + 
			"<i>Note: This is an auto-generated email. Please Do not Reply.</i>\r\n" + 
			"</div>"; 
	
	public static final String NOTIFICATION_REJECTED = "<div>\r\n" + 
			"<h1>Your Appointment with #Professor# on #DATE# #TIME# has been Rejected.<br><br>\r\n" + 
			"Please Request appointment on any other date or time.</h1><br>\r\n" + 
			"<i>Note: This is an auto-generated email. Please Do not Reply.</i>\r\n" + 
			"</div>"; 
	
	public static final String NOTIFICATION_REQUEST = "<div>\r\n" + 
			"<h1>#STUDENTNAME# has requested an appointment on #DATE# #TIME#.<br><br>\r\n" + 
			"Please accept the appointment based on your availability.</h1><br>\r\n" + 
			"<i>Note: This is an auto-generated email. Please Do not Reply.</i>\r\n" + 
			"</div>"; 
}
