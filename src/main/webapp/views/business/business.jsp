<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, business.model.vo.Business, java.sql.Date, member.model.vo.Member"%>

<%
	Member member = (Member)request.getAttribute("member");
	ArrayList<Business> list = (ArrayList<Business>)request.getAttribute("list");
	
	int nowpage = 1;
	if(request.getAttribute("currentPage") != null) {
		nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
	}
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>nbread</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
	<link rel="stylesheet" href="/comi/resources/css/question.css"/>
	<link rel="stylesheet" href="/comi/resources/css/business.css"/>
	
	
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>
	<script type="text/javascript">
	function showWriteForm(){
		location.href = "/comi/views/business/buWriteForm.jsp";
	}
	$(function(){
		$('.search-box-text').on('keyup', function(){
			if ( event.keyCode == 13 || event.which == 13 ) {
				search();
			}
		})

		$('.search-btn').on('click', function(){
			search('search', 1, keyword);
		})
	})
	function search(keyword) {
    keyword = $('.search-box-text').val();
	console.log(keyword);
	location.href="/comi/bsearch?keyword=" + keyword;
	}
	</script>
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

			<div class="no-title">
				<br><br><h2>사업 제휴 게시판입니다.</h2>
			</div>
			
			<!--상단-->
			<div class="search-container">
				<div class="bu-title">
				
				</div>
				<div class="search-box question">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="search" placeholder="제목을 입력하세요." class="search-box-text" value="">
				</div>
			</div>
			<!--상단 end-->
			<!--게시판 begin -->
				<div class="qa-container qa-notice">
				<div class="qa-box qa-box-th" id="post_list">
					<div class="qa-box-con qa-box-num">번호</div>
					<div class="qa-box-con qa-box-title">제목</div>
					<div class="qa-box-con qa-box-writer">작성자</div>
					<div class="qa-box-con qa-box-date">등록일</div>
					<div class="qa-box-con qa-box-count">조회수</div>
				</div>
				<% for(Business b : list){ %>
					<div class="qa-box qa-box-td">
					<div class="qa-box-con qa-box-num">   <%= b.getBuNum() %> </div>
					<div class="qa-box-con qa-box-title">
						<a href ="/comi/bdetail?bnum=<%= b.getBuNum()%>&page=<%= nowpage %>"><%= b.getBuTitle()%></a>
					</div>
					<div class="qa-box-con qa-box-writer">
						<%= loginMember.getMeAka() %>
					</div>
					<div class="qa-box-con qa-box-date"><%= b.getBuEnroll() %></div>
					<div class="qa-box-con qa-box-count"><%= b.getBuViews() %> </div>
					</div>
				<%}%>
				
				
			</div>
			<!-- 게시판 end -->
			
			<!-- 버튼박스 -->
			<div class="bu-container">
				<div class="bu-btnbox">
					
					<%-- 게시글 쓰기는 로그인한 회원만 가능함 --%>
					<% if(loginMember != null){ %>
					<button class="bu-write-btn" id="my_write_btn" >내가쓴글</button> 
					<button class="bu-write-btn" id="write_btn" onclick="showWriteForm();">글쓰기</button>
					<%} %>
				</div>
				<!-- <div class="bu-navbox">
					<button class="bu-nav-btn prevnext-btn" id="bu_nav_btn_prev">
						<img src="/comi/resources/images/prevBtn.png">
					</button>
					<div class="bu-navbox-btnbox">
						<button class="bu-nav-btn active" id="bu_nav_btn_1">1</button>
						<button class="bu-nav-btn" id="bu_nav_btn_2">2</button>
					</div>
					<button class="bu-nav-btn prevnext-btn" id="bu_nav_btn_next">
						<img src="/comi/resources/images/nextBtn.png">
					</button>
					
				</div> -->
			</div>
			<!-- 버튼박스 end -->
		</div>
		<div>
			<%@ include file="../common/PagingView.jsp" %> 
		</div>
	</main>
	
	<!-- footer section begin -->
	<footer id="footer_view">
		<%@ include file="../common/footer.jsp" %>
	</footer>
	<!-- footer section end -->
</body>
</html>