<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<section>
	
		<p class="welcome">Show available offers.</p>
		
		<div class="data">
			<c:forEach var="row" items="${offers}">
				Offer ID: ${row.id} from Name: ${row.username}<br/>
				   Text: ${row.text}<br/>
			</c:forEach>
		</div>
	
	</section>
