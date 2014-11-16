<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Enquiry</title>
<style>
h1 {
	color: blue;
	font-size: 2em;
	text-align: center;
}

h2 {
	color: blue;
	font-size: 1.5em;
	text-align: center;
}

p {
	color: red;
}

form {
	text-align: center;
}

table {
	margin: 20px auto;
	border: 2px solid black;
	text-align: center;
}

td,th {
	padding: 10px;
	border: 1px solid black;
	text-align: center;
}

.headrow {
	color: black;
	background-color: #0099CC;
	text-align: center;
}

.evenrow {
	color: black;
	background-color: #00FFCC;
}

td.subtitle {
	color: #660033;
	font-size: 20px;
}
</style>
</head>
<body>
	<h1>
		<spring:message code="page.title" />
	</h1>
	<h2>
		<spring:message code="enquiry.head.title" />
	</h2>
	<form:form name="form_detail" action="browse" modelAttribute="product"
		method="POST">
		<jsp:include page="ProductPage.jsp" />
		<jsp:include page="${p_targetView}" />
		<input type="submit" name="confirmed" value="確定" />
	</form:form>
</body>
</html>