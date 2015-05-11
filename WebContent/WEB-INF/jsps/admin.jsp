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
	
	<title>Admin</title>
</head>

<body>
		<div class="data">
			<p> All users </p>
			<c:forEach var="row" items="${users}">
				Username: ${row.username} Role: ${row.authority}<br/>
				   Name: ${row.name} Email: ${row.email} Enabled: ${row.enabled}<br/><br/>
			</c:forEach>
		</div>
		
			<p><a href="<c:url value="/"/>">Home page</a></p>
		
</body>
</html>