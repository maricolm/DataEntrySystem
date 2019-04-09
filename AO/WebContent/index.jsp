<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
测试页面：
<a href="${pageContext.request.contextPath}/col/export.do"> 下载</a>

<form action="${pageContext.request.contextPath}/col/upload.do" method="post" enctype="multipart/form-data">
	<input name="file" type="file">
	<input type="submit" value="tj">
</form>
</body>
</html>