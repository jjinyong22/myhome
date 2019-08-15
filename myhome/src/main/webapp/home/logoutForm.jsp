<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="../logout/template.html" method="get">
안녕하세요? ${sessionScope.loginUser }님~<br/>
<input type="submit" value="로그아웃"/>
</form>
</body>
</html>










