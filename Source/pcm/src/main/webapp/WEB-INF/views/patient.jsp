<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>

<body>
	<h1>Patient Work Area</h1>

	<form >
		<table>
			<tr>
				
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Create Consent" method="POST" action="createconsent.htm"/></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="View ConsentRequest" method="POST" action="viewconsent.htm"/></td>
			</tr>

		</table>
	</form>

</body>
</html>