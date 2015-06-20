<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<a class="title" href="<c:url value='/'/>">Inventory</a>

<sec:authorize access="!isAuthenticated()">
	<a class="login" href="<c:url value='/login'/>">Log in</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<c:url var="url" value="/logout" />
	<sf:form id="logout" class="logout" action="${url}" method="POST">
		<input type="submit" name="submit" value="Logout" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</sf:form>
	<div class="username"><sec:authentication property="principal.username" /></div>
</sec:authorize>
