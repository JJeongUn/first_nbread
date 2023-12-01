<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	Member member = (Member)request.getAttribute("member");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nbread</title>
</head>
<header>
<%@ include file="../common/header.jsp" %>
</header>
<br>
<br>
<h1> 관심사 편집 페이지 </h1>



 <footer>
<%@ include file="../common/footer.jsp" %>
</footer>
</body>
</html>