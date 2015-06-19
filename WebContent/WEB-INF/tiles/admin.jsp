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
			<td><input type="button" value="Edit" onclick="userEdit(this)"></td>
			<td><input type="button" value="Delete" onclick="userDelete(this)"></td>
		</tr>
	</c:forEach>
</table>

<p>
	<a href="<c:url value="/"/>">Home page</a>
</p>

<sf:form id="sfForm" method="post">
	<input type="hidden" id="sfusername" name="userName">
</sf:form>

<c:url var="url" value="/" />
<script>
	function userDelete(x) {
		var nRow = x.parentNode.parentNode.rowIndex;
		var userName = document.getElementById("table").rows[nRow].cells[0].innerHTML;
		document.getElementById("sfForm").action = "${url}deleteaccount";
		document.getElementById("sfusername").value = userName;
		if (confirm('\nDelete account: "' + userName + '"\nAre you sure ?')) {
			document.getElementById("sfForm").submit();
		}
	}

	function userEdit(x) {
		var nRow = x.parentNode.parentNode.rowIndex;
		var userName = document.getElementById("table").rows[nRow].cells[0].innerHTML;
		document.getElementById("sfForm").action = "${url}editaccount";
		document.getElementById("sfusername").value = userName;
		document.getElementById("sfForm").submit();
	}
</script>

