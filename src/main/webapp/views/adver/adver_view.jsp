<%@ page import="adver.model.vo.Adver"%>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ArrayList<Adver> list = (ArrayList<Adver>)request.getAttribute("list");
    
    int nowpage = 1;
	if(request.getAttribute("currentPage") != null) {
		nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
	}
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css" />
    <link rel="stylesheet" href="/comi/resources/css/main.css" />
    <link rel="stylesheet" href="/comi/resources/css/review.css" />
    <link rel="stylesheet" href="/comi/resources/css/question.css" />
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/common.js"></script>
    <script type="text/javascript" src="/comi/resources/js/question.js"></script>
    <script type="text/javascript">
        function showWriteForm(){
            location.href = "/comi/views/adver/adverWriteForm.jsp";
        }
    </script>
</head>

<body>
    <header id="header_view">
    	<%@ include file="../common/header.jsp" %> 
    </header>

    <main class="main_wrapper">
		<div class="container">
			
			<!--상단-->
			<div class="search-container">
				<div class="qa-title">
					광고 게시
				</div>
				<div class="search-box question">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="text" placeholder="제목을 입력해보세요." class="search-box-text" value="">
				</div>
			</div>
			<!--상단end-->

			<!--게시판-->
			
			
			<div class="qa-container qa-notice">
				<div class="qa-box qa-box-th" id="post_list">
					<div class="qa-box-con qa-box-num">번호</div>
					<div class="qa-box-con qa-box-title">제목</div>
					<div class="qa-box-con qa-box-writer">작성자</div>
					<div class="qa-box-con qa-box-date">등록일</div>
					<div class="qa-box-con qa-box-count">조회수</div>
				</div>
				<% for(Adver a : list){ %>
					<div class="qa-box qa-box-td">
					<div class="qa-box-con qa-box-num"><a 
							href="/comi/adsall?nonum=<%= a.getAdNum() %>&page=<%= nowpage %>"> <%= a.getAdNum()+1 %></a>
					</div>
					<div class="qa-box-con qa-box-title"><a 
							href="/comi/adsall?nonum=<%= a.getAdNum() %>&page=<%= nowpage %>"> <%= a.getAdTitle()%></a>
					</div>
					<div class="qa-box-con qa-box-writer"><a 
							href="/comi/adsall?nonum=<%= a.getAdNum() %>&page=<%= nowpage %>"> <%= a.getMeNum() %></a>
					</div>
					<div class="qa-box-con qa-box-date"><a 
							href="/comi/adsall?nonum=<%= a.getAdNum() %>&page=<%= nowpage %>"> <%= a.getAdEnroll() %></a>
					</div>
					</div>
				<%}%>
				
				
			</div>
			<!--게시판end-->

			<!--버튼박스-->
			<div class="qa-container">
				<div class="qa-btnbox">
					
					<%-- 게시글 쓰기는 로그인한 회원만 가능함 --%>
					<% if(loginMember != null){ %>
					<button class="qa-write-btn" id="write_btn" onclick="showWriteForm();">글쓰기</button>
					<% } %>
				</div>
				<!-- <div class="qa-navbox">
					<button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_prev">
						<img src="/comi/resources/images/prevBtn.png">
					</button>
					<div class="qa-navbox-btnbox">
						<button class="qa-nav-btn active" id="qa_nav_btn_1">1</button>
						<button class="qa-nav-btn" id="qa_nav_btn_2">2</button>
					</div>
					<button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_next">
						<img src="/comi/resources/images/nextBtn.png">
					</button>
					<button class="qa_list" id="qa_list" onclick="javascript:location.href='/comi/qlist?page=1';" style="cursor:pointer"> 목록
						
					</button>
					
				</div> -->
			</div>
			<!--버튼박스end-->
			
		</div>
	</main>
	
	<%@ include file="../common/PagingView.jsp" %>
	
    <footer id="footer_view">
		<%@ include file="../common/footer.jsp" %> 
	</footer>
</body>

</html>