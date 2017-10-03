<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Request Appointments</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/requestAppointments.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<div id="student-appointments-accordion">
			<h3>Request Appointments</h3>
			<div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Appointment Date :</label>
					<input type="text" required class="form-control getApptTime" id="appointmentDate" name="appointmentDate" placeholder="Appointment Date" style="width:400px;margin-left:240px;">
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Professor :</label>
					<select class="form-control dropdown-toggle getApptTime" id="instructor" name="instructor" style="width:400px;margin-left:240px";>
					 <option value = ''>--SELECT--</option>
					 <c:forEach items="${instructors}" var="instructor">
					 	<option value="${instructor.key}">${instructor.value}</option>
					 </c:forEach>
					</select>
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Appointment Time :</label>
					<select class="form-control dropdown-toggle" id="appointmentTime" name="appointmentTime" style="width:400px;margin-left:240px";>
					 <option>--SELECT--</option>
					</select>
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Appointment Type :</label>
					<select class="form-control dropdown-toggle" id="appointmentType" name="appointmentType" style="width:400px;margin-left:240px";>
						 <option>--SELECT--</option>
						 <option>Enrollment</option>
			             <option>Question</option>
			             <option>Emergency</option>
			             <option>Others</option>
					</select>
				</div>
				<div class="form-group" style="margin-left: 200px;">	
				<label style="float:left;margin-right:5px;font-weight: bold;">Description:</label>
					<input type="text" required class="form-control" id="description" name="description" placeholder="Description" style="width:400px;margin-left:240px;">
				</div>
				<input type="button" ID="btnSubmit" value="Request Appointment"
					Class="btn btn-lg btn-primary btn-block"
					style="width:400px;margin-left:300px;" />
			</div>
			<h3>Check Status</h3>
			<div style="border-left: 30px;">
			<div id="divAppointments">
			<br />
			<table id="Appointments" cellspacing="">
				<thead>
					<tr>
						<th>Appointment Date</th>
						<th>Instructor Name</th>
						<th>Appointment Time</th>
						<th>Appointment Type</th>
						<th>Description</th>
						<th>Appointment Status</th>
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