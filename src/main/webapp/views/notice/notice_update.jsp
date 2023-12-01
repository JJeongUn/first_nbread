<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
	<link rel="stylesheet" href="/comi/resources/css/question.css"/>
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>
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

h1{
color:	#E0892F;
}

</style>
	
	
	
</head>
<body>
<!-- Header Section Begin -->
    <header id="header_view">    
    <%@ include file="../common/header.jsp" %>
    </header>
<main class="main_wrapper">
		<div class="container">

			<div class="no-title">
				<!-- <span style="font-size: 20px;">공지사항 수정</span> -->
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
			<h1>공지사항 수정하기</h1>
			<!--공지사항 출력-->
			<div class="notice_box" style="width: 600px; border: 1px solid #ffed76; margin: 0 auto; text-align: center; ">
				<form action="/comi/noticeup" method="post">
					<input type="hidden" name="nonum" value="<%= notice.getNoNum()%>">
                    <table class="notice_table" style="display: flex; justify-content: center;">
                    <tr>
                        <th>제 목</th>
                        <td><input type="text" name="title" size="50" value="<%= notice.getNoTitle() %>"></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input type="text" name="writer" readonly value="<%= notice.getMeNum()%>" size="50"></td>
                    </tr>
                    <tr>
                        <th>내 용</th>
                        <td><textarea rows="13" cols="50" name="content"><%=notice.getNoCon()%></textarea></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                          &nbsp; &nbsp; &nbsp;<input class="btn" type="submit" value="수정하기">
                            <input class="btn" type="reset" value="수정취소">
                            <input class="btn" type="button" value="목록"
                            onclick="javascript:location.href='/comi/views/noitce/notice_view.jsp'; return false;"> 
                            <!-- 목록 지금 404 에러 -->
                            
                        </th>

                    </tr>
                </table>
                </form>
			</div>
			<hr>
			<!--공지사항 end-->

			<!--버튼박스-->
			<div class="no-container">
				<div class="no-btnbox">
					<button class="no-write-btn" id="write_btn"></button>
				</div>
				<div class="no-navbox">
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