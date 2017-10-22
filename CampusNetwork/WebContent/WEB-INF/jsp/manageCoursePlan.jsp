<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Manage Course Plan</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/manageCoursePlan.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<form id="coursePlanForm" action="" enctype='multipart/form-data'>
			<div id="course-plan-accordion">
				<h3>Add Course Plan</h3>
				<div>
					<label
						style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">Add
						Course Plan</label>
					<div class="form-group" style="margin-left: 200px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Select
							Course :</label> <select
							class="form-control dropdown-toggle getAssessement" id="courseId"
							name="courseId" style="width: 400px; margin-left: 280px;">
							<option value=''>--SELECT--</option>
							<c:forEach items="${availableCourses}" var="course">
								<option value='${course}'>${course}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group" style="margin-left: 200px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Browse
							For File :</label> <input type="file" name="dataFile" class=""
							style="width: 400px; margin-left: 280px";>
					</div>
					<input type="button" ID="btnUpload" value="Upload Schedule"
						Class="btn btn-lg btn-primary btn-block"
						style="width: 400px; margin-left: 300px;" />
				</div>
				<h3>Edit Course Plan</h3>
				<div style="border-left: 30px;">
					<label
						style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">Edit
						Course Plan</label>
					<div class="form-group" style="margin-left: 200px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;">Select
							Course :</label> <select
							class="form-control dropdown-toggle getAssessement"
							id="pcourseId" name="courseId"
							style="width: 400px; margin-left: 280px;">
							<option value=''>--SELECT--</option>
							<c:forEach items="${availableCourses}" var="course">
								<option value='${course}'>${course}</option>
							</c:forEach>
						</select>
					</div>
					<input type="button" ID="getSchedule" value="Get Schedule"
						Class="btn btn-lg btn-primary btn-block"
						style="width: 400px; margin-left: 300px;" />
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
									<th></th>
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
	<br />
	<br />
	<br />
	<br />
	<jsp:include page="includes/footer.jspf" />
</body>
</html>