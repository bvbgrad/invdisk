<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<section>
		<p class="welcome">Welcome to the Inventory program.</p>
	</section>

	<sec:authorize access="hasRole('USER')">
		<br/><p><a href="<c:url value="/showoffers"/>">Show offers</a></p>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<br/><p><a href="<c:url value="/showoffers"/>">Show offers</a></p>
		<br/><p><a href="<c:url value="/admin"/>">User Accounts Administration</a></p>
	</sec:authorize>
	