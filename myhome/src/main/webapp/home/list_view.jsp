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
<c:if test="${empty LIST}">
등록된게시글이 없습니다.
</c:if>

<c:if test="${ ! empty LIST}">
<table width="100%">
	<tr><td align="right"><b>${startRow }~${endRow }/${count }</b></td></tr>
</table>
<table border="1" width="100%">
	<tr><td>이미지</td><td>글제목</td><td>작성자</td><td>작성일</td></tr>
	<c:forEach var="w" items="${LIST }">
	<tr><td><img alt=""
	src="${pageContext.request.contextPath }/upload/${w.image_name }"
		width="50" height="50"/></td>
		<td><a href="javascript:goView(${w.writing_id })">${w.title }</a></td><td>${w.writer_name }</td>
		<td>${w.register_date }</td></tr>
		</c:forEach>
</table>
</c:if>
<c:set var="startPage" value=
"${currentPage-(currentPage % 10 == 0 ? 10:(currentPage%10))+1}"/>
<c:set var="endPage" value="${startpage + 9 }"/>
<c:if test="${endPage > pageCount }">
	<c:set var="endPage" value="${pageCount }"/>
</c:if>
<c:if test="${startPage > 10 }">
	<a href="javascript:goPage(${startPage - 1 })">[이전]</a>
</c:if>
<c:forEach var="pageNo" begin="${startpage }" end="${endPage }">
	<c:if test="${currentPage == pageNo }"><font size="5"></c:if>
	<a href="javascript:goPage(${pageNo })">${pageNo }</a>
	<c:if test="${currentPage == pageNo }"></font></c:if>
</c:forEach>
<c:if test="${endPage < pageCount }">
	<a href="javascript:goPage(${endPage + 1 })">[다음]</a>
	</c:if>
	<form name="move" method="post">
	<input type="hidden" name="PAGE_NUM" value="${currentPage }"/>
	<input type ="hidden" name="id"/>
	</form>
	
<script type="text/javascript">
	function goView(id){
		document.move.id.value = id;
		document.move.action="../read/readImage.html";
		document.move.submit();
	}
	

	function goPage(page){
		document.move.action = "../write/writeList.html";
		document.move.PAGE_NUM.value = page;
		document.move.submit();
		
	}

</script>
</body>
</html>







