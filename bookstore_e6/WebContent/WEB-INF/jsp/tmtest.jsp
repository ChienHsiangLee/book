<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<h1>商品資料新增作業</h1>
<br/>
	<form action="tmtest" method="post">
		<label>商品一編號：</label>
		<input type="text" name="p_pNo1" value="A0001" /><br>
		<label>商品二編號：</label>
		<input type="text" name="p_pNo2" value="A0002"/><br>		
		<input type="submit" name="insertion" value="新增"/>
		</form>
</body>
</html>