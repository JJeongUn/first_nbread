<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="party.model.vo.Party"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String pageType = (String) request.getAttribute("type");
    int currentPage = (int) request.getAttribute("currentPage");
    int classfy = (int) request.getAttribute("classfy");
    String keyword = (String) request.getAttribute("keyword");
    String sort = (String) request.getAttribute("sort");
    int listCount = (int) request.getAttribute("listCount");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>party_view</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/party_view.css"/>
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/party_view_click.css"/>
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/lib/lozad.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/util.js"></script>
    <script type="text/javascript">
        var pageType = <%= "\"" + pageType + "\"" %>;
        var listCount = Number(<%= "\"" + listCount + "\"" %>);
    </script>
    <script type="text/javascript" src="/comi/resources/js/party_view.js"></script>
    <script>
//var page = <%= "\"" + currentPage + "\"" %>;
var keyword = <%= "\"" + ((keyword == null) ? "" : keyword) + "\"" %>;
var sort = <%= "\"" + sort + "\"" %>;
console.log('sort : ' + sort);
var classfy = Number(<%= "\"" + classfy + "\"" %>);
var limit;

$(function(){
    $('#selectSort_1 option').each(function(){
        if($(this).val() == classfy){
            $(this).prop('selected', true);
        }    
    })

    $('#selectSort_2 option').each(function(){
        if($(this).val() == sort){
            $(this).prop('selected', true);
        }  
    })
    
    $('.search-box-text').on('keyup', function(){
        if ( event.keyCode == 13 || event.which == 13 ) {
            search('search', 1, keyword, sort, classfy);
            console.log('엔터 입력 : ' + keyword);
        }
    })

    $('.search-btn').on('click', function(){
        search('search', 1, keyword, sort_1, sort_2);
    })

    //정렬 1
    $('#selectSort_1').on('change', function(){
        classfy = $(this).val();
        search('select', 1, keyword, sort, classfy);
    })

    //정렬 2
    $('#selectSort_2').on('change', function(){
        sort = $(this).val();
        search('select', 1, keyword, sort, classfy);
    })
})

function search(type, page, keyword, sort_1, sort_2) {
    keyword = $('.search-box-text').val();
    if(type == 'select'){
        location.href = '/comi/partysall?type=' + <%= "\"" + pageType + "\"" %> + '&page=' + page 
                        + '&keyword=' + keyword + '&sort=' + sort + '&classfy=' + classfy;
    }else{
        //if(keyword != '') {
            //검색 시작
            location.href = '/comi/partysall?type=' + <%= "\"" + pageType + "\"" %> + '&page=' + page 
                        + '&keyword=' + keyword + '&sort=' + sort + '&classfy=' + classfy;
        //}
    }
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
            <div class="search-container">
                <div class="search-box">
                    <button class="search-btn">
                        <img src="/comi/resources/images/search_btn.png" class="search-image">
                    </button>
                    <% if(pageType.equals("findParty")){ %>
                    	<input type="text" placeholder="찾고 싶은 공유 모임을 검색해보세요." class="search-box-text" value="<%= (keyword == null) ? "" : keyword %>">
                    <% }else{ %>
                    	<input type="text" placeholder="예전 공유 모임을 검색해보세요." class="search-box-text" value="<%= (keyword == null) ? "" : keyword %>">
                    <% } %>
                </div>
                <div class="search-classify">
                
                    <span class="search-classify-title">분류</span>
                    <select class="search-classify-select" id="selectSort_1">
                        <option value="-1">전체</option>
                        <option value="1">모임</option>
                        <option value="2">공구</option>
                        <option value="3"> 렌탈</option>
                        <option value="4">게임</option>
                        <option value="5">OTT</option>
                        <option value="6"> 기타</option>
                    </select>

                    <select class="search-classify-select" id="selectSort_2">
                        <!--<option value="accuracy" selected>정확도순</option>-->
                        <option value="current">최신순</option>
                        <option value="interest">관심순</option>
                    </select>

                </div>
            </div>
            
            <div class="main_portfolio" id="portf_box">
                <%@ include file="./view_list.jsp" %>
            </div>

        </div>
    </main>
    
    <!-- Footer Section Begin -->
    <footer id="footer_view">
    	<%@ include file="../common/footer.jsp" %>
    </footer>
    <!-- Footer Section End -->
</body>
</html>