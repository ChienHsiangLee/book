<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/mystyle.css" rel="stylesheet" type="text/css">
		<title>Product Page</title>
	</head>
	<body>
		<table>
			<tr class="subtitle"><td>商品基本資料</td></tr>
			<tr>
				<td>商品編號:</td>
				<td><form:input type="text" name="p_pno" path="pNo"/></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><form:input type="text" name="p_pname" path="pName" /></td>
			</tr>
			<tr>
				<td>商品定價:</td>
				<td><form:input type="text" name="p_unitprice" path="unitPrice" /></td>
			</tr>	
			<tr>
				<td>商品類別:</td>
				<td>
              		<form:select name="p_category" path="category">
                		<option value="Book" ${product.category == 'Book' ? 'selected' : ''}>Book</option>
                		<option value="CD" ${product.category == 'CD' ? 'selected' : ''}>CD</option>
                		<option value="DVD" ${product.category == 'DVD' ? 'selected' : ''}>DVD</option>
              		</form:select>
              	</td>
			</tr>
		</table>
	</body>
</html>