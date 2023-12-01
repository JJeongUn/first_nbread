<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="business.model.vo.Business, member.model.vo.Member"%>
<%

   Member member = (Member)request.getAttribute("member");
   Business business = (Business)request.getAttribute("business");
   int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
   
   int nowpage = 1;
   if(request.getAttribute("currentPage") != null){
      nowpage = ((Integer)request.getAttribute("currentPage")).intValue();
   }
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bu_detail</title>
   <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
   <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
   <link rel="stylesheet" href="/comi/resources/css/main.css"/>
   <link rel="stylesheet" href="/comi/resources/css/review.css"/>
   <link rel="stylesheet" href="/comi/resources/css/question.css"/>
   <link rel="stylesheet" href="/comi/resources/css/qnaDetail.css"/>
   <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
   <script type="text/javascript" src="/comi/resources/js/question.js"></script>

<script type="text/javascript">
function requestReply(){
   //댓글달기 요청 함수
}
function requestDelete(){
   //게시글(원글, 댓글, 대댓글) 삭제 요청 함수 
   location.href = "/comi/bdelete?bnum=<%= business.getBuNum()%>";
}
function moveUpdatePage(){
   //게시글 (원글, 댓글, 대댓글) 수정 페이지로 이동 처리 함수
   location.href = "/comi/bmovepage?bnum=<%= business.getBuNum() %>&page=<%= currentPage %>";
}
function showWriteForm(){
   location.href = "/comi/views/business/buWriteForm.jsp";
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
   <header></header>
   <main class="main_wrapper">
      	<div class="container">

			<div class="no-title">
				<br><br><h2><%= business.getBuNum() %>번 글 상세보기</h2>
			</div>

			
			<div class="search-container">
				<!--<div class="bu-title">
					사업 제휴
				</div>
				<div class="search-box question">
					<button class="search-btn">
						<img src="/comi/resources/images/search_btn.png" class="search-image">
					</button>
					<input type="text" placeholder="제목을 입력하시오." class="search-box-text" value="">
				</div>-->
			</div>
			
			
			<!-- 게시판 -->
			<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
				<tr>
					<th width="120">제 목</th>
					<td><a href ="/comi/bdetail?bnum=<%= business.getBuNum()%>&page=<%= nowpage %>"><%= business.getBuTitle()%></a></td>
				</tr>
				<tr>
					<th width="120">작성자</th>
					<td><%= business.getMeNum()%></td>
				</tr>
				<tr>
					<th width="120">등록날짜</th>
					<td><%= business.getBuEnroll() %></td>
				</tr>
				<tr>
					<th width="120">내 용</th>
					<td class="table-content"><%= business.getBuCon() %></td>
				</tr>
			</table>   
    	</div>
         <!--게시판end-->

         <!--버튼박스-->
         <div class="qa-container">
            <div class="qa-btnbox">
				<button class="qa-write-btn qa_list" id="bu_list" onclick="javascript:location.href='/comi/blist?page=1';" style="cursor:pointer"> 
					목록으로
				</button>
               <% if(loginMember != null){ %>
               <!--<button class="qa-write-btn" id="my_write_btn">내가쓴글</button>-->
               <button class="qa-write-btn" id="write_btn" onclick="showWriteForm();">글쓰기</button>
               <% } %>
               <button class="qa-write-btn" onclick="moveUpdatePage(); return false;">수정하기</button> &nbsp; <!-- 수정/삭제 버튼 로그인 했을 때 가능하도록 위치 이동하기 -->
               <button class="qa-write-btn" onclick="requestDelete(); return false;">삭제하기</button> &nbsp;
               
			</div>
         </div>
         <!--버튼박스end-->
         
      </div>
   </main>
   
   <!-- Footer Section Begin -->
   <footer id="footer_view">
      <%@ include file="../common/footer.jsp" %> 
   </footer>
   <!-- Footer Section End -->
</body>
</html>