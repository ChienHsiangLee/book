<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Main Page</title>
<link href="/css/mystyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>商品資料查詢與維護作業</h1>
<br/>
	<form:form method="post">
		<label>商品名稱：</label>
		<input type="text" name="q_pName" id="q_pName"/>
		<input type="submit" name="insertion" value="新增"/>
		<input type="submit" name="browsing" value="查詢" />
	</form:form>
</body>
</html>