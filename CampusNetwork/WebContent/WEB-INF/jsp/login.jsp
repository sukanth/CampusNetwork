<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CampusNetwork - Login</title>
<jsp:include page="includes/commonIncludes.jspf" />
<link rel="stylesheet" href="${context}/css/site-styles.css">
<link rel="stylesheet" href="${context}/css/login.css">
</head>
<body>
	<jsp:include page="includes/header.jspf" />
	<!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img src="/CampusNetwork/images/1.jpg" style="width:1200px;margin-left:83px;" alt="Second slide"/>  
        </div>
           <div class="item">
          <img src="/CampusNetwork/images/3.jpg" style="width:1200px;margin-left:83px;" alt="First slide"/>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->
	<div id="mainContent" class="container">
		<div class="wrapper">
			<c:if test="${msg != null}">
				<div class="loginError alert alert-danger fade in"
					style="text-align: center;">
					<a href="#" class="close" data-dismiss="alert">&times;</a> ${msg}
				</div>
			</c:if>
			<form action="${context}/login/validateUser.htm" method="post"
				name="Login_Form" class="form-signin">
				<h3 class="form-signin-heading">Welcome Back! Please Sign In</h3>
				<hr class="colorgraph">
				<br> <input type="text" class="form-control" name="email"
					placeholder="Username" required="" /> <input
					type="password" class="form-control" name="password"
					placeholder="Password" required="" />

				<button class="btn btn-lg btn-primary btn-block" name="Submit"
					value="Login" type="Submit">Login</button>
			</form>
			 <div class="push"></div>
		</div>
	</div>
	<jsp:include page="includes/footer.jspf" />
</body>
</html>