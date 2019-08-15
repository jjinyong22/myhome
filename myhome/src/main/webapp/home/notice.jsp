<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h3 align="center">공지</h3>
<table border="1">
	<tr><td width="40">글번호</td><td width="300">글제목</td>
	<td width="80">작성자</td><td width="150">작성일</td></tr>
	<c:forEach var="cnt" items="${NOTICE_LIST }">
	<tr><td>${cnt.code }</td>
	<td>${cnt.title }</td>
	<td>운영자</td><td>${cnt.notice_date }</td>
	</tr>
	</c:forEach>
</table>
<c:forEach var="page" begin="1" end="${COUNT }">
<a href="../read/read.hrml?pageNo=${page }">${page }</a>
</c:forEach>
<input type="button" value="공지등록" align="right"/>

</div>
</body>
</html>