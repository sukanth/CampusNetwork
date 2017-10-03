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
<script src="${context}/js/student-home.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
	
		<div id="student-home-accordion">
			<h3>Course Progress And Important Dates</h3>
			<div>
				<div class="form-group" style="margin-left: 20px;">
					<div class="panel panel-primary"
						style="float: left; margin-right: 25px; width: 500px;">
						<div class="panel-heading">Important Dates</div>
						<div class="panel-body">
							<table cellspacing="">
								<c:forEach items="${importantDates}" varStatus="loopCounter"
									var="importantDate">
									<tr>
										<td width="200px"><c:out
												value="${importantDate.courseId}" /></td>
										<td width="350px"><c:out
												value="${importantDate.assignmentName}" /></td>
										<td width="350px"><c:out
												value="${importantDate.assignmentDeadline}" /></td>
										<td width="100px"><button type="button"
												class="btn btn-primary btn-lg" data-toggle="modal"
												data-target="#myModal1">View</button></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="panel panel-primary"
						style="float: right; width: 500px;">
						<div class="panel-heading">Upcoming Assessments</div>
						<div class="panel-body">
							<table cellspacing="">
								<c:forEach items="${upcomingAssessments}"
									varStatus="loopCounter" var="upcomingAssessment">
									<tr>
										<td width="200px"><c:out
												value="${upcomingAssessment.courseId}" /></td>
										<td width="350px"><c:out
												value="${upcomingAssessment.topicName}" /></td>
										<td width="350px"><c:out
												value="${upcomingAssessment.topicDate}" /></td>
										<td width="100px"><button type="button"
												class="btn btn-primary btn-lg" data-toggle="modal"
												data-target="#myModal1">View</button></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="panel panel-primary"
						style="float: left; margin-right: 25px; width: 1050px">
						<div class="panel-heading">Course Progress</div>
						<div class="panel-body">
							<Label ID="lblCourse1"
								style="font-style: italic; font-weight: bold; font-size: small; color: #333300">Course
								Id : CS551MT CourseName : Software Methods and Tools</Label>
							<div class="progress">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
							<br /> <Label ID="lblCourse2"
								style="font-style: italic; font-weight: bold; font-size: small; color: #333300">Course
								Id : CS551NA CourseName : Network Architecture</Label>
							<div class="progress">
								<div id="div1" class="progress-bar progress-bar-info"
									role="progressbar" aria-valuenow="20" aria-valuemin="0"
									aria-valuemax="100" style="width: 55.8%" runat="server">
									<span class="sr-only">20% Complete</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<h3>Course Plan</h3>
			<div style="border-left: 30px;">

				<div class="form-group" style="margin-left: 370px;">

					<Label ID="lblCourse2"
						style="font-style: italic; font-weight: bold; font-size: x-large;; color: #333300">Enrolled
						Courses</Label> <br /> <br />
				</div>
				<table id="enrolledCourses" cellspacing="">
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
					<c:forEach items="${enrolledCourses}" varStatus="loopCounter"
						var="enrolledCourse">
						<tr>
							<td width="200px"><c:out value="${enrolledCourse.courseId}" /></td>
							<td width="350px"><c:out
									value="${enrolledCourse.courseName}" /></td>
							<td width="350px"><c:out value="${enrolledCourse.startDate}" /></td>
							<td width="350px"><c:out value="${enrolledCourse.endDate}" /></td>
							<td style="padding-left: 40px" width="350px"><c:out
									value="${enrolledCourse.credits}" /></td>
							<td width="100px"><button type="button"
									courseId="${enrolledCourse.courseId}"
									class="getSchedule btn btn-primary btn-lg" data-toggle="modal"
									data-target="#myModal1">Get Schedule</button></td>
						</tr>
					</c:forEach>
				</table>
				<div id="divCoursesSchedule">
					<br />
					<br />
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