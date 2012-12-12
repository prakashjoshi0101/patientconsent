<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.meena.spring.pojo.Organization" %>

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<center>
<h2> Create Person :</h2>
<form:form method="POST" action="createpersom.htm" commandName="Person" >
  <table width="653" height="425" border="0">
    <tr>
      <td width="318">Firstname</td>
      <td width="319"><label for="textfield"></label>
      <form:input path="firstname" />
      <font color="red"><form:errors path="firstname"/></font>
      </td>
    </tr>
    <tr>
      <td>Lastname</td>
      <td><form:input path="lastname" />
      <font color="red"><form:errors path="lastname"/></font>
      </td>
    </tr>
    <tr>
      <td>DoB</td>
      <td><form:input path="dob" />
      <font color="red"><form:errors path="dob"/></font>
      </td>
    </tr>
    <tr>
      <td>Role</td>
      <td><form:input path="role" />
      <font color="red"><form:errors path="role"/></font>
      </td>
    </tr>
    <tr>
      <td>Email</td>
      <td><form:input path="email" />
      <font color="red"><form:errors path="email"/></font>
      </td>
    </tr>
  </table>
  
   <p>
    <input type="submit" name="button" id="button" value="Create" />
  </p>
  <p><br/>
  </p>  
</form:form>
<p>&nbsp;</p>
</center>
</body>
</html>