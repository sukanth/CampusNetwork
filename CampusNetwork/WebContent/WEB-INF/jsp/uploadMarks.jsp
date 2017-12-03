<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Manage Preformance</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<script src="${context}/js/site.js"></script>
<script src="${context}/js/uploadMarks.js"></script>
</head>
<body>
	<input type="hidden" id="contextRoot" value="${context}">
	<jsp:include page="includes/navigation.jspf" />
	<div id="mainContent" class="container">
	<form id="uploadMarksForm" action="" enctype='multipart/form-data'>
	<input type="hidden" id="hCourseId">
	<input type="hidden" id="hTopicName">
		<div id="uploadMarks-accordion">
			<h3>Upload Marks</h3>
			<div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Course :</label>
					<select class="form-control dropdown-toggle getAssessement" id="courseId" name="courseId" style="width:400px;margin-left:280px";>
					 <option value = ''>--SELECT--</option>
					 <c:forEach items="${availableCourses}" var="course">
			         		<option value = '${course}'>${course}</option>
			         	</c:forEach>
					</select>
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Type Of Assessment :</label>
					<select class="form-control dropdown-toggle getAssessement" id="assessmentType" name="assessmentType" style="width:400px;margin-left:280px";>
						 <option value = ''>--SELECT--</option>
						 <option value = 'assignments'>Assignments</option>
			             <option value = 'lab'>Labs</option>
			             <option value = 'exam'>Exams</option>
		            </select>
				</div>
				<div class="form-group" style="margin-left: 200px;">
					<label style="float:left;margin-right:5px;font-weight: bold;">Select Assessment :</label>
					<select class="form-control dropdown-toggle" id="assessment" name="assessment" style="width:400px;margin-left:280px";>
						 <option value = ''>--SELECT--</option>
		            </select>
				</div>
				<input type="button" ID="getMarks" value="Get Marks"
					Class="btn btn-lg btn-primary btn-block"
					style="width:400px;margin-left:300px;" />
						<br />
						<div id="divMarks">
						<div class="form-group" style="margin-left: 165px;">
						<label style="float: left; margin-right: 5px; font-weight: bold;padding-top: 5px">TotalMarks
							:</label> <input type="number" required class="form-control" id="totalMarks"
							name="totalMarks" placeholder="Total Marks" min ="0"
							style="width: 120px; margin-left: 140px;">
					</div>
						<table id="uploadMarks" cellspacing="">
							<thead>
								<tr>
									<th>SSO</th>
									<th>Student Name</th>
									<th>TotalMarks</th>
									<th>MarksObtained</th>
									<th>Percentage</th>
									<th>Comments</th>
								</tr>
							</thead>
						</table>
					<br/>
				<input type="button" ID="btnUpload" value="Upload Marks"
					Class="btn btn-lg btn-primary btn-block"
					style="width:400px;margin-left:300px;" />
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