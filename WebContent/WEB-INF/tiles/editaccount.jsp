<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Edit Account: ${user.username}</h2>
${user.password}

<c:url var="url" value="/updateaccount" />
<sf:form id="details" method="post" action="${url}" commandName="user" validate="none">
	<table class="formtable">
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
		<tr>
			<td><button onclick="goBack()">Cancel</button></td>
			<td><input class="control" value="Update account information"
				type="submit" /></td>
		</tr>
	</table>

	<sf:input type="hidden" path="username" name="username" />
	<sf:input type="hidden" path="password" name="password" validate="none" />
</sf:form>

<script>
	function goBack() {
		window.history.back();
	}
</script>