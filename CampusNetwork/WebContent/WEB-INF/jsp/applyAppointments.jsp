<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Appointment Requests</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/appointmentRequests.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container" style="min-height: 510px">
			<h3>Appointment Requests</h3>
			<div style="border-left: 30px;">
			<div id="divAppointments">
			<br />
			<table id="Appointments" cellspacing="">
				<thead>
					<tr>
						<th>Appointment Date</th>
						<th>Student Name</th>
						<th>Appointment Time</th>
						<th>Appointment Type</th>
						<th>Description</th>
						<th style="width: 250px">Action</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
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