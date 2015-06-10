<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<h2>Create New Account</h2>

<c:url var="url" value="/createaccount" />	
<sf:form id="details" method="post" action="${url}"  commandName="user">

<table class="formtable">
<tr><td class="label">Username: </td><td><sf:input class="control" path="username" name="username" type="text" /><br/>
	<sf:errors path="username" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Name: </td><td><sf:input class="control" path="name" name="name" type="text" /><br/>
	<sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Email: </td><td><sf:input class="control"  path="email" name="email" type="text" /><br/>
	<sf:errors path="email" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Password: </td><td><sf:input id="password" class="control"  path="password" name="password" type="text" /><br/>
	<sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Confirm Password: </td><td><input id="confirmpass" class="control"  
	name="confirmpass" type="text" /><div id="matchpass"></div></td></tr>
<tr><td class="label"> </td><td><input class="control"  value="Create account" type="submit" /></td></tr>
</table>

</sf:form>

