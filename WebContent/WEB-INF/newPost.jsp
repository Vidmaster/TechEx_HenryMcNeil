<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="w3.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New Post</title>
</head>
<body class="w3-light-grey">
	<div class="w3-content" style="max-width:1400px">
		<div class="w3-row">
			<span class="w3-padding-large w3-left"><a href="index.jsp">&lt;&lt; Home</a></span>
		</div>
		<div class="w3-container w3-center w3-padding-8"><h2>Create a new blog post:</h2></div>
	
		<div class="w3-row">
			<div class="w3-col">
				<div class="w3-card-4 w3-margin w3-white">
					<form action="newPost.jsp" method="post">
						<div class="w3-row w3-container w3-padding-8">
							Subject: <input type="text" name="subject" size=80 class="w3-border w3-padding" autofocus>
							<br><br>
							Author: <select name="user" required class="w3-border w3-padding">
								<c:forEach items="${users}" var="user">
									<option value="${user.id}"><c:out value="${user.name}" /></option>
								</c:forEach>
							</select>
						</div>
						<div class="w3-row w3-container w3-padding-8">
							<p>Post:</p>
							<textarea cols=100 rows=20 name="body" class="w3-border w3-padding"></textarea>
						</div>
						<div class="w3-row w3-container w3-padding-8">
							<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="submit" value="Submit">
							<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="reset" value="Reset">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>