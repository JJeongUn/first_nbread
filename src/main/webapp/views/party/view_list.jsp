<%@page import="java.util.ArrayList"%>
<%@page import="party.model.vo.Party"%>
<%@page import="photo.model.vo.Photo"%>
<%@page import="adver.model.vo.Adver"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
ArrayList<Party> partyList = (ArrayList<Party>) request.getAttribute("partyList"); 
ArrayList<Photo> photoList = (ArrayList<Photo>) request.getAttribute("photolist");
ArrayList<Adver> advertise = (ArrayList<Adver>) request.getAttribute("advertise");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/slick.min.css">
<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/slick-theme.min.css">
<link rel="stylesheet" type="text/css" href="/comi/resources/css/party_view.css">
<script type="text/javascript" src="/comi/resources/js/lib/slick.min.js"></script>
<script type="text/javascript" src="/comi/resources/js/util.js"></script>
</head>
<body>
	<% for(Party p : partyList){ %>
	<% if(!(p.getPaAct().equals("B"))){%>


	<a class="port_box flexBox"
		href="/comi/partysel?panum=<%= p.getPaNum() %>&act=<%= p.getPaAct() %>">
		<div class="image featured">
			<% 
                Boolean photoBool = false;
                Photo myPh = null;
                for(Photo ph : photoList){
                    if(p.getPhNum() != 0 && p.getPhNum() == ph.getPhotonum()){
                        photoBool = true;
                        myPh = ph;
                        break;
                    }
                } 
            %>

            <%  if(photoBool) { 
                    if(myPh != null) { %> <img src="/comi/resources/partyfile/<%= myPh.getPhotonum() %>/<%= myPh.getPhoto1() %>" alt="" /><% } %>
            <%  }else{ %>
                    <img src="/comi/resources/images/empty.png" alt="" />
            <%  } %>

		</div>
		<div class="text_box">
			<div class="port_box_title"><%= p.getPaTitle() %></div>
			<div class="port_box_textbox">
				<div class="port_box_price">
					가격 : <span class="price_all"><%= p.getPaTotalAmount() %></span>
				</div>
				<div class="port_box_deposit port_box_text_right">
					예치금 : <span class="price_deposit"><%= p.getPaDeposit() %></span>
				</div>
			</div>
			<div class="port_box_textbox">
				<%if(p.getPaLocation() != null) {%>
				<div class="port_box_address"><%= p.getPaLocation() %></div>
				<%}else{%>
				<div class="port_box_address">모이는 장소가 없어요!</div>
				<%}%>
				<div class="port_box_date port_box_text_right"><%= p.getPaEnroll() %></div>
			</div>
			<div class="port_box_textbox">
				<div class="port_box_text">
					인원 모집
					<%= p.getPaTotalNum() %>명
				</div>
				<div class="port_box_text port_box_text_right">
					인당
					<%= p.getPaPerAmount() %>원
				</div>
			</div>
			<%if(p.getPaAct().equals("N")) {%>
			<div class="party_closed_bottom">종료된 모임이에요.</div>
			<%}%>
		</div> <%}%>
	</a>
	<% } %>

    
    <% if(advertise != null && advertise.size() > 0) { %>
    <% 
        ArrayList<String> photoArr = new ArrayList<String>();
    	String pho1 = advertise.get(0).getAdPhoto1();
    	if(pho1 != null) photoArr.add(pho1);
    	String pho2 = advertise.get(0).getAdPhoto2();
    	if(pho2 != null) photoArr.add(pho2);
    	String pho3 = advertise.get(0).getAdPhoto3();
    	if(pho3 != null) photoArr.add(pho3);
    	String pho4 = advertise.get(0).getAdPhoto4();
    	if(pho4 != null) photoArr.add(pho4);
    	String pho5 = advertise.get(0).getAdPhoto5();
    	if(pho5 != null) photoArr.add(pho5);
    	
    %>
    
    <div class="port_box flexBox advertise">
        
        <div class="image featured">
            <!-- 슬라이더 -->
            <div class="slider_view" id="sliderView_1">
            	<% if(photoArr.size() > 0) { %>
	                <% for(int i=0; i<photoArr.size(); i++){ 
	                    out.print("advertise : " + advertise);
	                    %>
	                    <a href="<%= advertise.get(0).getAdUrl() %>" target="_blank">
	                        <div class="slide_box">
	                        	<img src="/comi/resources/adverfile/<%= advertise.get(0).getAdPhNum() %>/<%= photoArr.get(i) %>" alt="" />
	                        </div>
	                    </a>
	                <% } %>
	            <% }else{ %>
	            		<a href="<%= advertise.get(0).getAdUrl() %>" target="_blank">
	                        <div class="slide_box">
	                            <img src="/comi/resources/images/empty.png" alt="" />
	                        </div>
	                    </a>
	           	<% } %>
            </div>

        <script type="text/javascript">new Util().slide($('.slider_view'));</script>
        </div>
        <a href="<%= advertise.get(0).getAdUrl() %>" target="_blank">
            <div class="text_box">
                <%= advertise.get(0).getAdTitle() %>
            </div>
        </a>
    </div>
    <% } %>
</body>
</html>