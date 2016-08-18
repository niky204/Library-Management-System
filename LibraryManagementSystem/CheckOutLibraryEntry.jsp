<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="Logout">Logout</a>

<form action="CheckOutLibraryEntry" method="post">
<c:forEach items="${entry}" var="lentry">
<p><b>ID:${lentry.id}</b></p>
<p><b>Name:${lentry.name}</b></p>
<br>

<table border="1">
<tr>
	<td><label>Date Borrowed</label></td>
	<td><input type='hidden' name='dateborrowed' value='${dateborrowed}'/><label>${dateborrowed}</label></td>
</tr>
<tr>
	<td><label>Due Back by(optional):</label></td>
	<td><input type='text' name='duedate'/></td>
</tr>
<tr>
	<td>CIN</td>
	<td><input type="text" name="cin"/></td>
</tr>
<tr>
	<td>Name</td>
	<td><input type="text" name="nameofstudent"/></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" name="submit" value="CheckOut"/><input type="hidden" name="hidden" value="${lentry.id}"/></td>
</tr>




</table>

</c:forEach>

</form>

</body>
</html>