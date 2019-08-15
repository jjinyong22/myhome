<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3 align="center" width="80%">상품 목록</h3>
<table border="1" width="80%">
	<tr><td width="40">상품번호</td><td width="200">상품이름</td>
	<td width="80">상품가격</td><td width="100">장바구니</td></tr>
	<c:forEach var="item" items="${ITEM_LIST }">
	<tr><td>${item.code }</td><td>${item.name }</td>
	<td>${item.price }</td>
	<td><a href="#" onClick=
"window.open('../cart/addCart.html?CODE=${item.code }','cart_result','width=400,height=300').focus()">장바구니 담기</a></td>
	</c:forEach>
</table>
<c:forEach var="page" begin="1" end="${COUNT }">
<a href="../read/product.html">${page }</a>
</c:forEach>
</body>
</html>






