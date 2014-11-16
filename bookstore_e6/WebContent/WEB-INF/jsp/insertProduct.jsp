<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="product" action="insertDetail" method="post">
		<jsp:include page="ProductPage.jsp" />
		<input type="submit" name="prod_confirmed" value="確定" />
		<input type="submit" name="prod_cancelled" value="取消" />
	</form:form>
</body>
</html>