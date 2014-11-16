<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Page</title>
<link href="css/mystyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td class="subtitle">書籍資料</td>
		</tr>
		<tr>
			<td>作者:</td>
			<td><form:input type="text" name="p_author" path="author" /></td>
		</tr>
		<tr>
			<td>ISBN:</td>
			<td><form:input type="text" name="p_isbn" path="ISBN" /></td>
		</tr>
		<tr>
			<td>版本:</td>
			<td><form:input type="text" name="p_edition" path="edition" /></td>
		</tr>
		<tr>
			<td>出版社:</td>
			<td><form:input type="text" name="p_publisher" path="publisher" />
			</td>
		</tr>
	</table>
</body>

</html>