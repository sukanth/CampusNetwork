<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Mark Attendance</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/markAttendance.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<form id="setAttendanceForm" action="" enctype='multipart/form-data'>
			<div id="setAttendance-accordion">
				<h3>Mark Attendance</h3>
				<div>
					<label
						style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">Mark
						Attendance</label>
					<div class="errMsg alert alert-danger"
						style="text-align: center;">
						<a id="" href="#" class="close hideMsg">&times;</a><span class="errMsgSpan"></span>
					</div>
					<div class="form-group" style="margin-left: 240px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Select
							Course :</label> <select class="form-control dropdown-toggle"
							id="courseId" name="courseId"
							style="width: 400px; margin-left: 240px";>
							<option value=''>--SELECT--</option>
							<c:forEach items="${availableCourses}" var="course">
								<option value='${course}'>${course}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group" style="margin-left: 240px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Select
							Date :</label> <input type="text" required class="form-control"
							id="attendanceDate" name="attendanceDate" readonly="readonly"
							placeholder="Select Attendance Date"
							style="width: 400px; margin-left: 240px;">
					</div>
					<div class="form-group" style="margin-left: 240px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Latitude
							:</label> <input type="text" required class="form-control" id="latitude"
							name="latitude" placeholder="Latitude" readonly="readonly"
							style="width: 400px; margin-left: 240px;">
					</div>
					<div class="form-group" style="margin-left: 240px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Longitude
							:</label> <input type="text" required class="form-control" id="longitude"
							name="longitude" placeholder="Longitude" readonly="readonly"
							style="width: 400px; margin-left: 240px;">
					</div>
					<div class="form-group" style="margin-left: 240px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Attendance Code
							:</label> <input type="text" required class="form-control" id="attendanceCode"
							name="attendanceCode" placeholder="Attendance Code"
							style="width: 400px; margin-left: 240px;">
					</div>

					<input type="button" ID="markAttendance" value="Mark Attendance"
						Class="btn btn-lg btn-primary btn-block"
						style="width: 400px; margin-left: 300px;" />
				</div>
				<h3>Check Attendance</h3>
				<div style="border-left: 30px;">
					<label
						style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">Check Attendance</label>
					<div id="divAttendance">
						<br />
						<table id="Attendances" cellspacing="">
							<thead>
								<tr>
									<th>Course Id</th>
									<th>Attendance Date</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<jsp:include page="includes/footer.jspf" />
</body>
</html>