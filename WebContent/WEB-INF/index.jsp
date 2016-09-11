<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Henry's Tech Exercise Blog</title>
</head>
<body>
	<c:if test="${!message.isEmpty()}">
		${message}
	</c:if>
	<div class="header"><h1>Welcome to my blog!</h1></div>
	<div class="links" id = "links"><a href="newPost.jsp">New Post</a> | <a href="newUser.jsp">New User</a></div>
	<br>
	<h2>Recent Posts:</h2>
	<div class="allPosts" id = posts>
		<c:if test="${posts.isEmpty()}">
			<p>No posts to display.</p>
		</c:if>
		<c:forEach items="${posts}" var="post">
			<div class="post" id="post${post.id}">
				<h3><a href="viewPost.jsp?post=${post.id}">${post.subject}</a></h3>
				<h4>Posted by <a href="viewUser.jsp?author=${post.userId}">${post.userName}</a> on ${post.posted}</h4>
				<p>${post.body}</p>
				<a href="viewPost.jsp?post=${post.id}">Comments ( ${post.numComments} )</a> | <a href="viewPost.jsp?post=${post.id}">Add Comment</a>
			</div>
			<br><br>
		</c:forEach>
	</div>
</body>

</html>