<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>


<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath}/static/css/reset.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/static/css/sprites.css" rel="stylesheet">

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
      	<form name='f' action='${pageContext.request.contextPath}/logout' method='POST'>
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
	
			<br><p> All users </p>
			<c:forEach var="row" items="${users}">
				Username: ${row.username} Enabled: ${row.enabled}<br/>
				   Name: ${row.name} Email: ${row.email}<br/>
			</c:forEach>
		</div>
	
	</section>


	<footer>
        <p>Friendly-Tutor(TM) Inventory project: Contact
         <a href="mailto:xyx@friendly-tutor.org">xyz@friendly-tutor.org</a>
         for more information.
         <br>   Under Construction (as of <%= new java.util.Date() %>)
         </p>
         
 	</footer>

</body>
</html>
