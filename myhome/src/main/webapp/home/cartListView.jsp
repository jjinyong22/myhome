<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
		uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h4 align="center">장바구니 관리</h4>
<c:choose>
	<c:when test="${CART_LIST == null || SIZE == 'NO'}">
	장바구니가 비었습니다.
	</c:when>
	<c:otherwise>
		<table border="1">
			<tr><td width="70">상품코드</td><td width="250">상품이름</td>
			<td width="80">상품가격</td><td width="50">상품갯수</td>
			<td width="100">금액</td><td width="80">수정/삭제</td></tr>
			<c:forEach var="cnt" items="${CART_LIST }">
			<form action="../cart/modify.html" method="post">
			<input type="hidden" name="CODE" value="${cnt.code }"/>
			<tr><td>${cnt.code }</td><td>${cnt.name }</td>
			<td><fmt:formatNumber groupingUsed="true">${cnt.price }
			</fmt:formatNumber></td>
			<td><input type="text" size="3" name="NUMBER"
				value="${cnt.num }"/></td>
			<td><fmt:formatNumber groupingUsed="true">
			${cnt.price  * cnt.num}</fmt:formatNumber></td>
			<td><input type="submit" name="BTN" value="수정"/>
			<input type="submit" name="BTN" value="삭제"/></td>
			</tr>
			</form>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
<form action="../checkout/checkout.html" method="post">
총 합 : <fmt:formatNumber groupingUsed="true">
${totalAmount }</fmt:formatNumber>원<br/>
<input type="hidden" name="TOTAL" value=""/>
<input type="submit" value="결제하기"/>
</form>
</body>
</html>










