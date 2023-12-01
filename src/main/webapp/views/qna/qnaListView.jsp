<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, qna.model.vo.Qna, java.sql.Date" %>    
<%
	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
	int nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comi</title>
<script type="text/javascript">
function showWriteForm(){
	location.href = "/comi/views/qna/qnaWriteForm.jsp";
}
</script>
</head>
<body></body>
<%@ include file="../common/menubar.jsp" %>
<hr>
<h1 align="center">게시글 목록</h1>
<br>

<%-- 게시글 쓰기는 로그인한 회원만 가능함 --%>
<% if(loginMember != null){	//로그인 했다면 %>
<div style="align:center;text-align:center;">
	<button onclick="showWriteForm();">글쓰기</button>
</div>
<% } %>

<%-- 조회된 게시글 목록 출력 --%>
<table align="center" border="1" cellspacing="0" width="700">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
		<th>첨부파일</th>
		<th>조회수</th>
	</tr>
<%-- 보류	<% for(Qna q : list){ %>	
		<tr>
			<td></td>
			<td>
			
			</td> --%>
		</tr>
</body>
</html>