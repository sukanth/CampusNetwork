<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Manage Appointments</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/instructor-appointments.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<div id="instructor-appointments-accordion">
			<h3>Set Appointments</h3>
			<div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select From Date :</label>
					<input type="text" required class="form-control" id="fromDate" name="fromDate" placeholder="From Date" style="width:400px;margin-left:240px;">
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select To Date :</label>
					<input type="text" required class="form-control" id="toDate" name="toDate" placeholder="To Date" style="width:400px;margin-left:240px;">
				</div>

				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select From time :</label>
					<input type="text" required class="form-control timepicker" id="fromTime" name="fromTime" placeholder="From Time" style="width:150px;margin-left:240px;">
				</div>
				<div class="form-group" style="margin-left: 200px;">	
				<label style="float:left;margin-right:5px;font-weight: bold;">Select To time :</label>
					<input type="text" required class="form-control timepicker" id="toTime" name="toTime" placeholder="To Time" style="width:150px;margin-left:240px;">
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Duration of Appointment :</label>
					<input type="number" required class="form-control" id="txtDuration" name="txtDuration" placeholder="Duration Of Appointment(in Minutes)" style="width:400px;margin-left:240px;">
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Max No Of Appointments :</label>
					<input type="number" required class="form-control" id="txtMaxSlots" name="txtMaxSlots" placeholder="Max No of Appointments" style="width:400px;margin-left:240px;">
				</div>
				<input type="button" ID="btnSubmit" value="Set Appointment"
					Class="btn btn-lg btn-primary btn-block"
					style="width:400px;margin-left:300px;" />
			</div>
			<h3>Cancel Appointments</h3>
			<div style="border-left: 30px;">
			<div id="divAppointments">
			<br />
			<table id="Appointments" cellspacing="">
				<thead>
					<tr>
						<th>Appointment From Date</th>
						<th>Appointment To Date</th>
						<th>From Time</th>
						<th>To Time</th>
						<th>Duration</th>
						<th>Max Appointments</th>
						<th>Cancel Appointment</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<jsp:include page="includes/footer.jspf" />
</body>
</html>