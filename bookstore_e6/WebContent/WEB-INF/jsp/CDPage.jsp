<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CD Page</title>
<link href="css/mystyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td class="subtitle">CD 資料</td>
		</tr>
		<tr>
			<td>作曲者:</td>
			<td><input type="text" name="p_composer" value="${product.composer}" /></td>
		</tr>
		<tr>
			<td>演唱者:</td>
			<td><input type="text" name="p_performer" value="${product.performer}" /></td>
		</tr>
		<tr>
			<td>片數:</td>
			<td><input type="text" name="p_number" value="${product.number}" /></td>
		</tr>
		<tr>
			<td>發行商:</td>
			<td><input type="text" name="p_publisher" value="${product.publisher}" />
			</td>
		</tr>
	</table>
</body>

</html>