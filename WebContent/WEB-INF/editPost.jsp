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
	<div class="w3-content" style="max-width:1400px">
		<div class="w3-row">
			<span class="w3-padding-large w3-left"><a href="viewPost.jsp?post=${post.id}">&lt;&lt; Back</a></span>
		</div>
		<div class="w3-container w3-center w3-padding-8"><h2>Edit Post:</h2></div>
		<div class="w3-row">
			<div class="w3-col">
				<div class="w3-card-4 w3-margin w3-white">
					<div class="w3-container w3-padding-8">
						<form action="editPost.jsp" method="post">
							<div class="w3-row w3-container w3-padding-4">
								Subject: <input type="text" name="subject" value="${post.subject}" class="w3-border w3-padding" size=100>
							</div>
							<div class="w3-row w3-container w3-padding-4">
								<p>Body:</p>
								<textarea name="body" class="w3-border w3-padding" rows=20 cols=100><c:out value="${post.body}" /></textarea><br>
							</div>
							<div class="w3-row w3-container w3-padding-4">
								<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="submit" value="Submit">
								<input class="w3-btn w3-padding-large w3-white w3-border w3-hover-border-black" type="reset" value="Reset">
							</div>
							<input type="hidden" name="post" value="${post.id}">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>