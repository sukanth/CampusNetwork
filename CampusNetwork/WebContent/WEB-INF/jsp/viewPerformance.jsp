<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - View Performance</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/viewPerformance.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<div id="view-performance-accordion">
			<h3>View Performance</h3>
			<div style="border-left: 30px;">
			<label style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">View Performance</label>
					<div class="form-group" style="margin-left: 200px;">
					<label style="float: left; margin-right: 5px; font-weight: bold;">Select
						Course :</label> <select
						class="form-control dropdown-toggle getAssessement" id="pcourseId"
						name="courseId" style="width: 400px; margin-left: 280px;">
						<option value=''>--SELECT--</option>
						<c:forEach items="${availableCourses}" var="course">
							<option value='${course}'>${course}</option>
						</c:forEach>
					</select>
				</div>
				<input type="button" ID="viewPerformance" value="View Performance"
					Class="btn btn-lg btn-primary btn-block"
					style="width: 400px; margin-left: 300px;" />
				<div id="divIndividualPerformance" style="padding-top: 2%;">
					<div class="alert alert-info alert-dismissible"
						style="padding-top: 1%; padding-bottom: 1%">
						<strong>Performance For Course: <label class="labels" ID="lblCourseId"></label>
						</strong>
					</div>
					<br />
					<table id="individualPerformance" cellspacing="">
						<thead>
							<tr>
								<th>Course Id</th>
								<th>Topic Name</th>
								<th>Total Marks</th>
								<th>Marks Obtained</th>
								<th>Percentage</th>
								<th>comments</th>
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