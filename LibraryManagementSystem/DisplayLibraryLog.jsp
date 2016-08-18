<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   <%@ taglib prefix="cs320" uri="http://cs3.calstatela.edu/cs320stu54/Homework5" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${not empty login}">
	<a href="Login">Login</a>
	</c:when>
	<c:otherwise>
	<a href="Logout">Logout</a>
	</c:otherwise>
</c:choose>

<table border='1'>
<tr>
	<th><b>ID</b></th>
	<th><b>Type</b></th>
	<th><b>Name</b></th>
	<th><b>Additional Info</b></th>
	<th><b>Available</b></th>
	<th><b>Operation</b></th>
</tr>
<c:forEach items="${libentry}" var="entry">
 <tr>
   <td>${entry.id}</td>
   <td>${entry.type}</td>
   <td>${entry.name}</td>
   <td>${entry.additionalinfo}</td>
   
  
	<cs320:coloredTd available="${entry.available}" overdue="${entry.overdue}"/>
	
		
  <td><a href="ViewLibraryEntryHistory?id=${entry.id}">View</a>|<a href="EditLibraryEntry?id=${entry.id}">Edit</a></td>
  </tr>
</c:forEach>


</table>
<a href="AddLibraryEntry">Add Items</a>



</body>
</html>