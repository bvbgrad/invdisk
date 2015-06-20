<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Reset Password for account: ${user.username}</h2>

<c:url var="url" value="/updatepassword" />
<sf:form id="details" name="f" method="post" action="${url}" commandName="user" onsubmit="return false;">

	<table class="formtable">
		<tr>
			<td class="label">New Password:</td>
			<td><sf:input id="password" class="control" path="password"
					name="newpassword" type="text" /><br /> <sf:errors path="password"
					cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td><input id="confirmpass" class="control" name="confirmpass"
				type="text" />
			<div id="matchpass"></div></td>
		</tr>
		<tr>
			<td colspan="2"><button onclick="submitUpdate()">Reset password</button></td>
		</tr>
		<tr>
			<td colspan="2"><button onclick="cancel()">Cancel</button></td>
		</tr>
	</table>

	<sf:input type="hidden" path="username" name="username" />
	<sf:input type="hidden" path="name" name="name" />
	<sf:input type="hidden" path="email" name="mail" />
	<sf:input type="hidden" path="enabled" name="enabled" />
	<sf:input type="hidden" path="authority" name="authority" />

</sf:form>

<br/><br/>
<h3>New password will be effective on next login.</h3>

<script>
	$(document).ready(function() {
		document.f.password.value ="";
		document.f.password.focus();
	})

	function submitUpdate() {
		document.getElementById("details").submit();
	}
	
	function cancel() {
		window.history.back();
	}
	
</script>