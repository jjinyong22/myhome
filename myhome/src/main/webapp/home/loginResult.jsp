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
<c:choose>
	<c:when test="${sessionScope.loginUser != null }">
	<h3 align="center">�α��� �Ǿ����ϴ�.
	�ȳ� ${sessionScope.loginUser }��</h3></c:when>
	<c:otherwise>
		<h3 align="center">Ʋ�� �Ƶ��ȣ Ȯ�ο��.</h3>
		<c:if test="${cartlogin == 'YES' }">
			<a href="../cart/login.html">- �ٽ� �α��� �ϱ�</a>
			</c:if>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${cartlogin == 'SUCCESS' }">
		<a href="#"
		onClick="self.close();opener.window.location.reload();">�ݱ�</a>
	</c:when>
	</c:choose>


</body>
</html>