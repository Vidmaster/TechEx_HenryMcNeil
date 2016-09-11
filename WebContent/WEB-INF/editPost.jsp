<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="w3.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Post: ${post.subject}</title>
</head>
<body class="w3-light-grey">
	<a href="viewPost.jsp?post=${post.id}">&lt;&lt; Back</a>
	<h2>Edit Post:</h2>
	<div class="post" id="post">
		<form action="editPost.jsp" method="post">
			Subject: <input type="text" name="subject" value="${post.subject}" size=80 >
			<textarea name="body" cols=100 rows=20><c:out value="${post.body}" /></textarea><br>
			<input type="hidden" name="post" value="${post.id}">
			<input type="submit" value="Submit">
			<input type="reset" value="Clear">
		</form>
	</div>
</body>
</html>