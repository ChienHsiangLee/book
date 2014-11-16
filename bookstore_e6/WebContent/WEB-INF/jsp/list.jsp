<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show product listing</title>
<link href="<c:url value="/resources/css/mystyle.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1>
		<spring:message code="page.title" />
	</h1>
	<h2>
		<spring:message code="listing.page.title" />
	</h2>
	<form:form action="/bookstore_e6/welcome" method="get">
		<input	type="submit" value="<spring:message code="button.back.caption"/>" />
	</form:form>
	<table class="frame">
		<tr>
			<th><spring:message code="caption.pno" /></th>
			<th><spring:message code="caption.pname" /></th>
			<th><spring:message code="caption.unitprice" /></th>
			<th><spring:message code="caption.category" /></th>
			<th><spring:message code="caption.modification" /></th>
			<th><spring:message code="caption.deletion" /></th>
		</tr>

		<c:forEach var="prod" items="${products}">
			<tr>
				<c:url var="thisURL" value="enquiry">
					<c:param name="p_category" value="${prod.category}" />
					<c:param name="p_pNo" value="${prod.pNo}" />
				</c:url>
				<td><a href="<c:out value="${thisURL}"/>">${prod.pNo}</a></td>
				<td>${prod.pName}</td>
				<td>${prod.unitPrice}</td>
				<td>${prod.category}</td>
				<c:url var="modificationURL" value="modification">
					<c:param name="p_category" value="${prod.category}" />
					<c:param name="p_pNo" value="${prod.pNo}" />
				</c:url>
				<td><a href="<c:out value="${modificationURL}"/>" ><spring:message
							code="modification.title" /></a></td>
				<td><a href="/bookstore/deletion"><spring:message
							code="deletion.title" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<form:form action="/bookstore_e6/welcome" method="get">
		 <input	type="submit" value="<spring:message code="button.back.caption"/>" />
	</form:form>
</body>
</html>