<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Analyze Attendance</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/analyzeAttendance.js"></script>
<script type="text/javascript"
	src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script type="text/javascript"
	src="https://www.amcharts.com/lib/3/serial.js"></script>
<style>
.amcharts-chart-div a {
	display: none !important;
}

#chartdiv {
	width: 100%;
	height: 435px;
	font-size: 11px;
}

.border {
	border: 2px solid #393939;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 4px 4px 5px rgba(50, 50, 50, 0.75);
	-moz-box-shadow: 4px 4px 5px rgba(50, 50, 50, 0.75);
	box-shadow: 4px 4px 5px rgba(50, 50, 50, 0.75);
}

.labels {
	margin-left: 3px;
	padding-top: 2px;
	text-shadow: 2px 2px 3px rgba(150, 150, 150, 0.75);
	font-family: Verdana, Geneva, sans-serif;
	font-size: .9em;
}

legend {
	text-shadow: 2px 2px 3px rgba(150, 150, 150, 0.75);
	font-family: Verdana, Geneva, sans-serif;
	font-size: 1.4em;
	border-top: 2px solid #009;
	border-bottom: 2px solid #009;
	border-left: 2px solid #009;
	border-right: 2px solid #009;
	border-radius: 10px;
	-webkit-box-shadow: 4px 4px 5px rgba(50, 50, 50, 0.75);
	-moz-box-shadow: 4px 4px 5px rgba(50, 50, 50, 0.75);
	box-shadow: 4px 4px 5px rgba(50, 50, 50, 0.75);
	padding: 3px;
}

.inline {
	display: inline-block;
}
</style>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
		<div id="analyze-attendance-accordion">
			<h3>Class Attendance</h3>
			<div>
				<label
					style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">Class
					Performance</label>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float: left; margin-right: 5px; font-weight: bold;">Select
						Course :</label> <select
						class="form-control dropdown-toggle" id="courseId"
						name="courseId" style="width: 400px; margin-left: 280px;">
						<option value=''>--SELECT--</option>
						<c:forEach items="${availableCourses}" var="course">
							<option value='${course}'>${course}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float: left; margin-right: 5px; font-weight: bold;">Select Attendance Date :</label> 
					<select class="form-control dropdown-toggle"
						id="attendanceDate" name="attendanceDate"
						style="width: 400px; margin-left: 280px;">
						<option value=''>--SELECT--</option>
					</select>
				</div>
				<input type="button" ID="btnSubmit" value="Analyze"
					Class="btn btn-lg btn-primary btn-block"
					style="width: 400px; margin-left: 300px;" />
				<div id="chartView" style="padding-top: 1%;">
					<div class="alert alert-info alert-dismissible"
						style="padding-top: 1%; padding-bottom: 1%">
						<strong>Course: <label class="labels" ID="lblCourseId"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Course
							Strength: <label class="labels" ID="lblStrength"></label>
						</strong>
					</div>
					<fieldset class="border">
						<legend>Visualization</legend>
						<div id="chartdiv"></div>
						<div class="container-fluid">
							<div class="row text-center" style="overflow: hidden;">
								<div class="col-sm-3"
									style="float: none !important; display: inline-block;"></div>

								<div class="col-sm-3"
									style="float: none !important; display: inline-block;"></div>

								<div class="col-sm-3"
									style="float: none !important; display: inline-block;"></div>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
			<h3>Individual Attendance</h3>
			<div style="border-left: 30px;">
			<label style="padding-bottom: 1%; margin-left: 330px; font-weight: bold; font-size: x-large; font-style: italic;">Individual Performance</label>
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
				<div class="form-group" style="margin-left: 200px;">
					<label style="float: left; margin-right: 5px; font-weight: bold;">Select Student :</label> 
					<select class="form-control dropdown-toggle" id="ssoId" name="ssoId" style="width: 400px; margin-left: 280px;">
						<option value=''>--SELECT--</option>
					</select>
				</div>
				<input type="button" ID="viewAttendance" value="View Attendance"
					Class="btn btn-lg btn-primary btn-block"
					style="width: 400px; margin-left: 300px;" />
				<div id="divIndividualAttendance" style="padding-top: 2%;">
					<br />
					<table id="individualAttendance" cellspacing="">
						<thead>
								<tr>
									<th>Attendance Date</th>
									<th>Status</th>
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
	<jsp:include page="includes/footer.jspf" />
</body>
</html>