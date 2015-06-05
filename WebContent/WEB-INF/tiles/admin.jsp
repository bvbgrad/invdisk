<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>


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
