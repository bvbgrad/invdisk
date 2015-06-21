<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('USER')">
	<div class="right">
		<c:url var="url" value="/editaccount" />
		<form action="${url}" method="post">
			<input type="hidden" id="sfusername" name="userName" value="<sec:authentication property='principal.username' />">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Edit profile" name="submit" />
		</form>
		<c:url var="url" value="/resetpassword" />
		<form action="${url}" method="post">
			<input type="hidden" id="sfusername" name="userName" value="<sec:authentication property='principal.username' />">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Reset Password" name="submit" />
		</form>
	</div>
</sec:authorize>

<p class="welcome">Welcome to the Inventory program.</p>

	
<sec:authorize access="hasRole('USER')">
	<br />
	<p><a href="<c:url value="/showoffers"/>">Show offers</a></p>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
	<br />
	<p><a href="<c:url value="/showoffers"/>">Show offers</a></p>
	<br />
	<p><a href="<c:url value="/admin"/>">User Accounts Administration</a></p>
</sec:authorize>
