<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>comi</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
	<link rel="stylesheet" href="/comi/resources/css/question.css"/>
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>
</head>
<body>
	<!-- header section begin -->
	<header id="header_view">
		<%@ include file="../common/header.jsp" %> 
	</header>
	<!-- header section end -->
	
	<!-- main -->
	<main class="main_wrapper">
		<div class="container">
			
			<!-- 상단 -->
			<div class="search-container">
				<div class="bu-title">
					사업 제휴
				</div>
				<div class="search-box question">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="text" placeholder="제목을 입력하세요." class="search-box-text" value="">
				</div>
			</div>
			<!--상단 end-->
			
			<!-- 게시판 -->
			<div class="bu-container bu-notice">
				<div class="bu-box bu-box-th">
					<div class="bu-box-con bu-box-num">번 호</div>
					<div class="bu-box-con bu-box-title">제 목</div>
					<div class="bu-box-con bu-box-writer">작성자</div>
					<div class="bu-box-con bu-box-date">등록일</div>
					<div class="bu-box-con bu-box-count">조회수</div>
				</div>
				<div class="bu-box bu-box-td">
					<div>1</div>
					<div>본인 인증을 다시 확인해주세요.</div>
					<div>user11</div>
					<div>2023-08-09</div>
					<div>5</div>
				</div>
			</div>
		</div>
</body>
</html>