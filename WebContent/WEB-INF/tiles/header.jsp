<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<a class="title" href="<c:url value='/'/>">Inventory</a>

<sec:authorize access="!isAuthenticated()">
	<a class="login" href="<c:url value='/login'/>">Log in</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a class="login" href="<c:url value='/logout'/>">Log Out</a>
</sec:authorize>
