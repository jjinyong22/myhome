<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">게시글 내용</h3>
[글번호] : ${BBS_ITEM.seqno }<br/>
[글제목] : ${BBS_ITEM.title }<br/>
[작성자] : ${BBS_ITEM.id },[작성일] : ${BBS_ITEM.bbs_date }<br/>
========================================================<br/>
${BBS_ITEM.content }
</body>
</html>