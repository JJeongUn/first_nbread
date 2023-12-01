<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice, photo.model.vo.Photo"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	Photo photo = (Photo)request.getAttribute("photo");
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
	<link rel="stylesheet" href="/comi/resources/css/notice_detail.css"/>
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>
</head>
<body>
<!-- Header Section Begin -->
    <header id="header_view">    
    <%@ include file="../common/header.jsp" %>
    </header>
<main class="main_wrapper">
		<div class="container">

			<div class="no-title">
				<br><br><h2><%=notice.getNoNum()%>번째 공지글 입니다.</h2>
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
			
	
		<!--공지사항 출력-->


			<table class="tg">
				<thead>
					<tr class="notice-section">
						<th class="tg-c3ow"><%=notice.getNoNum()%></th>
						<th class="tg-0pky"><%=notice.getNoTitle()%></th>
						<th class="tg-0lax">
							<%
							if (notice.getNoModDate() == null) {
							%> <%=notice.getNoEnroll()%> <%
							} else {
							%> <%=notice.getNoModDate()%> <%
							}
							%>
						</th>
					</tr>
				</thead>
				<tbody class="notice-body">
					<tr>
						<td class="tg-0pky" colspan="3">
							<%
							if (photo.getPhotonum() > 0) {
							%>
							<div class="photo">
								<div class="photo_box">
									<img class="photo_img"
										src="/comi/resources/noticefile/<%=photo.getPhotonum()%>/<%=photo.getPhoto1()%>">

									<%
									}
									%>
								</div>
							</div> <br><%=notice.getNoCon()%></td>
					</tr>
				</tbody>
			</table>

			<hr> <!--공지사항 end-->
	
	<!--버튼박스-->
			<div class="no-container">
				<div class="no-btnbox">
					<button class="no-write-btn" id="write_btn"
										onclick="javascript:location.href='/comi/nomovepage?nonum=<%=notice.getNoNum()%>'">수정하기</button>
					<button class="no-write-btn" id="delete_btn"
										onclick="javascript:location.href='/comi/noticedel?nonum=<%=notice.getNoNum()%>'">삭제하기</button>
				</div>
				<div class="no-navbox">
					<button class="no-nav-btn prevnext-btn" id="no_nav_btn_prev">
						<img src="/comi/resources/images/prevBtn.png">
					</button>
					<div class="no-navbox-btnbox">
						<button class="no-nav-btn active" id="no_nav_btn_1">페이징 들어갈 부분</button>
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
