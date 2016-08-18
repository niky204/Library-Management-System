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
<c:choose>
	<c:when test="${not empty logout}">
	<a href="Logout">Logout</a>
	</c:when>
</c:choose>

<form action="EditLibraryEntry?id=${libentry.id}" method="post">
<table>


<tr><td>Type</td>


	
	<td><select name='type' >
	<c:forEach items="${typelist}" var="t">
		<c:if test="${t==libentry.type}">
		<option selected value="${t}">${t}</option>
		</c:if>
		<c:if test="${t!=libentry.type}">
		<option value="${t}">${t}</option>
		</c:if>
	</c:forEach>
	</select>
	</td>
	
	

</tr>

	

<tr><td>Name:</td><td> <input type='text' name='name' value='${libentry.name}' /> </td></tr><br/>
<tr><td>Additional Info:</td><td><input type='text' name='additionalinfo' value='${libentry.additionalinfo}'/></td></tr>
<br/>
<tr><td><input type='submit' name='edit' value='Edit' /></td></tr> <br />
</table>
</form>


</body>
</html>