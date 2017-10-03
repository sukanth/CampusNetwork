<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Home</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/instructor-home.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<div id="instructor-home-accordion">
			<h3>Assigned Courses Progress</h3>
			<div>
				<div class="form-group" style="margin-left: 20px;">
					<Label ID="lblCourse2"
						style="font-style: italic; font-weight: bold; font-size: x-large;; color: #333300">Course Progress</Label> <br /> <br />
						 <Label ID="lblCourse1"
						style="font-style: italic; font-weight: bold; font-size: small; color: #333300">Course
						Id : CS551MT CourseName : Software Methods and Tools</Label>
					<div class="progress">
						<div class="progress-bar progress-bar-success" role="progressbar"
							aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
							style="width: 40%">
							<span class="sr-only">40% Complete (success)</span>
						</div>
					</div>
					<br /> <Label ID="lblCourse2"
						style="font-style: italic; font-weight: bold; font-size: small; color: #333300">Course
						Id : CS551NA CourseName : Network Architecture</Label>
					<div class="progress">
						<div id="div1" class="progress-bar progress-bar-info"
							role="progressbar" aria-valuenow="20" aria-valuemin="0"
							aria-valuemax="100" style="width: 20%" runat="server">
							<span class="sr-only">20% Complete</span>
						</div>
					</div>
					<br/>
					<div class="progress">
						<div id="div1" class="progress-bar progress-bar-warning"
							role="progressbar" aria-valuenow="20" aria-valuemin="0"
							aria-valuemax="100" style="width: 60%" runat="server">
							<span class="sr-only">60% Complete (warning)</span>
						</div>
					</div>
					<br/>
					<div class="progress">
						<div id="div1" class="progress-bar progress-bar-danger"
							role="progressbar" aria-valuenow="20" aria-valuemin="0"
							aria-valuemax="100" style="width: 80%" runat="server">
							<span class="sr-only">80% Complete (danger)</span>
						</div>
					</div>
				</div>
			</div>
	<h3>Check Schedules</h3>
	<div style="border-left: 30px;">

		<div class="form-group" style="margin-left: 370px;">

			<Label ID="lblCourse2"
				style="font-style: italic; font-weight: bold; font-size: x-large;; color: #333300">Assigned
				Courses</Label> <br /> <br />
		</div>
		<table id="assignedCourses" cellspacing="">
			<thead>
				<tr>
					<th>Course Id</th>
					<th>Course Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Credits</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${assignedCourses}" varStatus="loopCounter"
				var="assignedCourse">
				<tr>
					<td width="200px"><c:out value="${assignedCourse.courseId}" /></td>
					<td width="350px"><c:out value="${assignedCourse.courseName}" /></td>
					<td width="350px"><c:out value="${assignedCourse.startDate}" /></td>
					<td width="350px"><c:out value="${assignedCourse.endDate}" /></td>
					<td style="padding-left: 40px" width="350px"><c:out
							value="${assignedCourse.credits}" /></td>
					<td width="100px"><button type="button"
							courseId="${assignedCourse.courseId}"
							class="getSchedule btn btn-primary btn-lg" data-toggle="modal"
							data-target="#myModal1">Get Schedule</button></td>
				</tr>
			</c:forEach>
		</table>
		<div id="divCoursesSchedule">
			<br /> <br />
			<div class="alert alert-info alert-dismissible" id="diverror"
				runat="server" role="alert">
				<div style="margin-left: 340px;">
					<strong>Course Schedule For : <label id="courseName"></label></strong>
				</div>
			</div>
			<table id="coursesSchedule" cellspacing="">
				<thead>
					<tr>
						<th>Date</th>
						<th>Topic</th>
						<th>Assignment</th>
						<th>Assignment Deadline</th>
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