<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="business.model.vo.Business"%>
<%
Business business = (Business) request.getAttribute("business");
int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bu_update</title>
<style>
	table textarea {
		height: 150px;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<h1 align="center"><%=business.getBuNum() %>번 게시글 수정페이지</h1>
	<br>
	
	<%
	if(business.getBuLev() == 1){
	%>
	<form action="/comi/bupdate" method="post" enctype="multipart/form-data">
		
		<input type="hidden" name="bnum" value="<%=business.getBuNum()%>">
		<input type="hidden" name="page" value="<%=currentPage%>">
		
		<table align="center" width="500" border="1" cellspacing="0" cellpadding="5" style="border:2px solid #ffe436; margin:0 auto; text-align: center;
		background-color: #ffed76; border-radius:10px;">
			<tr>
				<th>제 목</th>
				<td><input type="text" name="title" size="50" 
				value="<%=business.getBuTitle() %>"></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea row="5" cols="50" name="content"><%=business.getBuCon() %></textarea></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="수정하기"> &nbsp;
				<input type="reset" value="수정취소"> &nbsp;
				<input type="button" value="이전페이지 이동" onclick="javascript:history.go(-1); return flase;"> &nbsp;
				<input type="button" value="목록" onclick="javascript:location.href='/comi/blist?page=<%=currentPage %>'; return flase;">
				</th>
			</tr>
		</table>
	</form>
	
	<%
	}
	%>
	<!-- Footer Section Begin -->
	<footer id="footer_view">
		<%@ include file="../common/footer.jsp" %> 
	</footer>
	<!-- Footer Section End -->
</body>
</html>