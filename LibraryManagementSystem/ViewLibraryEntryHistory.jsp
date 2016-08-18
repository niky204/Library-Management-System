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

<c:forEach items="${entry}" var="lentry">
<p><b>ID:${lentry.id}</b></p>
<p><b>Name:${lentry.name}</b></p>
<br>
<c:choose>
<c:when test="${lentry.available==true}">

<a href="DispalyLibraryLog">Back to Items</a> | <a href="CheckOutLibraryEntry?id=${lentry.id}">Check Out</a>
</c:when>
<c:otherwise>

<a href="DispalyLibraryLog">Back to Items</a>
</c:otherwise>
</c:choose>

<table border='1'>
<tr>
<th>CIN</th><th>Name</th><th>Date Borrowed</th><th>Due Back by</th><th>Date Returned</th>
</tr>
<c:forEach items="${subentry}" var="sub">
			<c:choose>
			<c:when test="${not empty sub.datereturned}">
			<tr><td>${sub.cin}</td><td>${sub.nameofstudent}</td><td>${sub.dateborrowed}</td><td>${sub.duedate}</td><td>${sub.datereturned}</td></tr>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${not empty sub.duedate }">
						<tr><td>${sub.cin}</td><td>${sub.nameofstudent}</td><td>${sub.dateborrowed}</td><td bgcolor="red">${sub.duedate}</td><td><a href="ReturnLibraryEntry?subid=${sub.id}&libid=${lentry.id}">Return</a></td></tr>
					</c:when>
					<c:otherwise>
						<tr><td>${sub.cin}</td><td>${sub.nameofstudent}</td><td>${sub.dateborrowed}</td><td>${sub.duedate}</td><td><a href="ReturnLibraryEntry?subid=${sub.id}&libid=${lentry.id}">Return</a></td></tr>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
		
	
</c:forEach>
</table>
</c:forEach>
</body>
</html>