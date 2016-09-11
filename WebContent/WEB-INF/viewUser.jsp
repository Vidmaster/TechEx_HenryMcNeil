<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="w3.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile for ${user.name}</title>
</head>
<body class="w3-light-grey">
	<a href="index.jsp">&lt;&lt; Home</a>
	<div class="w3-hide-large w3-hide-small w3-margin-top w3-margin-bottom">
	    <div class="w3-container w3-white w3-padding-32">
			<h2><c:out value="${user.name}" /></h2>
			<p><c:out value="${user.description}" /></p>
		</div>
	</div>
	<br>
	<h2>Recent Posts:</h2>
	<div class="allPosts" id = posts>
		<c:if test="${posts.isEmpty()}">
			<p>No posts by this user.</p>
		</c:if>
		<c:forEach items="${posts}" var="post">
			<div class="post" id="post${post.id}">
				<h3><a href="viewPost.jsp?post=${post.id}"><c:out value="${post.subject} "/></a></h3>
				<h4>Posted by <a href="viewUser.jsp?author=${post.userId}"><c:out value="${post.userName}" /></a> on ${post.posted}</h4>
				<p><c:out value="${post.body}" /></p>
				<a href="viewPost.jsp#comments?post=${post.id}">Comments ( ${post.numComments} )</a> | <a href="viewPost.jsp#addComment?post=${post.id}">Add Comment</a>
			</div>
			<br><br>
		</c:forEach>
	</div>
</body>
</html>