<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" 
	prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" 
	prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>사용자 등록 화면</title>
</head>
<body>
<script type="text/javascript">
function idCheck(){
	if(document.frm.id.value == ""){
		alert("ID를 입력하세요.");
		document.frm.id.focus();
		return;
	}
	var url="../idcheck/idcheck.html?ID="+document.frm.id.value;
	window.open(url,"_blank","width=450,height=200");
}
function validate(form){
	if(form.name.value == ""){
		alert("이름을 입력하세요."); form.name.focus(); return false;
	}
	if(form.id.value == ""){
		alert("ID를 입력하세요."); form.id.focus(); return false;
	}
	if(form.password.value == ""){
		alert("암호를 입력하세요."); form.password.focus(); return false;
	}
	if(form.password.value != form.CONFIRM.value){
		alert("암호가 일치하지 않습니다.");
		form.password.focus(); return false;
	}
	if( !form.gender[0].checked && !form.gender[1].checked){
		alert("성별을 선택하세요."); form.gender[0].focus(); return false;
	}
	if(form.job.selectedIndex < 1){
		alert("직업을 선택하세요."); form.job.focus(); return false;
	}
	if(form.idChecked.value == ""){
		alert("ID 중복 검사를 해주세요."); return false;
	}
	if(confirm("입력한 내용이 맞습니까?")){
		
	}else { return false; }
}
</script>
<h2>사용자 등록 화면</h2>
<div align="left" class="body">
<form:form name="frm" modelAttribute="user" method="post" 
	action="../entry/entry.html" onSubmit="return validate(this)">
<input type="hidden" name="idChecked" id="idChecked"/>
이름	<form:input path="name" maxLength="20"
		cssClass="name"/><br/>
아이디 <form:input path="id" maxLength="20"
		cssClass="id"/>
<input type="button" value="중복검사" onClick="idCheck()"/>		
<br/>
패스워드 <form:password path="password" maxLength="20"
		cssClass="password"/><br/>
패스워드 확인 <input type="password" name="CONFIRM"/><br/>
성별 : 남 <form:radiobutton path="gender" value="M"/>
여 <form:radiobutton path="gender" value="F"/><br/><br/>
E-MAIL <form:input path="email" maxLength="50"
			cssClass="email"/><br/>
직업 : <form:select path="job">
<form:option value="--선택하세요--"/><form:option value="회사원"/>
<form:option value="학생"/><form:option value="기타"/>
</form:select><br/><br/>
<input type="submit" value="등록" />
<input type="reset" value="리셋" />
</form:form>
</div>
</body>
</html>










