<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 


<%
	int idx=0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>


<style>
table.txc-table{
	width:100%;
	border-spacing: 0;
	
}
td.center{
	text-align: center;
}
td {
	border: 1px solid;
	height: 40px;
	
}

</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1><br>
	<a href="${contextPath}/wifi/home.do">홈</a> | <a href="${contextPath}/wifi/history.do">위치 히스토리 목록</a> | <a href="${contextPath}/wifi/openApi.do">Open API 와이파이 정보 가져오기</a><br><br>
	<form method="get" action="${contextPath}/wifi/nearWifi.do" >
	</form>
	<table class="txc-table" >
      <tr align="center" bgcolor="lightgreen">
         <td><b>ID</b></td>
         <td><b>X좌표</b></td>
         <td><b>Y좌표</b></td>
         <td><b>조회일자</b></td>
         <td><b>비고</b></td>        
   </tr> 

   <c:choose>
    <c:when test="${empty historyList}" >
      <tr border=1>
        <td colspan=5 class="center">
          <b>정보가 존재하지 않습니다.</b>
       </td>  
      </tr>
   </c:when>  
   <c:when test="${!empty historyList}" >
      <c:forEach  var="history" items="${historyList }" >
      	<%
      		if(idx % 2 == 0) {
      			idx++;
      	%>
      		<tr>
      	<%
      		} else {
      			idx++;
      	%>
      		<tr bgcolor="#e9ecef">
      	<%
      		}
      	%>
          <td>${history.id }</td>
          <td>${history.lnt }</td>
          <td>${history.lat }</td>
          <td>${history.search_date }</td>
          <td class="center">
          	<form method="get" action="${contextPath}/wifi/delete.do">
          		<input type="hidden" name="id" value="${history.id }" ><input type="submit" value="삭제">
          	</form>
          </td>
       </tr>
     </c:forEach>
</c:when>
</c:choose>
   </table>  
</body>
</html>