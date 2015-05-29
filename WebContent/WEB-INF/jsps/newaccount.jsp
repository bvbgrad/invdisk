<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>

	<link href='<c:url value="/static/css/reset.css"/>' rel="stylesheet">
	<link href='<c:url value="/static/css/style.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/static/css/sprites.css"/>' rel="stylesheet">
	
	<c:url var="url" value="/static/scripts/jquery.js" />	
	<script src="${url}" type="text/javascript"></script>

<script type="text/javascript">
function onLoad() {
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	
	$("#details").submit(canSubmit);
}

function canSubmit() {
	var password = $("#password").val();
	var confirmpass = $("#confirmpass").val();

	if(password != confirmpass) {
	 	alert("<fmt:message key='UnmatchedPasswords.user.password'/> \n" +
	 			password + " : " + confirmpass)
		return false;
	}
	else {
		return true;
	}
}

function checkPasswordsMatch() {
	var password = $("#password").val();
	var confirmpass = $("#confirmpass").val();
	
	if(password.length > 3 || confirmpass.length > 3) {
		
		if(password == confirmpass) {
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'/>");
			$("#matchpass").addClass("valid");
			$("#matchpass").removeClass("error");
			
		}
		else {
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}
		
	}
}


$(document).ready(onLoad);

</script>

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>New Account</title>
</head>
<body>

<c:url var="url" value="/createaccount" />	
<sf:form id="details" method="post" action="${url}"  commandName="user">

<table class="formtable">
<tr><td class="label">Username: </td><td><sf:input class="control" path="username" name="username" type="text" /><br/>
	<sf:errors path="username" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Name: </td><td><sf:input class="control" path="name" name="name" type="text" /><br/>
	<sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Email: </td><td><sf:input class="control"  path="email" name="email" type="text" /><br/>
	<sf:errors path="email" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Password: </td><td><sf:input id="password" class="control"  path="password" name="password" type="text" /><br/>
	<sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Confirm Password: </td><td><input id="confirmpass" class="control"  
	name="confirmpass" type="text" /><div id="matchpass"></div></td></tr>
<tr><td class="label"> </td><td><input class="control"  value="Create account" type="submit" /></td></tr>
</table>

</sf:form>

</body>
</html>