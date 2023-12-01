<%@page import="java.util.Arrays"%>
<%@page import="common.Paging"%>
<%@page import="partyCo.model.vo.PartyCo"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String login = (loginMember != null) ? "login" : "logout";
	String myId = (loginMember != null) ? loginMember.getMeId() : "";
	int myMeNum = (loginMember != null) ? loginMember.getMeNum() : -1;
	int partyColistCount = (int) request.getAttribute("partyColistCount");
	ArrayList<ArrayList<PartyCo>> partyCoList = (ArrayList<ArrayList<PartyCo>>) request.getAttribute("partyCoList");
	Paging copage = (Paging) request.getAttribute("partyCoPaging");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/view_reply.css"/>
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    <script type="text/javascript">
    var currentPage = Number(<%= "\"" + copage.getCurrentPage() + "\"" %>);
    var login = <%= "\"" + login + "\"" %>;
	var myId = <%= "\"" + myId + "\"" %>;
	var myMeNum = Number(<%= "\"" + myMeNum + "\"" %>);
	var paNum = Number(<%= "\"" + party.getPaNum() + "\"" %>);
	var myPhotoNum = null;
	<% if(loginMember != null) { %> 
		myPhotoNum = Number(<%= "\"" + loginMember.getMePhotoAdd() + "\"" %>);
	<% } %>
    </script>
    <script type="text/javascript" src="/comi/resources/js/util.js"></script>
    <script type="text/javascript" src="/comi/resources/js/view_reply.js"></script>
