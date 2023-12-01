<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="business.model.vo.Business, member.model.vo.Member"%>
 
<%
	Member member = (Member)request.getAttribute("member");
	Business business = (Business)request.getAttribute("business");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nbread</title>
<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
	<link rel="stylesheet" href="/comi/resources/css/question.css"/>
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>

<style>

table {
border: 2px solid #FFE436;
margin: 0 auto; 
text-align: center; 
background-color: #ffed76;
border-radius: 10px;

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
		<div>
			
			<!-- 상단 -->
			<!--상단-->
			<div class="search-container">
				<div class="qa-title" style="color:	#E0892F;">
					사업 제휴
				</div>
				<!--
				<div class="search-box question">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="text" placeholder="제목을 입력해보세요." class="search-box-text" value="">
				</div>
				-->
			</div>
			<!--상단end-->
			<!-- 상단end -->
<!-- form에서 입력값들과 파일을 함께 전송하려면 
enctype="multipart/form-data 추가해야함 -->	

<form action="/comi/binsert" method="post" enctype="multipart/form-data">
<input type="hidden" name="writer" value="<%= loginMember.getMeNum() %>">
<table align="center" width="500"  cellspacing="0" cellpadding="5">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" size="50"></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td ><input type="text" size="50"
		readonly value="<%= loginMember.getMeAka() %>"></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td><input type="file" name="upfile"></td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="15" cols="50" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기"> &nbsp;
			<input type="reset" value="작성취소"> &nbsp;
			<input type="button" value="목록" 
			onclick="javascript:location.href='/comi/blist?page=1'; return false;">
		</th>
	</tr>
</table>
</form>
<br>	
		</div>
	</main>
	
<hr>
<!-- Footer Section Begin -->
<footer id="footer_view">
	<%@ include file="../common/footer.jsp" %> 
</footer>
<!-- Footer Section End -->
</body>
</html>