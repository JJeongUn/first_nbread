<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="qna.model.vo.Qna, member.model.vo.Member"%>

<%
	
	Member member = (Member)request.getAttribute("member");
	Qna qna = (Qna)request.getAttribute("qna");
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/comi/resources/css/qnaWriteForm.css"/>
	<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
	<link rel="stylesheet" href="/comi/resources/css/question.css"/>
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>

<title>nbread</title>
<style>
 input.btn {
    background-color: #FFCC76;
    color: #000000;
    padding: 5px 5px; /* 좀 더 넓은 여백 적용 */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 15px;
    text-decoration: none;
    transition: background-color 0.3s ease;
    width: 120px;
    height: 30px; /* 높이 조정 */
    margin-top: 10px; /* 상단 여백 조정 */ 
    margin-bottom: 10px;
}

 input.btn:hover {
    background-color: #fca005;
    color: #ffffff;
}

div.notice_box {
	background-color:rgba(255, 237, 118, 0.8);
	border-radius: 9px;
	height: 400px;
}

table.notice_table {
margin-top: 20px;
height: 400px;

}

h2{
color:	#E0892F;
}

</style>
</head>
<body>
	<!-- Header Section Begin -->
    <header id="header_view">
    	<%@ include file="../common/header.jsp" %> 
    </header>
    <!-- Header Section End -->
	<!-- main -->
	<main class="main_wrapper">
		<div class="container">
			
			<!--상단-->
			<div class="search-container">
				<div class="qa-title">
					Q&A
				</div>
				<!--
				<div class="search-box question">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="text" placeholder="제목을 입력해보세요." class="search-box-text" value="">
					<br>
				
				</div>
				-->
			</div>
			<!--상단end-->


<!-- form 에서 입력값들과 파일을 함께 전송하려면 반드시 속성
 enctype="multipart/form-data" 를 추가해야함 -->
<% String loginName = null;%>
<%if(loginMember.getMeAka() != null){
	loginName = loginMember.getMeAka();
}else{
	loginName = loginMember.getMeName();
}%>

<form action="/comi/qinsert" method="post" enctype="multipart/form-data">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5"
	style="border:2px solid #ffe436; margin:0 auto; text-align: center;
	background-color: #ffed76; border-radius:10px;">
	<input type="hidden" name="writer" value="<%= loginMember.getMeNum() %>">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" size="50"></td>
	</tr>
	<tr>
		<th>작성자</th>
		
		<td><%= loginName %></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td><input type="file" name="upfile"></td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="50" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기"> &nbsp;
			<input type="reset" value="작성취소"> &nbsp;
			<input type="button" value="목록" 
			onclick="javascript:location.href='/comi/qlist?page=1'; return false;">
		</th>
	</tr>
</table>
</form>
<br>
		</div>
	</main>

<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>