</head>
<body>
	
	<!-- 게시글 리뷰-->
    <section id="review">
        <!--댓글 헤드-->
        <div class="review-head" data-panum="<%= party.getPaNum() %>">
            <span class="review-head-title" id="review_number"><%= partyColistCount %>개의 댓글</span>
            <button type="button" class="review-head-refresh" alt="새로고침">
                <img src="/comi/resources/images/refresh.png">
            </button>
        </div>
        <!--댓글 헤드 end-->

        <!--댓글 쓰기-->
        <div class="review-write">
            <form action="" action="/comi/partycoins" method="post">
                <fieldset>
                <% if(login.equals("login")) { %>
                    <legend class="u_vc">댓글 쓰기</legend>
                    <div class="review-write-inner review-rewriting" 
            			data-panum="<%= party.getPaNum() %>"
						data-depth="1">
                        
                        <div class="review-write-profilearea">
                            <div class="review-write-profile">
								<% if(loginMember.getMePhotoAdd() != null) {%>
                                	<img class="img-profile" src="/comi/resources/profile_photo_upfiles/<%= loginMember.getMeNum() %>/<%= loginMember.getMePhotoAdd() %>.png">
                                <% }else{ %>
									<img class="img-profile" src="/comi/resources/images/launcher.png">
								<% } %>
								<span class="write-name"><%= myId %></span>
                            </div>
                        </div>

                        <div class="review-write-area">
                            <div class="review-write-area-inbox">
                                <textarea name="reply" title="댓글" class="review-write-textarea" id="review_textarea_1" rows="3" cols="30" maxlength="300"></textarea>
                                <label for="review_write_textarea_1" id="review_textlabel_1" class="u_cbox_guide">
                                    다양한 의견이 서로 존중될 수 있도록 다른 사람에게 불쾌감을 주는 욕설, 혐오, 비하의 표현이나 타인의 권리를 침해하는 내용은 주의해주세요.  
                                    모든 작성자는 <em class="guide_emphasis">본인이 작성한 의견에 대해 법적 책임을 갖는다는 점</em> 유의하시기 바랍니다.
                                </label>
                            </div>
                        </div>
                        
                        <div class="review-write-count-box">
                            <div class="review-write-count">
                                <strong class="review-write-count-num" id="write_count_1">0</strong>/
                                <span class="review-write-count-total">300</span>
                            </div>
                            <input type="button" class="review-write-upload" id="party_reply_btn" value="등록">
                        </div>
                    
                    </div>
                <% }else{ %>    
                    
                <% } %>
                	<div class="review-write-inner review-write-inner-login">
                    	<div class="review-write-oucontainer">
                    		<textarea title="댓글" class="review-write-outbox" rows="3" cols="30" readonly></textarea>
                    		<label class="review-write-guide" >댓글을 작성하려면 로그인 해주세요</label>
                    	</div>
                    </div>
                </fieldset>
            </form>
        </div>
        <!--댓글 쓰기 end-->
		<% 
        if(partyColistCount > 0 && partyCoList != null) { %>
        <!--댓글 정렬 버튼-->
        <div class="review-sort">
            <button class="review-sort-btn active" id="sortType_1">최신순</button>
            <button class="review-sort-btn" id="sortType_2">답글순</button>
            <button class="review-sort-btn" id="sortType_3">좋아요순</button>
        </div>
        <!--댓글 정렬 버튼 end-->
        <% } %>
        
        <!--review-body-->
        <div class="review-body">
    	<% 
        if(partyColistCount > 0 && partyCoList != null) {
            for(int i=0; i<partyCoList.size(); i++) { 
            	PartyCo depth1Obj = partyCoList.get(i).get(0);
           	%>
            <div class="review-package">
            	
            	<div class="review-body-container reviewbody-first" 
            	data-parent="<%= depth1Obj.getComParent() %>" 
            	data-depth="<%= depth1Obj.getComDepth() %>"
            	data-panum="<%= depth1Obj.getPaNum() %>"
				data-comnum="<%= depth1Obj.getComNum() %>">
            	
	            	<!-- review-body-box -->
	       			<div class="review-body-box">
		
		                <div class="review-body-list">
		                    <div class="review-body-list-profile">
								<% if(depth1Obj.getMePhotoAdd() != null) {%>
                                	<img class="review-body-list-profile-img" src="/comi/resources/profile_photo_upfiles/<%= depth1Obj.getMeNum() %>/<%= depth1Obj.getMePhotoAdd() %>.png">
                                <% }else{ %>
									<img class="review-body-list-profile-img" src="/comi/resources/images/launcher.png">
								<% } %>
								
		                        <div class="review-body-list-profile-box">
		                            <div class="review-body-list-name"><%= depth1Obj.getMeId() %></div>
		                            <div class="review-body-list-date"><%= depth1Obj.getComEnroll() %></div>
		                        </div>
		                    </div>
		                    <div class="review-body-list-right">
								<div class="review-dropdown">
									<% if(myMeNum == depth1Obj.getMeNum()){ %>
									<button class="review-delete-btn">삭제</button>
									<%}else {%>
									<button class="review-report-btn">신고</button>
									<%}%>
								</div>
		                        <button class="review-body-list-right-btn">
		                            <img class="review-body-list-right-btn-img" src="/comi/resources/images/1.png">
		                        </button>
		                    </div>
		                </div>
		
		                <div class="review-body-read">
		                    <div class="review-body-read-text">
		                        <%= depth1Obj.getComCon() %>
		                    </div>
		                </div>
		
		                <div class="review-body-bottom">
		                    <button class="review-body-bottom-retext">
		                        댓글 <b><%= depth1Obj.getComCount() %></b>
		                    </button>
		                    <div class="review-body-bottom-heartbox">
		                        <button class="review-body-bottom-heart" data-heart="empty">
		                            <img class="review-body-bottom-heart-img" src="/comi/resources/images/heart.png">
		                        </button>
		                        <span class="review-body-heart">
		                        	<%= depth1Obj.getComViews() %>
								</span>
		                    </div>
		                </div>
		
		            </div>
	       			<!-- review-body-box end -->
            	</div>
            	
            	<div class="review-reply-area">
            		
	            	<% 
	            	ArrayList<PartyCo> tempList = partyCoList.get(i);
	            	
	            	if(tempList.size() == 1) {
    					if(login.equals("login")) { %>
        					<!--댓글 쓰기-->
					        <div class="review-write review-rewriting">
					            <form>
					                <fieldset>
					                    <legend class="u_vc">댓글 쓰기</legend>
					                    <div class="review-write-inner review-rewriting" data-depth="2">
					                        
					                        <div class="review-write-profilearea">
					                            <div class="review-write-profile">
													<% if(loginMember.getMePhotoAdd() != null) {%>
														<img class="img-profile" src="/comi/resources/profile_photo_upfiles/<%= loginMember.getMeNum() %>/<%= loginMember.getMePhotoAdd() %>.png">
													<% }else{ %>
														<img class="img-profile" src="/comi/resources/images/launcher.png">
													<% } %>
					                                <span class="write-name"><%= myId %></span>
					                            </div>
					                        </div>
					
					                        <div class="review-write-area">
					                            <div class="review-write-area-inbox">
					                                <textarea title="댓글" class="review-write-textarea" rows="3" cols="30"></textarea>
					                                <label for="review_write_textarea_2" class="u_cbox_guide">
					                                    다양한 의견이 서로 존중될 수 있도록 다른 사람에게 불쾌감을 주는 욕설, 혐오, 비하의 표현이나 타인의 권리를 침해하는 내용은 주의해주세요.  
					                                    모든 작성자는 <em class="guide_emphasis">본인이 작성한 의견에 대해 법적 책임을 갖는다는 점</em> 유의하시기 바랍니다.
					                                </label>
					                            </div>
					                        </div>
					                        
					                        <div class="review-write-count-box">
					                            <div class="review-write-count">
					                                <strong class="review-write-count-num">0</strong>/
					                                <span class="review-write-count-total">300</span>
					                            </div>
					                            <input type="button" class="review-write-upload" value="등록">
					                        </div>
					                    
					                    </div>
					                </fieldset>
					            </form>
					        </div>
					        <!--댓글 쓰기 end--> 
    					<% }else{ %>    
					        
    					<%}%>
    						<div class="review-write-inner review-write-inner-reply">
					        	<div class="review-write-oucontainer">
					        		<textarea title="댓글" class="review-write-outbox" rows="3" cols="30" readonly></textarea>
					        		<label class="review-write-guide" >댓글을 작성하려면 로그인 해주세요</label>
					        	</div>
					        </div>
    						<button class="review-fold-btn">
								<span class="review-fold-btn-text">답글 접기</span>
							</button>
    				<%}else {
	            	
	            	
		            	for(int j=1; j<tempList.size(); j++) {%>
		            	
		            		<div class="review-body-container reviewbody-second" 
							data-panum="<%= tempList.get(j).getPaNum() %>"
							data-parent="<%= tempList.get(j).getComParent() %>"
							data-comnum="<%= tempList.get(j).getComNum() %>">
		        			
			        			<!-- review-body-box -->
			        			<div class="review-body-box">
					
					                <div class="review-body-list">
					                    <div class="review-body-list-profile">
											<% if(tempList.get(j).getMePhotoAdd() != null) {%>
												<img class="review-body-list-profile-img" src="/comi/resources/profile_photo_upfiles/<%= tempList.get(j).getMeNum() %>/<%= tempList.get(j).getMePhotoAdd() %>.png">
											<% }else{ %>
												<img class="review-body-list-profile-img" src="/comi/resources/images/launcher.png">
											<% } %>

					                        <div class="review-body-list-profile-box">
					                            <div class="review-body-list-name"><%= tempList.get(j).getMeId() %></div>
					                            <div class="review-body-list-date"><%= tempList.get(j).getComEnroll() %></div>
					                        </div>
					                    </div>
					                    <div class="review-body-list-right">
											<div class="review-dropdown">
												<% if(myMeNum == tempList.get(j).getMeNum()){ %>
												<button class="review-delete-btn">삭제</button>
												<%}else {%>
												<button class="review-report-btn">신고</button>
												<%}%>
											</div>
					                        <button class="review-body-list-right-btn">
					                            <img class="review-body-list-right-btn-img" src="/comi/resources/images/1.png">
					                        </button>
					                    </div>
					                </div>
					
					                <div class="review-body-read">
					                    <div class="review-body-read-text">
					                        <%= tempList.get(j).getComCon() %>
					                    </div>
					                </div>
					
					                <div class="review-body-bottom">
					                    <button class="review-body-bottom-retext">
					                        댓글 <b><%= tempList.get(j).getComCount() %></b>
					                    </button>
					                    <div class="review-body-bottom-heartbox">
					                        <button class="review-body-bottom-heart" data-heart="empty">
					                            <img class="review-body-bottom-heart-img" src="/comi/resources/images/heart.png">
					                        </button>
					                        <span class="review-body-heart">
					                        	<%= tempList.get(j).getComViews() %>
											</span>
					                    </div>
					                </div>
					
					            </div>
					            <!-- review-body-box end -->
					        </div>
			        		<!-- review-body-container reviewbody-second end -->
		        			
		       				<%if(j == tempList.size()-1) { %>
		       					<% if(login.equals("login")) { %>
		        					<!--댓글 쓰기-->
							        <div class="review-write review-rewriting">
							            <form>
							                <fieldset>
							                    <legend class="u_vc">댓글 쓰기</legend>
							                    <div class="review-write-inner review-rewriting" data-depth="2">
							                        
							                        <div class="review-write-profilearea">
							                            <div class="review-write-profile">
															<% if(loginMember.getMePhotoAdd() != null) {%>
																<img class="img-profile" src="/comi/resources/profile_photo_upfiles/<%= loginMember.getMeNum() %>/<%= loginMember.getMePhotoAdd() %>.png">
															<% }else{ %>
																<img class="img-profile" src="/comi/resources/images/launcher.png">
															<% } %>

							                                <span class="write-name"><%= myId %></span>
							                            </div>
							                        </div>
							
							                        <div class="review-write-area">
							                            <div class="review-write-area-inbox">
							                                <textarea title="댓글" class="review-write-textarea" rows="3" cols="30"></textarea>
							                                <label for="review_write_textarea_2" class="u_cbox_guide">
							                                    다양한 의견이 서로 존중될 수 있도록 다른 사람에게 불쾌감을 주는 욕설, 혐오, 비하의 표현이나 타인의 권리를 침해하는 내용은 주의해주세요.  
							                                    모든 작성자는 <em class="guide_emphasis">본인이 작성한 의견에 대해 법적 책임을 갖는다는 점</em> 유의하시기 바랍니다.
							                                </label>
							                            </div>
							                        </div>
							                        
							                        <div class="review-write-count-box">
							                            <div class="review-write-count">
							                                <strong class="review-write-count-num">0</strong>/
							                                <span class="review-write-count-total">300</span>
							                            </div>
							                            <input type="button" class="review-write-upload" value="등록">
							                        </div>
							                    
							                    </div>
							                </fieldset>
							            </form>
							        </div>
							        <!--댓글 쓰기 end--> 
		       					<% }else{ %>    
							        
		       					<%}%>
		       					<div class="review-write-inner review-write-inner-reply">
						        	<div class="review-write-oucontainer">
						        		<textarea title="댓글" class="review-write-outbox" rows="3" cols="30" readonly></textarea>
						        		<label class="review-write-guide" >댓글을 작성하려면 로그인 해주세요</label>
						        	</div>
						        </div>
		       					<button class="review-fold-btn">
									<span class="review-fold-btn-text">답글 접기</span>
								</button>
		       				<%}%>
		        			
		        			
		              	<% } %>
              		<% } %>
              		
              	</div><!-- review-reply-area end -->
              	
            </div>
            <!--review-package end-->
          	<% } %>
        <% } %>  	
        		
            
        </div>
		<!--review-body end-->
		
		<!-- review-bottom -->
		<div class="review-bottom">
        
        	<% 
        	//out.print(copage);
        	   if(copage.getCurrentPage() <= 1){ %>
            	<!-- <button class="review-bottom-btn" id="review_bottom_prev"></button> -->
            <% }else{ %>
	    		<!-- <button class="review-bottom-btn" id="review_bottom_prev"></button>-->
	    	<% } %>
            
			<% 
			for(int p=copage.getStartPage(); p<=copage.getEndPage(); p++){ 
				if(p == copage.getCurrentPage()) {
			%>	
				<button class="review-bottom-btn active" id="reviewPagebtn_<%= p %>">
  					<span class="review-pagespan"><%= p %></span>
  				</button>
			<%	}else{ %>
				<button class="review-bottom-btn" id="reviewPagebtn_<%= p %>">
  					<span class="review-pagespan"><%= p %></span>
  				</button>
			
			<%}%>
				
			<%}//for문 %>
			
			<% if((copage.getCurrentPage() + copage.getLimit()) < copage.getEndPage() 
					&& (copage.getCurrentPage() + copage.getLimit()) > copage.getMaxPage()){ //이전그룹이 있다면 %>
	    		<!-- <button class="review-bottom-btn" id="review_bottom_next" disabled></button>-->
	    	<% }else{ %>
	    		<!-- <button class="review-bottom-btn" id="review_bottom_next"></button> -->
	    	<% } %>
            
        </div>
        <!-- review-bottom end -->
		
        <!--<button id="replyMoreBtn"><b>댓글 더보기</b></button>-->
    </section>
    <!-- 게시글 리뷰 end-->
</body>
</html>