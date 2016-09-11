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
	<h2>Create a new blog post:</h2>
	<a href="index.jsp">&lt;&lt; Home</a>
	<br><br>
	<form action="newPost.jsp" method="post">
		Subject: <input type="text" name="subject" size=80 autofocus><br>
		Author: <select name="user" required>
			<c:forEach items="${users}" var="user">
				<option value="${user.id}"><c:out value="${user.name}" /></option>
			</c:forEach>
		</select>
		<br>
		Post: <br>
		<textarea cols=100 rows=20 name="body"></textarea>
		<br>
		<input type="submit" value="Submit">
		<input type="reset" value="Clear">
	</form>
</body>
</html>