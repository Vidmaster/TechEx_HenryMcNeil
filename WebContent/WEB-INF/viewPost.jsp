<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="w3.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Post: ${post.subject}</title>
</head>
<body class="w3-light-grey">
	<c:if test="${!message.isEmpty()}">
		${message}
	</c:if>
	<a href="index.jsp">&lt;&lt; Home</a>
	<div class="post" id="post${post.id}">
		<h3><a href="viewPost.jsp?post=${post.id}"><c:out value="${post.subject}" /></a></h3>
		<h4>Posted by <a href="viewUser.jsp?author=${post.userId}"><c:out value="${post.userName}" /></a> on ${post.posted}<c:if test="${post.updated != null}">, updated on ${post.updated}</c:if></h4>
		
		<p><c:out value="${post.body}" /></p>
		<p><a href="editPost.jsp?post=${post.id}">Edit post</a>
	</div>
	<div class="comments" id="comments">
		<c:forEach items="${comments}" var="comment">
			<div class="comment" id="comment${comment.id}">
				<h4><c:out value="${comment.subject}" /></h4>
				Posted by <c:out value="${comment.authorName}" /> at ${comment.posted}
				<p><c:out value="${comment.text}" /></p>
			</div>
		</c:forEach>
	</div>	
	<h3>Add Comment</h3>
	<form action="AddCommentServlet" method="post">
		Subject: <input type="text" name="subject" size=80 maxlength=240><br>
		Author: <input type="text" name="author_name" size=60 maxlength=60><br>
		Comment:<br>
		<textarea name="text" cols=100 rows=4></textarea>
		<input type="hidden" name="post" value="${post.id}">
		<br>
		<input type="submit" value="Submit">
		<input type="reset" value="Clear">
	</form>
</body>
</html>