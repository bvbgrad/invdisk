<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<title>Login Page</title>
	<link href='<c:url value="/static/css/style.css"/>' rel="stylesheet">
</head>
<body onload='document.f.username.focus();'>
	<h3>Login with Username and Password</h3>
	
	<c:if test="${param.error != null}">
		<p class="loginerror"> Check that your username and password are correct. </p>
	</c:if>
	
	<c:url var="url" value="/login" />	
	<form name='f' action="${url}" method='POST'>
		<table class="formtable">
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
	<p><a href='<c:url value="/newaccount"/>'>Create new account</a></p>
</body>
</html>