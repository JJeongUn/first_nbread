<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="common.Paging" %>    

<%
	Paging paging = (Paging)request.getAttribute("paging");

	int startPage = paging.getStartPage();
	int endPage = paging.getEndPage();
	int maxPage = paging.getMaxPage();
	int currentPage = paging.getCurrentPage();
	int limit = paging.getLimit();
	
	String urlMapping = paging.getUrlMapping();
	
	String action = (String)request.getAttribute("action");
	String keyword = null, begin = null, end = null;

	if(action != null){
		if(action.equals("date")) {
			begin = (String)request.getAttribute("begin");
			end = (String)request.getAttribute("end");
		}else {
			keyword = (String)request.getAttribute("keyword");
		}
	}
	
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/comi/resources/css/question.css" />
</head>
<body>

<%-- 목록 페이징 처리 --%>
<% if(action == null){ %>
<div class="qa-container">
    <div class="qa-navbox">
        <%if(currentPage <= 1){%>
            <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_prev_prev">
                <img src="/comi/resources/images/prevprevBtn.png">
            </button>
        <%}else{ //currentPage > 1 %>  
            <a href="/comi/<%= urlMapping %>?page=1">
                <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_prev_prev">
                    <img src="/comi/resources/images/prevprevBtn.png">
                </button>
            </a>
        <%}%>

        <%-- 이전 페이지 그룹으로 이동--%>
        <%if((currentPage - 10) < startPage && (currentPage -10) > 1 ){ %>
            <a href="/comi/<%= urlMapping %>?page=<%= startPage - 10 %>">
                <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_prev">
                    <img src="/comi/resources/images/prevBtn.png">
                </button>
            </a>
        <% }else{ %>  
            <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_prev">
                <img src="/comi/resources/images/prevBtn.png">
            </button>
        <%}%>

	<%-- 현재 페이지가 속한 페이지그룹 숫자 출력 --%>

        <%for(int p = startPage; p<=endPage; p++){
           	 if(p==currentPage){ 
        %>
            <button class="qa-nav-btn active" id="qa_nav_btn_<%= p%>"><%= p%></button>
        	<% }else{%>
            <a href="/comi/<%= urlMapping %>?page=<%= p%>">
                <button class="qa-nav-btn" id="qa_nav_btn_<%= p%>"><%= p%></button>
            </a>
       	    <%}}%>
      

        <%-- 다음 페이지 그룹으로 이동--%>

        <%if((currentPage + 10) > endPage && (currentPage + 10) < maxPage ){ //다음그룹이 있다면  %>
            <a href="/comi/<%= urlMapping %>?page=<%= endPage+10%>">
                <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_next">
                    <img src="/comi/resources/images/nextBtn.png">
                </button>
            </a>&nbsp;
        <%}else{ //다음그룹이 없다면 > 1 %>  
            <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_next">
                <img src="/comi/resources/images/nextBtn.png">
            </button>
        <%}%>

        <%if(currentPage >= maxPage){%>
            <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_next_next">
                <img src="/comi/resources/images/nextnextBtn.png">
            </button>
        <%}else{ //currentPage < maxPage %> 
            <a href="/comi/<%= urlMapping %>?page=<%= maxPage %>">
                <button class="qa-nav-btn prevnext-btn" id="qa_nav_btn_next_next">
                    <img src="/comi/resources/images/nextnextBtn.png">
                </button>
            </a>
        <% } %>
    </div>
</div>
    	<% } %>
</body>
</html>