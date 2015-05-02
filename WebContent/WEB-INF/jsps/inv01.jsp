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
	
	<div class="admin_button"></div>
	<div class="profile logged_out"></div>

	<section>
	
		<p class="welcome">Welcome to the Inventory program.</p>
		
		<div class="data">
		<c:forEach var="row" items="${offers}">
			ID: ${row.id}<br/>
			Name: ${row.username}<br/>
			Text: ${row.text}<br/>
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
