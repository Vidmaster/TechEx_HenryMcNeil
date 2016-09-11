<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New User</title>
</head>
<body>
<h2>Create a new blog user:</h2>
<a href="javascript:history.back()">&lt;&lt; Back</a>
<br><br>
<form action="UserServlet" method="post">

User Name: <input type="text" name="name" size=80 autofocus>
<br><br>
Biography: <br>
<textarea cols=100 rows=8 name="description"></textarea>
<br>
<input type="submit" value="Submit">
<input type="reset" value="Clear">
</form>
</body>
</html>