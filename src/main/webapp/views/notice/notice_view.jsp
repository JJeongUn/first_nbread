<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, notice.model.vo.Notice, common.Paging, java.sql.Date" %>
<%@ page import="member.model.vo.Member"%>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
	ArrayList<Member> memberlist = (ArrayList<Member>)request.getAttribute("memberlist");
	
	/* Paging paging = (Paging)request.getAttribute("paging");
		int startPage = paging.getStartPage();
		int endPage = paging.getEndPage();
		int maxPage = paging.getMaxPage();
		int currentPage = paging.getCurrentPage(); */
		int nowpage = 1;
		if(request.getAttribute("currentPage") != null){
			nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
		}	
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>notice</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
<!-- 	<link rel="stylesheet" href="/comi/resources/css/question.css"/> -->
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>
 	<link rel="stylesheet" href="/comi/resources/css/noticeView.css"/> 
	<style>

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

			<div class="no-title">
				<br><br><span style="font-size: 20px;">공지사항</span>
			</div>
			
			<!--상단-->
			<div class="search-container">
				
				<div class="search-box question" style="text-align: center; margin: auto;">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="text" placeholder="제목을 입력해보세요." class="search-box-text" value="">
				</div>
			</div>
			<!--상단end-->

			<!--게시판-->
			<div class="qa-container qa-notice">

				<!-- <tr><th>번호</th><th>제목</th><th>날짜</th><th>조회수</th><th>글쓴이</th></tr> -->
				<div class="qa-box qa-box-th" id="post_list">
				<div class="qa-box-con qa-box-num">번호</div>
					<div class="qa-box-con qa-box-title">제목</div>
					<div class="qa-box-con qa-box-writer">작성자</div>
					<div class="qa-box-con qa-box-date">등록일</div>
					<div class="qa-box-con qa-box-count">조회수</div>
				</div>
			
				<%
				for (Notice n : list) {
				%>
					<div class="qa-box qa-box-td">
					<div class="qa-box-con qa-box-num"><a
							href="/comi/noticesel?nonum=<%=n.getNoNum()%>&page=<%=nowpage%>""><%=n.getNoNum()%></a>
					</div>
					<div class="qa-box-con qa-box-title"><a
							href="/comi/noticesel?nonum=<%=n.getNoNum()%>&page=<%=nowpage%>""><%=n.getNoTitle()%></a>
					</div>
					<div class="qa-box-con qa-box-writer"><a
							href="/comi/noticesel?nonum=<%=n.getNoNum()%>&page=<%=nowpage%>""><%=n.getMeNum()%></a>
					</div>
					<div class="qa-box-con qa-box-date"><a
							href="/comi/noticesel?nonum=<%=n.getNoNum()%>&page=<%=nowpage%>""><%=n.getNoEnroll()%></a>
					</div>
					<div class="qa-box-con qa-box-count"><a
							href="/comi/noticesel?nonum=<%=n.getNoNum()%>&page=<%=nowpage%>""><%= n.getNoViews()%></a>
					</div>
					</div>
				<%}%>
				</div>
				<hr>
			<!--게시판end-->

			<!--버튼박스-->
			<div class="no-container">
				<div class="no-btnbox">
					<% if(loginMember != null && loginMember.getMeAdmin().equals("Y")){ %>
						<button class="no-write-btn" id="write_btn" onclick="javascript:location.href='/comi/views/notice/notice_write.jsp'">글쓰기</button>
					<%}%>
				</div>
				<!-- <div class="no-navbox">
					<button class="no-nav-btn prevnext-btn" id="no_nav_btn_prev">
						<img src="/comi/resources/images/prevBtn.png">
					</button>
					<div class="no-navbox-btnbox">
						<button class="no-nav-btn active" id="no_nav_btn_1">1</button>
						<button class="no-nav-btn" id="no_nav_btn_2">2</button>
					</div>
					<button class="no-nav-btn prevnext-btn" id="no_nav_btn_next">
						<img src="/comi/resources/images/nextBtn.png">
					</button>
				</div> -->
			</div>
			<!--버튼박스end-->
			
		</div>
	</main>
	

	<!-- Footer Section Begin -->

	<%@ include file="../common/PagingView.jsp" %>	
		
	<footer id="footer_view">	
	<%@ include file="../common/footer.jsp" %>
	</footer>
	<!-- Footer Section End -->
</body>
</html>