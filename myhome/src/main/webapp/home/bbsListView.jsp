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
<h3 align="center">게시글 목록</h3>
<table border="1">
	<tr><td width="40">글번호</td><td width="300">글제목</td>
	<td width="80">작성자</td><td width="90">작성일</td></tr>
	<c:forEach var="cnt" items="${BBS_LIST }">
	<tr><td>${cnt.seqno }</td>
	<td><a href="../read/readDetail.html?SEQNO=${cnt.seqno }">${cnt.title }</a></td>
	<td>${cnt.id }</td><td>${cnt.bbs_date }</td>
	</tr>
	</c:forEach>
</table>
<c:forEach var="page" begin="1" end="${COUNT }">
<a href="../read/read.hrml?pageNo=${page }">${page }</a>
</c:forEach>
</body>
</html>