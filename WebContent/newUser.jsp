<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="w3.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New User</title>
</head>
<body class="w3-light-grey">
	<div class="w3-content" style="max-width:1400px">
		<c:if test="${!message.isEmpty()}">
			${message}
		</c:if>
		<div class="w3-row">
			<span class="w3-padding-large w3-left"><a href="index.jsp">&lt;&lt; Home</a></span>
		</div>
		<div class="w3-container w3-center w3-padding-8">
			<h2>Create a new blog user:</h2>
		</div>
		<div class="w3-row">
			<div class="w3-col">
				<div class="w3-card-4 w3-margin w3-white">
					<div class="w3-container w3-padding-8">
						<form action="UserServlet" method="post">
							<div class="w3-row w3-container w3-padding-4">
								User Name: <input type="text" name="name" size=80 class="w3-border w3-padding" autofocus>
							</div>
							<div class="w3-row w3-container w3-padding-4"><p>Biography:</p>
								<textarea cols=100 rows=8 name="description" class="w3-border w3-padding"></textarea>
							</div>
							<div class="w3-row w3-container w3-padding-4">
								<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="submit" value="Submit">
								<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="reset" value="Reset">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>