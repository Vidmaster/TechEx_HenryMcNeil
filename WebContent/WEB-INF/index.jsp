<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="w3.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Henry's Tech Exercise Blog</title>
</head>
<body class="w3-light-grey">
	<div class="w3-content" style="max-width:1400px">
		<c:if test="${!message.isEmpty()}">
			${message}
		</c:if>
		<div class="class=w3-container w3-center w3-padding-32">
			<h1>Welcome to my blog!</h1>
			<p><a href="newPost.jsp">New Post</a> | <a href="newUser.jsp">New User</a></p>
		</div>
		
		<br>
		<h2>Recent Posts:</h2>
		<div class="w3-row" id = posts>
			<c:if test="${posts.isEmpty()}">
				<p>No posts to display.</p>
			</c:if>
			<c:forEach items="${posts}" var="post">
				<div class="w3-card-4 w3-margin w3-white" id="post${post.id}">
					<div class="w3-container w3-padding-8">
						<h3><a href="viewPost.jsp?post=${post.id}"><c:out value="${post.subject}" /></a></h3>
						<h5>Posted by <a href="viewUser.jsp?author=${post.userId}">${post.userName}</a> on ${post.posted}<c:if test="${post.updated != null}">, updated on ${post.updated}</c:if></h5>
						<div class="w3-container"><p><c:out value="${post.body}" /></p></div>
						<div class="w3-row">
							<span class="w3-padding-large w3-left"><a href="viewPost.jsp?post=${post.id}#addComment">Add Comment</a></span>
							<span class="w3-padding-large w3-right">Comments <span class="w3-tag">${post.numComments}</span></span>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>