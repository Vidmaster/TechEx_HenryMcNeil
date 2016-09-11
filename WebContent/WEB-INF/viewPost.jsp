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
	<div class="w3-content" style="max-width:1400px">
		<c:if test="${!message.isEmpty()}">
			${message}
		</c:if>
		<div class="w3-row">
			<span class="w3-padding-large w3-left"><a href="index.jsp">&lt;&lt; Home</a></span>
		</div>
		<div class="w3-row">
			<div class="w3-card-4 w3-margin w3-white" id="post${post.id}">
				<div class="w3-container w3-padding-8">
					<h3><c:out value="${post.subject}" /></h3>
					<h5>Posted by <a href="viewUser.jsp?author=${post.userId}"><c:out value="${post.userName}" /></a> on ${post.posted}<c:if test="${post.updated != null}">, updated on ${post.updated}</c:if></h5>
					
					<p><c:out value="${post.body}" /></p>
					<div class="w3-row"><a href="editPost.jsp?post=${post.id}">Edit post</a></div>
				</div>
			</div>
		</div>
		
		<c:if test="${!comments.isEmpty()}">
			<div class="w3-row">
				<div class="w3-container w3-padding-8">
				<h4>Comments:</h4>
					<c:forEach items="${comments}" var="comment">
						<div class="w3-card-4 w3-margin w3-white" id="comment${comment.id}">
							<div class="w3-container w3-padding-2">
							<h5><c:out value="${comment.subject}" /></h5>
							Posted by <c:out value="${comment.authorName}" /> at ${comment.posted}
							<p><c:out value="${comment.text}" /></p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<div class="w3-container w3-padding-8" id="addComment">
			<h4>Add New Comment</h4>
			<form action="AddCommentServlet" method="post">
				<div class="w3-container w3-padding-4">
					Subject: <input type="text" name="subject" size=80 maxlength=240 class="w3-border w3-padding"><br>
				</div>
				<div class="w3-container w3-padding-8">
					Author: <input type="text" name="author_name" size=60 maxlength=60 class="w3-border w3-padding"><br>
				</div>
				<div class="w3-container w3-padding-8">
					Comment:<br>
					<textarea name="text" cols=100 rows=4 class="w3-border w3-padding"></textarea>
				</div>
				<input type="hidden" name="post" value="${post.id}">
				<div class="w3-row w3-container w3-padding-4">
					<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="submit" value="Submit">
					<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="reset" value="Reset">
				</div>
			</form>
		</div>
	</div>
</body>
</html>