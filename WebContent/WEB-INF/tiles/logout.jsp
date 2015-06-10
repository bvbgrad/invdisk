<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="!isAuthenticated()">
	<p class="welcome">You are logged out. </p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<c:url var="url" value="/logout" />
	<form name='f' action="${url}" method='POST'>
		<table class="logoutform">
			<tr>
				<td><input name="submit" type="submit" value="Confirm Logout" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</sec:authorize>

