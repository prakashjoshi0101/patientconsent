<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Finally ${username} ${password} are here!!!</h1>
	<h4>sessionId: ${cookie.sessionId.value}</h4>
	<h4>stepId: ${cookie.stepId.value}</h4>

	<form method="GET" action="hello.htm">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" size="15" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Call Hello Service!" /></td>
			</tr>
		</table>
	</form>

	<h1>${hello}</h1>

</body>
</html>