<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 


<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
		h1{ text-align: center; }
		
		.atag {
    		text-align: center;
		}
		
</style>
</head>
<body>
	<h1>${size}개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<div class="atag">
    		<a href="${contextPath}/wifi/home.do">홈 으로 가기</a>
	</div>

</body>
</html>