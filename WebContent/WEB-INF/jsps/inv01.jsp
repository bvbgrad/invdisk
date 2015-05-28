<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<link href="<c:url value="/static/css/reset.css"/>" rel="stylesheet">
	<link href="<c:url value="/static/css/style.css"/>" rel="stylesheet">
	<link href="<c:url value="/static/css/sprites.css"/>" rel="stylesheet">
	
<!--	
	<link href="${pageContext.request.contextPath}/static/css/reset.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/static/css/sprites.css" rel="stylesheet">
-->

	<!--[if lt IE 9]>
	    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	    <script src="static/scripts/html5shiv-printshiv.js"></script>
	<![endif]-->                             
	
	<title>Inventory</title>
</head>

<body>
	<header>
		<h1>Friendly-Tutor(TM) Inventory Project</h1>
	</header>
	
	<div class="profile logged_in"></div>
	
	<div class="admin_button"></div>
	
	<div class="right">
      	${username}
      	<form name='f' action='c:url value="/logout"' method='POST'>
         	<input  class="right" name="submit" type="submit" value="Logout" />
         	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
   	</div>
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

	<footer>
        <p>Friendly-Tutor(TM) Inventory project: Contact
         <a href="mailto:xyx@friendly-tutor.org">xyz@friendly-tutor.org</a>
         for more information.
         <br>   Under Construction (as of <%= new java.util.Date() %>)
         </p>
         
 	</footer>

</body>
</html>
