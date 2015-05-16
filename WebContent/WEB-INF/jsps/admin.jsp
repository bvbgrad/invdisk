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
	<table class="datatable">
		<caption>All users</caption>
		<tr>
			<th>Username</th>
			<th>Role</th>
			<th>Name</th>
			<th>Email</th>
			<th>Enabled</th>
		</tr>
		<c:forEach var="row" items="${users}">
			<tr>
				<td>${row.username}</td>
				<td>${row.authority}</td>
				<td>${row.name}</td>
				<td>${row.email}</td>
				<td>${row.enabled}</td>
			</tr>
		</c:forEach>
		</table>
				
	<p><a href="<c:url value="/"/>">Home page</a></p>
		
</body>
</html>