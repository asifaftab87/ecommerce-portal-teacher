<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>

<link rel="shortcut icon"
	href="http://localhost:9292/teacher/favicon.ico">
<link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<link href="webjars/bootstrap-datepicker/1.0.1/css/datepicker.css"
	rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="webjars/bootstrap-datepicker/1.0.1/js/bootstrap-datepicker.js"></script>
</head>

<body>

	<div class="container mb-4">
		<form action="/teacher/registration" method="post" modelAttribute="userDto">
			
			<p class="h4 mb-4 text-center mt-4">Sign up</p>
			
			<div class="container border">
				
				<div class="form-group mt-3">
			        <label for="firstName">First Name</label>
			        <input type="text" class="form-control" id="firstName" placeholder="Enter first name" name="firstName">
			    </div>
			    
			    <div class="form-group">
			        <label for="lastName">Last Name</label>
			        <input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName">
			    </div>
			    
			    <div class="form-group">
			        <label for="password">Date of Birth</label>
			        <input type="date" name="dobString" max="3000-12-31" min="1000-01-01" class="form-control" id="dobString">
			    </div>
			    
			    <div class="form-group">
			        <label for="email">Email</label>
			        <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email">
			    </div>
			    
			    <div class="form-group">
			        <label for="contactNum">Contact Number</label>
			        <input type="text" class="form-control" id="contactNum" placeholder="Enter Contact Number" name="contactNum">
			    </div>
			   
			    <div class="form-group">
			        <label for="password">Password</label>
			        <input type="password" class="form-control" id="passwordUser" placeholder="Password" name="passwordUser">
			    </div>
			    
			    <div class="form-check-inline">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="roleName" value="ROLE_TEACHER">Teacher
				  </label>
				</div>
				<div class="form-check-inline">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="roleName" value="ROLE_STUDENT" checked>Student
				  </label>
				</div>
				<div class="form-check-inline">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="roleName" value="ROLE_USER">User
				  </label>
				</div>
				 <div class="form-group mt-3">
			    	<button type="submit" class="btn btn-primary">Sign Up</button>
			    </div>
		    </div>
		</form>
	</div>
	
	<script src="js/custom.js"></script>
</body>

</html>