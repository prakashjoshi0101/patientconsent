<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Login secure website!!!</h1>

	<form method="POST" action="login.htm">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" size="15" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" size="15" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Login" /></td>
			</tr>

		</table>
	</form>

</body>
</html>
