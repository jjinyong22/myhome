<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
				prefix="c" %>    
				  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${param.RESULT == 'nologin' }">
		<h3 align="center">
		서비스를 이용하려면 로그인이 필요합니다.</h3>
		<form action="memberLogin" method="post">
			<label>로그인<span id="join">
			<a href="login.jsp?BODY=join.jsp">회원가입</a></span><br/>
			<input type="text" name="id" size="15"/>
			</label><br/>
			<label>암호<br/>
			<input type="password" name="pwd" size="15"/>
			</label><br/><br/>
			<input type="submit" value="로그인"/>
		</form>
	</c:when>
	
	<c:when test="${param.RESULT == 'nocart' }">
		<h3 align="center">
		장바구니에 담으려면 로그인이 필요합니다.</h3>
		<form action="cartLogin.do" method="post">
			<label>로그인<span id="join">
			<a href="login.jsp?BODY=join.jsp">회원가입</a></span><br/>
			<input type="text" name="id" size="15"/>
			</label><br/>
			<label>암호<br/>
			<input type="password" name="pwd" size="15"/>
			</label><br/><br/>
			<input type="submit" value="로그인"/>
		</form>
	</c:when>
	
	<c:otherwise>
<!-- 		<form action="memberLogin" method="post"> -->
<!-- 			<label>로그인<span id="join"> -->
<!-- 			<a href="login.jsp?BODY=join.jsp">회원가입</a></span><br/> -->
<!-- 			<input type="text" name="id" size="15"/> -->
<!-- 			</label><br/> -->
<!-- 			<label>암호<br/> -->
<!-- 			<input type="password" name="pwd" size="15"/> -->
<!-- 			</label><br/><br/> -->
<!-- 			<input type="submit" value="로그인"/> -->
<!-- 		</form> -->
		<form:form modelAttribute="user" action="../login/template.html" method="post">
		아이디 : <form:input path="id" size="12"/>
		<font color="red"><form:errors path="id"/></font><br/>
		패스워드 : <form:password path="password" size="12"/>
		<font color="red"><form:errors path="password"/></font><br/>
		<input type="submit" value="로그인"/>
		<input type="reset" value="취소"/>
		</form:form>
		<div align="right"><a href="../home/loginentry.html">가입하기</a></div>
	</c:otherwise>
</c:choose>
</body>
</html>





