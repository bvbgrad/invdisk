<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<section>
	
		<p class="welcome">Welcome to the Inventory program.</p>
		
		<div class="data">
			<c:forEach var="row" items="${offers}">
				Offer ID: ${row.id} from Name: ${row.username}<br/>
				   Text: ${row.text}<br/>
			</c:forEach>
		</div>
	
	</section>

	<sec:authorize access="hasRole('ADMIN')">
		<br/><p><a href="<c:url value="/admin"/>">User Accounts Administration</a></p>
	</sec:authorize>
