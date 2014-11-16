<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Product Insertion</title>
		<link href="css/mystyle.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1><spring:message code="page.title" /></h1>
		<h2><spring:message code="insertion.head.title" /></h2>
		<form:form modelAttribute="product" action="backendJob/insertion" method="POST">	
			<jsp:include page="ProductPage.jsp" />
			<jsp:include page="${p_targetView}" />
			<input type="submit" name="confirmed" value="確定" />
			<input type="submit" name="cancelled" value="取消" />			
		</form:form>
	</body>
</html>