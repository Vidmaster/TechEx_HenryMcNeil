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
	<div class="w3-content" style="max-width:1400px">
		<div class="w3-row">
			<span class="w3-padding-large w3-left"><a href="index.jsp">&lt;&lt; Home</a></span>
		</div>
		<div class="w3-margin-top w3-margin-bottom">
		    <div class="w3-container w3-white w3-padding-32">
				<h2><c:out value="${user.name}" /></h2>
				<p><c:out value="${user.description}" /></p>
			</div>
		</div>
		<div class="w3-row">
			<div class="w3-container w3-padding-8">
				<h2>Recent Posts:</h2>
				<c:if test="${posts.isEmpty()}">
					<p>No posts by this user.</p>
				</c:if>
				<c:forEach items="${posts}" var="post">
					<div class="w3-card-4 w3-margin w3-white" id="post${post.id}">
						<div class="w3-container w3-padding-8">
							<h3><a href="viewPost.jsp?post=${post.id}"><c:out value="${post.subject} "/></a></h3>
							<h5>Posted by <a href="viewUser.jsp?author=${post.userId}"><c:out value="${post.userName}" /></a> on ${post.posted}</h5>
							<p><c:out value="${post.body}" /></p>
							<div class="w3-row">
								<span class="w3-padding-large w3-left"><a href="viewPost.jsp?post=${post.id}#addComment">Add Comment</a></span>
								<span class="w3-padding-large w3-right">Comments <span class="w3-tag">${post.numComments}</span></span>
							</div>
						</div>
					</div>	
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>