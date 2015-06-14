<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table class="datatable" id="table">
	<caption>All users</caption>
	<tr>
		<th>Username</th>
		<th>Role</th>
		<th>Name</th>
		<th>Email</th>
		<th>Enabled</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="row" items="${users}">
		<tr>
			<td>${row.username}</td>
			<td>${row.authority}</td>
			<td>${row.name}</td>
			<td>${row.email}</td>
			<td>${row.enabled}</td>
			<td><a href="<c:url value="/logout"/>">Edit</a></td>
			<td><input type="button" value="Delete" onclick="userDelete(this)"></td>
		</tr>
	</c:forEach>
</table>

<p>
	<a href="<c:url value="/"/>">Home page</a>
</p>

<c:url var="url" value="/deleteaccount" />
<sf:form id="sfDeleteAccount" method="post" action="${url}">
	<input type="hidden" id="deleteUser" name="userName">
</sf:form>


<script>
	function userDelete(x) {
		var nRow = x.parentNode.parentNode.rowIndex;
		var userName = document.getElementById("table").rows[nRow].cells[0].innerHTML;
		if (confirm('Delete account: "' + userName + '"\nAre you sure ?')) {
			document.getElementById("deleteUser").value = userName;
			document.getElementById("sfDeleteAccount").submit();
		}
	}
</script>

