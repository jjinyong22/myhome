<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: 'HYHeadLine', 'HY�߰��', 'HYPos55t';
	font-size : 17px;
}
#join{
	padding-left : 30px;
	font-size : 12px;
}
#header {
	width:800px;	margin:0 auto; background:red;
}
#wrap {
	width:2519px; height:1200px; margin:a auto;
}
#aside {
	width:1000px; height:30px; float:left;
	background:pink;
}
#content{
	width:3000px; height:1400px; float:right;
	background-image:url(imgs/caca.jpg);
	background-size:800px 270px;
}
#bside {
	width:350px; height:25px; float:right;
	backgroud:pink;
}
</style>
</head>
<body>

<div id="header"><img alt="" src="imgs/logo.gif">
</div>
<c:choose>
		<c:when test="${sessionScope.LOGIN != null}">
			<a href="mainPage.jsp?BODY=mypage.jsp">���� ������</a>
		</c:when>
		<c:otherwise>
			<a href="mainPage.jsp?BODY=join.jsp">ȸ������</a>
		</c:otherwise>
	</c:choose>
<div id="wrap">
<div id="aside">
<a href="mainPage.jsp?BODY=front.jsp">����ȭ��</a>
<a href="mainPage.jsp?BODY=boardinsert.jsp">�Խñ۾���	</a>
<a href="Listboard">�Խ��Ǹ��	</a>
<a href="mainPage.jsp?BODY=iteminsert.jsp">��ǰ���	</a>
<a href="Listitem">��ǰ���	</a>
<a href="mainPage.jsp?BODY=imageInsert.jsp">�̰Խ���/���</a>
<a href="">�̰Խ��Ǹ��</a>
<div id="bside">
<c:choose>
			<c:when test="${sessionScope.LOGIN != null }">
			</c:when>
			<c:otherwise>
			</c:otherwise>
</c:choose>
</div>
</div>
<div id="content">
<c:choose>
			<c:when test="${param.BODY != null }">
			<jsp:include page="${param.BODY }"/>
			</c:when>
			<c:otherwise>
			<jsp:include page="front.jsp"/>
			</c:otherwise>
</c:choose>
</div>
</div>

</body>
</html>