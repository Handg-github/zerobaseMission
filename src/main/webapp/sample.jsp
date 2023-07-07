<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<c:set var="lat1" value="${requestScope.lat }"  /> 
<c:set var="lnt1" value="${requestScope.lnt }"  /> 

<%
	
	int idx = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>

<script >

function input_Text(){
    document.getElementById("lat").value = '37.5544069';
    document.getElementById("lnt").value = '126.8998666';
}

</script>


<style>
table.txc-table{
	width:100%;
	border-spacing: 0;
	
}
td.empty{
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
	LAT: <input type="text" id="lat" value="${lat1 }" name="lat">, LNT: <input type="text" id="lnt" value="${lnt1 }" name="lnt">
	<input type="button" onclick="input_Text()" value="내 위치 가져오기"> <input type="submit" value="근처 WIFI 정보 보기"><br>
	</form>
	<table class="txc-table" >
      <tr align="center" bgcolor="lightgreen">
         <td><b>거리(Km)</b></td>
         <td><b>관리번호</b></td>
         <td><b>자치구</b></td>
         <td><b>와이파이명</b></td>
         <td><b>도로명주소</b></td>
         <td><b>상세주소</b></td>
		 <td><b>설치위치(층)</b></td>
		 <td><b>설치유형</b></td>
		 <td><b>설치기관</b></td>
		 <td><b>서비스구분</b></td>
		 <td><b>망종류</b></td>
		 <td><b>설치년도</b></td>
		 <td><b>실내외구분</b></td>
		 <td><b>WIFI접속환경</b></td>
		 <td><b>X좌표</b></td>
		 <td><b>Y좌표</b></td>
		 <td><b>작업일자</b></td>
         
   </tr> 

   <c:choose>
    <c:when test="${empty  wifiList}" >
      <tr border=1>
        <td colspan=17 class="empty">
          <b>위치 정보를 입력한 후에 조회해 주세요.</b>
       </td>  
      </tr>
   </c:when>  
   <c:when test="${!empty wifiList}" >
      <c:forEach  var="wifi" items="${wifiList }" >
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
          <td>${wifi.distance }</td>
          <td>${wifi.x_swifi_mgr_no }</td>
          <td>${wifi.x_swifi_wrdofc}</td>     
          <td>${wifi.x_swifi_main_nm }</td>     
          <td>${wifi.x_swifi_adres1}</td>   
          <td>${wifi.x_swifi_adres2}</td>
          <td>${wifi.x_swifi_instl_floor}</td>
          <td>${wifi.x_swifi_instl_ty}</td>
          <td>${wifi.x_swifi_instl_mby}</td>
          <td>${wifi.x_swifi_svc_se}</td>
          <td>${wifi.x_swifi_cmcwr}</td>
          <td>${wifi.x_swifi_cnstc_year}</td>
          <td>${wifi.x_swifi_inout_door}</td>
          <td>${wifi.x_swifi_remars3}</td>
          <td>${wifi.lnt}</td>
          <td>${wifi.lat}</td>
          <td>${wifi.work_dttm}</td>   
       </tr>
     </c:forEach>
</c:when>
</c:choose>
   </table>  
</body>
</html>