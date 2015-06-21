<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url var="url" value="/updateaccount" />
<sf:form id="sfform" method="post" action="${url}" commandName="user" onsubmit="return false;">
	<table class="formtable">
		<caption>Edit "${user.username}" Account</caption>
		<tr>
			<td class="label">Name:</td>
			<td><sf:input class="control" path="name" name="name" type="text" /><br />
				<sf:errors path="name" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" path="email" name="email" type="text" /><br /> 
				<sf:errors path="email" cssClass="error"></sf:errors></td>
		</tr>
<sec:authorize access="hasRole('ADMIN')">
		<tr>
			<td class="label">Enabled:</td>
			<td><sf:input class="control" path="enabled" name="enabled" type="text" /><br /> 
				<sf:errors path="enabled" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label">Authority:</td>
			<td><sf:input class="control" path="authority" name="authority" type="text" /><br /> 
				<sf:errors path="name" cssClass="error"></sf:errors></td>
		</tr>
</sec:authorize>
		<tr>
			<td colspan="2"><button onclick="submitUpdate()">Update account information</button></td>
		</tr>
		<tr>
			<td colspan="2"><button onclick="cancel()">Cancel</button></td>
		</tr>
	</table>

	<sf:input type="hidden" path="username" name="username" />
	<sf:input type="hidden" path="password" name="password" />
<sec:authorize access="hasRole('USER')">
	<sf:input type="hidden" path="enabled" name="enabled" />
	<sf:input type="hidden" path="authority" name="authority" />
</sec:authorize>
</sf:form>

<script>
	function submitUpdate() {
		document.getElementById("sfform").submit();
	}
	
	function cancel() {
		window.history.back();
	}
</script>