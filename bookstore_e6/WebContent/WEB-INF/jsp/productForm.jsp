<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/mystyle.css" rel="stylesheet" type="text/css">
<title>Product Page</title>
<style>
.error {
color: #ff0000;
font-weight: bold;
}
</style>
</head>
<body>
	<form:form action="productForm" method="post" modelAttribute="product">
		<table>
			<tr class="subtitle">
				<td><spring:message code="productProperties.title" /></td>
			</tr>
			<tr>
				<td><spring:message code="caption.pno" />:</td>
				<td><form:input path="pNo" /></td>
				<td><form:errors path="pNo" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="caption.pname" />:</td>
				<td><form:input path="pName" /></td>
				<td><form:errors path="pName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="caption.unitprice" />:</td>
				<td><form:input path="unitPrice" /></td>
				<td><form:errors path="unitPrice" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="caption.category" />:</td>
				<td><select name="p_category">
						<option ${product.category == 'BOOK' ? 'selected' : ''}>Book</option>
						<option  ${product.category == 'CD' ? 'selected' : ''}>CD</option>
						<option  ${product.category == 'DVD' ? 'selected' : ''}>DVD</option>
					</select>
					</td>
			</tr>
		</table>
		<input type="submit" name="prod_confirmed" value="確定" />
		<input type="submit" name="prod_cancelled" value="取消" />
	</form:form>
</body>
</html>