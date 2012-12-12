!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script language="javascript" type="text/javascript" src="datetimepicker.js">
</script>
</head>

<body>
<form method="post" action="createuseraccount.htm">


  Enter a Username:<input name="username" type="text"/>
   <font color="red"></font>
  <br/>
  <br/>
  Password:<input name="password" type="text" />
   <font color="red"></font>
  <br/>
    <tr>
      <td height="49">Person</td>
      <td>
	  <select name="organ">
	  <option value="${UserAccountList.Person.firstname}" >${UserAccountList.Person.lastname}</option>
	  </select>
	  <font color="red"><form:errors path="org"/></font>
	  </td>
    </tr>
  <br/>
  <br/>
</p>
 
<input type="submit" value="submit"/>
</form>

</body>
</html>
