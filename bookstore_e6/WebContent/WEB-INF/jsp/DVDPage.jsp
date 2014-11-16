<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DVD Page</title>
<link href="css/mystyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td class="subtitle">DVD 資料</td>
		</tr>
		<tr>
			<td>導演:</td>
			<td><input type="text" name="p_director" value="${product.director}" /></td>
		</tr>
		<tr>
			<td>主演員一:</td>
			<td><input type="text" name="p_actor1" value="${product.actor1}" /></td>
		</tr>
		<tr>
			<td>主演員二:</td>
			<td><input type="text" name="p_actor2" value="${product.actor2}" /></td>
		</tr>
		<tr>
			<td>分級：</td>
			<td><select name="p_rating">
					<option value="普遍級" ${p_rating == '普遍級' ? 'selected' : ''}>普遍級</option>
					<option value="保護級" ${p_rating == '保護級' ? 'selected' : ''}>保護級</option>
					<option value="輔導級" ${p_rating == '輔導級' ? 'selected' : ''}>輔導級</option>
					<option value="限制級" ${p_rating == '限制級' ? 'selected' : ''}>限制級</option>
			</select></td>
		</tr>
		<tr>
			<td>發行商:</td>
			<td><input type="text" name="p_publisher" value="${p_publisher}" /></td>
		</tr>
	</table>
</body>

</html>