<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	String type = (String) request.getAttribute("type");
%>
<%
	//로그인 확인을 위해서 내장된 session 객체를 이용
	Member loginMember = (Member)session.getAttribute("loginMember");
%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>header</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/header.css"/>
</head>
<body>
    <!-- Header Section Begin -->
    
    <header class="header">
        <div class="container">
            <!--메뉴-->
            <nav class="header_menu">
                <!--로고-->
                <div class="header_logo left">
                    <a href="/comi/main.jsp"><img src="/comi/resources/images/logo.png" alt=""></a>
                </div>

                <!--메뉴-->
                <div class="header_text center">
                    <ul>
                        <li class="<%= (type != null && type.equals("make")) ? "active" : "" %>"><a href="/comi/views/party/party_make.jsp">공유 해요</a></li>
                        <li class="<%= (type != null && type.equals("findParty")) ? "active" : "" %>"><a href="/comi/partysall?type=findParty">공유 찾기</a></li>
                        <li class="<%= (type != null && type.equals("findReview")) ? "active" : "" %>"><a href="/comi/partysall?type=findReview">공유 후기</a></li>
                        <li class="<%= (type != null && type.equals("qna")) ? "active" : "" %>"><a href="/comi/qlist">Q&A</a></li>
                        <% if (loginMember != null && "Y".equals(loginMember.getMeAdmin())) { %>
                            <li class="<%= (type != null && type.equals("qna")) ? "active" : "" %>"><a href="/comi/admin">Admin Page</a></li>
                        <% } %>
                    </ul>
                </div>
				
                <!--장바구니, 찜-->
                <div class="header_right right">
                    <div class="header_right_widget">
                    	
                   		<!-- 비로그인 상태 -->
                    	<% if(loginMember == null) { %>
                    	 <a><img src="/comi/resources/images/launcher.png" style="width: 30px; height: 30px"></a>
                        <li><a href="/comi/views/member/member_login.html">로그인</a></li>

                        <!-- 로그인 상태 -->
                        <% } else { %>
                        
                        <a href="/comi/myinfo?menum=<%= loginMember.getMeNum() %>">
                            <% if(loginMember.getMePhotoAdd() == null) { %>
                                <img src="/comi/resources/images/launcher.png" style="width: 30px; height: 30px">
                            <% } else { %>
                                <img src="/comi/resources/profile_photo_upfiles/<%= loginMember.getMeNum() %>/<%= loginMember.getMePhotoAdd() %>" style="width: 30px; height: 30px">
                            <% } %>
                        </a>
                        
                        <div class="myinfo-box">
                            <a href="/comi/myinfo?menum=<%= loginMember.getMeNum() %>"><%= loginMember.getMeName() %>님</a>
					        <a href="/comi/logout">로그아웃</a> 
                        </div>
					    <% } %>
                        
                    </div>
                </div>
            </nav>

        </div>
    </header>
    <!-- Header Section End -->
    
    <!-- mobile_header 사용안함-->
    <header class="mobile_header">
        
        <nav class="mobile_nav">
            <div class="mobile_nav_top">
                <a class="home_btn" href="/comi/main.html">
                    <img src="/comi/resources/images/headerTopIcon_1.png" />
                </a>
            
                <button class="nav_btn" type="button">
                    <img src="/comi/resources/images/headerTopIcon_2.png" />
                </button>
            </div>
            
            <div class="navbar_box">
                <div class="nav_item">
                    <a class="nav_link" href="/comi/views/party/party_make.html" target="_blank">
                        <img src="/comi/resources/images/headerSubIcon_1.png" />
                            <span class="nav_link_tex">공유 해요</span>
                    </a>
                </div>
                <div class="nav_item">
                    <a class="nav_link" href="/comi/views/party/party_view.html" target="_blank">
                        <img src="/comi/resources/images/headerSubIcon_2.png" />
                            <span class="nav_link_tex">공유 찾기</span>
                    </a>
                </div>
                <div class="nav_item">
                    <a class="nav_link" href="/comi/views/party/party_closed.html" target="_blank">
                        <img src="/comi/resources/images/headerSubIcon_3.png" />
                            <span class="nav_link_tex">공유 후기</span>
                    </a>
                </div>
                <div class="nav_item">
                    <a class="nav_link" href="/comi/views/qna/qna.html" target="_blank">
                        <img src="/comi/resources/images/headerSubIcon_5.png" />
                            <span class="nav_link_tex">Q&A</span>
                    </a>
                </div>
            </div>
        </nav>
    </header>
    <!-- mobile_header End -->

</body>
</html>