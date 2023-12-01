<%@page import="party.model.vo.Party"%>
<%@page import="member.model.vo.Member"%>
<%@page import="photo.model.vo.Photo" %>
<%@page import="chat.model.vo.Chat"%>
<%@page import="totalparty.model.vo.TotalParty"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="org.json.simple.JSONValue" %>
<%@page import="org.json.simple.JSONArray" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
   //String type = (String) request.getAttribute("type");
   Photo photo = (Photo) request.getAttribute("photo");
   Party party = (Party) request.getAttribute("party");
   Member member = (Member) request.getAttribute("member");//파티장
   String category_check = (String)request.getAttribute("category_check");
   if(category_check == null){
      category_check = "기타";
   }
   int per = 0;
   if(party.getPaPayCk() > 0) {
	   per = (int)((double)party.getPaPayCk()/(double)party.getPaTotalAmount() * 100);
   }
   ArrayList<TotalParty> totalPartyList = (ArrayList<TotalParty>)request.getAttribute("totalpartylist");
   ArrayList<Chat> chatlist = (ArrayList<Chat>)request.getAttribute("chatlist");
   Boolean chatFlag = true;
   if(chatlist == null){
      chatFlag = false;
   }
   int totalCount = (int) request.getAttribute("totalcount");
   request.setAttribute("party",party);
%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>party_view</title>
   <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
   <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/slick.min.css">
   <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/slick-theme.min.css">
   <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
   <link rel="stylesheet" type="text/css" href="/comi/resources/css/main.css"/>
   <link rel="stylesheet" type="text/css" href="/comi/resources/css/party_click.css"/>
   <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
   <script type="text/javascript" src="/comi/resources/js/lib/axios.min.js"></script>
   <script src="https://js.tosspayments.com/v1/payment-widget"></script>
   <script type="text/javascript" src="/comi/resources/js/lib/slick.min.js"></script>
   <script type="text/javascript" src="/comi/resources/js/util.js"></script>
   <script type="text/javascript">
      var paNum = <%= "\"" + party.getPaNum() + "\"" %>;
      $(function(){
         $('#complete_btn').on('click', function(){
            if(confirm("파티를 종료하시겠습니까?")){
               location.href = '/comi/partycomp?panum=' + paNum;
            }
         })
         var articleCursor = $('.article-pay-cursor');
         var cursorPer = Number(<%= "\"" + per + "\"" %>);
         articleCursor.css({
            'left' : 'calc('+cursorPer+'% - '+(articleCursor.width()/2)+'px)'
         })
         $('.article-pay-cursor').text('결제 '+cursorPer+'%');

         $('.report_btn').on('click', function(){
            var visible = $('.article-dropdown').css('display');
            if(visible == 'none') $('.article-dropdown').css('display', 'block');
            else $('.article-dropdown').css('display', 'none');
         })
      })
   </script>
</head>
<body>
    <!-- Header Section Begin -->
    <header id="header_view">
       <%@ include file="../common/header.jsp" %>
    </header>
    <!-- Header Section End -->
    <%
      String partyMemberType = "none";
      if(loginMember != null) {
         if(loginMember.getMeNum() == member.getMeNum()) {
            partyMemberType = "host";
         }else{
            
            if(totalPartyList != null) {
               if(totalPartyList.size() > 0) {
                  for(int i=0; i<totalPartyList.size(); i++) {
                     if(loginMember.getMeNum() == totalPartyList.get(i).getMeNum()) {
                        partyMemberType = "member";
                        break;
                     }
                  }
               }
            }
            
         }
      }
      //out.print("partyMemberType : " + partyMemberType);
    %>
   <!-- main -->
   <main class="main_wrapper">
      <% if(type.equals("findReview")) { //종료된 모임 %>
      <div class="container side_margin_zero margin-top20px">
         <div class="party_closed_up">종료된 모임이에요.</div>

         <!-- 슬라이더 -->
         <div class="main_slider margin-top20px">
      <% }else { //종료되지 않은 모임 %>
      <div class="container side_margin_zero">
      
         <!-- 슬라이더 -->
         <div class="main_slider">
      <% } %>
            <div id="slider_main">
               <div class="slider_main_box">
                  <div class="slide_box">
                  <% if(party.getPhNum() == 0){%>
                     <img class="picture_box" src="<%= "/comi/resources/images/empty.png" %>" />
                  <%}else{%>
                     <img class="picture_box" src="/comi/resources/partyfile/<%= photo.getPhotonum() %>/<%= photo.getPhoto1() %>" />
                     <%if(photo.getPhoto2() != null){%>
                        <img class="picture_box" src="/comi/resources/partyfile/<%= photo.getPhotonum() %>/<%= photo.getPhoto2() %>" />
                     <%}%>
                     <%if(photo.getPhoto3() != null){%>
                        <img class="picture_box" src="/comi/resources/partyfile/<%= photo.getPhotonum() %>/<%= photo.getPhoto3() %>" />
                     <%}%>
                     <%if(photo.getPhoto4() != null){%>
                        <img class="picture_box" src="/comi/resources/partyfile/<%= photo.getPhotonum() %>/<%= photo.getPhoto4() %>" />
                     <%}%>
                     <%if(photo.getPhoto5() != null){%>
                        <img class="picture_box" src="/comi/resources/partyfile/<%= photo.getPhotonum() %>/<%= photo.getPhoto5() %>" />
                     <%}%>
                  <%} %>>
                  </div>
               </div>
            </div>
         </div>
      </div>
      
      <script type="text/javascript">new Util().slide($('#slider_main'));</script>
      
      <div class="container">
         <!-- 프로필 -->
         <section id="article-profile">
            <div class="article-profile-box">

               <div class="article-join-box">
                  <% if(partyMemberType != "none"){ %>
                     <% if(partyMemberType == "host") { %>
                        파티장입니다~
                     <% }else{ %>
                        파티원입니다~
                     <% } %>
                  <% } %>
               </div>

               <a class="article-profile-link" href="#">
                  <div class="space-between">
                     <div class="display-align-items-center">
                        <div id="article-profile-image-box">
                           <img src="/comi/resources/images/launcher.png" />
                        </div>
                        <div id="article-profile-left">
                           <label onclick="javascript:location.href='/comi/myinfo?menum=<%= member.getMeNum()%>'">
                           <%if(member.getMeAka() == null){%>
                              <div id="article-nickname"><%= member.getMeName() %></div>   
                           <%}else{%>
                              <div id="article-nickname"><%= member.getMeAka() %></div>
                           <%}%>
                           </label>
                           <%if(party.getPaLocation() == null){%>
                              <div id="article-region-name">모임의 장소가 없어요</div>   
                           <%}else{%>
                              <div id="article-region-name"><%= party.getPaLocation() %></div>
                           <%}%>
                        </div>
                     </div>
                     <div class="article-profile-right">
                        <div class="temperature-wrap">
                           <span>신뢰도</span>
                           <span class="text-color text-color-03" id="text-color-id"><%= member.getMeLike() %></span>
                        </div>
                        <div class="meters">
                           <div class="bar bar-color-03" 
                           id="bar-color-id" style="width:<%= member.getMeLike() %>%;"></div>
                        </div>
                        <div class="temperature-face">
                           <img id="temperature-face-id" src="/comi/resources/images/launcher.png" />
                        </div>
                        
                     </div>
                  </div>
               </a>
            </div>
         </section>
         <!-- 프로필 end-->
         
         <!-- 게시글 -->
         <section id="article-description">
            <div class="article-title-box">
               <h1 id="article-title">
                  <%= party.getPaTitle() %>
               </h1>
               <button class="report_btn">
                  ⋮
               </button>
            </div>
            <div class="article-dropdown">
               <button class="article-report-btn" onclick="report()">신고</button>
            </div>
            
            <p id="article-category">
               <%= category_check %><br>
               <span id="article-befordate">
                  <%= party.getPaTime() %>
               </span>
            </p>
            
            <p id="article-location">
               <%if(party.getPaLocation() == null){%>
                  모임의 장소가 없어요
               <%}else{%>
                  <a href="javascript:popup()"><%= party.getPaLocation() %></a>
               <%}%>
               
               <script>
                  function report(){
                     var reportpartyname = '<%= party.getPaTitle()%>'
                     var reportpartynum = '<%= party.getPaNum()%>'
                     alert(reportpartyname + " 파티를 신고했습니다.")
                     location.href = "/comi/report?num=" + reportpartynum + "&reportpartyname=party";
                  }
                  function popup(){
                     //창 크기 지정
                  var width = 500;
                  var height = 500;
                  
                  //pc화면기준 가운데 정렬
                  var left = (window.screen.width / 2) - (width/2);
                  var top = (window.screen.height / 4);
                  
                     //윈도우 속성 지정
                  var windowStatus = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=yes, titlebar=yes';
                  
                     //연결하고싶은url
                     const url = "/comi/views/common/location.jsp?local=<%=party.getPaLocation()%>";

                  //등록된 url 및 window 속성 기준으로 팝업창을 연다.
                  window.open(url, "hello popup", windowStatus);
                  }
               </script>
            </p>
            <div id="article-price-box">
               
               <div id="article-deposit">예치금 : <%= party.getPaDeposit() %>원</div>
               <div id="article-bar-box">
                  <span id="article-price-bar"></span>
                  <span id="article-deposit-bar" style="width:<%= per %>%">
                  </span>
                  <span class="article-pay-cursor">결제</span>
               </div>
               <div id="article-price">가격 : <%= party.getPaTotalAmount() %>원</div>
      
               <div class="article-people-box">
                  <div id="article-people">모집인원 <%= party.getPaTotalNum() %>명</div>
                  <div id="article-people">현재인원 <%= totalCount %>명</div>
                  <div id="article-people-price">인당 <%= party.getPaPerAmount() %>원</div>
               </div>
            </div>
            <div id="article-detail">
               <p>
                  <%= party.getPaCon() %>
               </p>
            </div>
            <p id="article-counts">
               찜 <%= party.getPaLike() %> ∙ 조회 <%= party.getPaViews() %>
            </p>

         </section>
         <!-- 게시글 end-->

         <!--결제창-->
         <div class="view-payment-pop">
            <div class="view-payment-p">
               <% if(loginMember != null) { 
                     if(loginMember.getMeNum() == member.getMeNum()) { %>
                        파티장 - 결제금액 : <%= party.getPaDeposit() %>원
                  <% }else{ %>
                        파티원 - 결제금액 : <%= party.getPaPerAmount() %>원
                  <% } %>
               <% } %>
            </div>
            <!-- <input type="button" class="view-payment-btn" value="결제하기"> -->
            <div class="view-payment-pop-box">
               <div id="payment-method"></div>
               <button id="payment" class="party-pay-btn">결제하기</button>
            </div>
            
         </div>


         <!-- 결제 페이지 파티장과 회원이 같을때만 보이기 -->

         <div class="view-payment-box <%= (party.getPaAct().equals("Y")) ? "" : "complete-ok" %>">
            
	         
             <% if(loginMember != null) { 
            	   if(party.getPaAct().equals("Y") && partyMemberType == "host") { %>
 	         		<button id="complete_btn" class="party-click-btn">파티완료</button>
 	         	<% } %>
               	<% //out.print("partyMemberType : " + partyMemberType + " party.getPaAct() : " + party.getPaAct());
	               if(partyMemberType != "none" && party.getPaAct().equals("Y")) { %>
                     <% if(totalPartyList != null) { %>
                        <% for(TotalParty tp : totalPartyList) {
                           //out.print("loginMember.getMeNum() : " + loginMember.getMeNum());
                           //out.print("tp.getMeNum() : " + tp.getMeNum());
            	            //out.print("tp : " + tp.getPayOk());
                        %>
                           <% if(loginMember.getMeNum() == tp.getMeNum() && tp.getPayOk().equals("N")){%>
                              <button id="paymentPop" class="party-click-btn">결제하기</button>
                           <% 
                                 break;
                              } %>
                        <% } %>   

                     <% } %>
	            	   
	            	<% } %>
            <% } %>
            <!-- <span id="paymentEnd">결제완료</span> -->
         </div>
         
         
         <!-- 결제 페이지 end -->
         <% if(loginMember != null) { %>
         <script type="text/javascript">
         var partyMemberTypeJs = <%= "\"" + partyMemberType + "\"" %>;
         
         function generateRandomString() {
            return window.btoa(Math.random()).slice(0, 20);
         }

         $(function(){
            console.log('!!!!!!!!!!!!!!!!!!!!!!');
            //결제
            var paymentWidget = PaymentWidget(
               "test_ck_6BYq7GWPVv5nNOzL6dw3NE5vbo1d",
               PaymentWidget.ANONYMOUS
            );

            var paymentMethodWidget;
            var depositBool = (partyMemberTypeJs == 'host') ? 'Y' : 'N';
            var price = (partyMemberTypeJs == 'host') 
                              ? Number(<%= "\"" + party.getPaDeposit() + "\"" %>)
                              : Number(<%= "\"" + party.getPaPerAmount() + "\"" %>);//결제금액
            var orderId = generateRandomString();             
            $('#paymentPop').on('click', function(){
               $.ajax({
                  url : '/comi/partypay'
                  ,type : 'post'
                  ,dataType : 'json'
                  ,data : {
                     pa_num : Number(paNum)
                     ,me_num : Number(<%= "\"" + loginMember.getMeNum() + "\"" %>)
                  }
                  ,success : function(json){
                     console.log('파티 불러오기 완료');

                     var jsonstr = JSON.stringify(json);
                     var jsonData = JSON.parse(jsonstr);

                     var databasePrice = (partyMemberTypeJs == 'host') ? jsonData.deposit : jsonData.peoplePrice;
                     console.log('price : ' + price + '  databasePrice : ' + databasePrice);
                     if(price == databasePrice) {//결제 금액과 테이블 저장된 금액이 맞을때
                        //price = 100;
                        paymentMethodWidget = paymentWidget.renderPaymentMethods("#payment-method", { value: price });
                        console.log('파티 금액 맞음 완료');
                        $('#paymentPop').hide();
                        $('.view-payment-pop').show();
                        
                     }
                  },error : function(error){
                        console.log('저장안됨');
                  }
               });
               
            });

            $('#payment').click(function(){

               paymentWidget.requestPayment({
                  amount: price,
                  orderId: orderId,
                  orderName: <%= "\"" + party.getPaTitle() + "\"" %>,
                  //successUrl: ,
                  //failUrl: url + '&pay=no',
                  customerEmail: <%= "\"" + loginMember.getMeEmail() + "\"" %>,
                  customerName: <%= "\"" + loginMember.getMeName() + "\"" %>
               })
               .then(function (res) {
                  // 성공 처리: 결제 승인 API를 호출하세요
                  
                  console.log('결제가 성공적으로 처리되었습니다. res.amount : ' 
                  + res.amount + ' price : ' + price + '  res.paymentKey : ' + res.paymentKey
                  + '  res.orderId : ' + res.orderId);
                  
                  if(res.amount == price) {
                     //결제 정보와 받아온 최종 데이터가 맞으면 데이터 테이블에 저장함
                     //최종승인 후 테이블에 저장
                     
                     axios({
                        url: 'https://api.tosspayments.com/v1/payments/confirm',
                        method: 'post',
                        headers : {
                           Authorization : 'Basic dGVzdF9za19QQmFsMnZ4ajgxUHZaWTU3MFlrcjVSUWdPQU5EOg==',
                           'Content-Type': 'application/json'
                        },
                        data: {
                           paymentKey : res.paymentKey
                           ,amount : res.amount
                           ,orderId : res.orderId
                        }
                     })
                     .then(function a(response) {
                        console.log('최종승인');
                        console.log('totalAmount : ' + response.data.totalAmount 
                         + ' method : '+ response.data.method);
                        $.ajax({
                           url : '/comi/payins'
                           ,type : 'post'
                           ,dataType : 'json'
                           ,data : {
                              
                              pa_num : paNum
                              ,me_num : Number(<%= "\"" + loginMember.getMeNum() + "\"" %>)
                              ,pm_amount : response.data.totalAmount//결제금액
                              ,pm_host : depositBool
                              ,pm_deposit : depositBool
                              ,pm_method : response.data.method
                              
                           }
                           ,success : function(result){
                              var jstr = JSON.stringify(result);
                              var res = JSON.parse(jstr);
                              console.log('결제 저장완료 result : ' + result);

                              //토탈파티 업데이트 후 파티의 페이체크도 업데이트 해야 함
                              location.href = '/comi/totalpartyup?panum='+paNum+'&price='+response.data.totalAmount+'&menum=' + Number(<%= "\"" + loginMember.getMeNum() + "\"" %>)
                              
                              //location.href = '/comi/partysel?panum='+paNum+'&act=Y';
                              //저장
                              //$('.view-payment-box').hide();
                              //$('#paymentPop').hide();
                              //$('#paymentEnd').show();
                           },error : function(error){
                              console.log('저장안됨');
                           }
                        });
                        
                     })
                     .catch(function (error) {
                        console.log(error);
                     });
                  }

                  
               })
               .catch(function (error) {
                  // 에러 처리: 에러 목록을 확인하세요
                  console.log('error.code : ' + error.code);
                  // https://docs.tosspayments.com/reference/error-codes#failurl로-전달되는-에러
                  if (error.code === 'USER_CANCEL') {
                  // 결제 고객이 결제창을 닫았을 때 에러 처리
                  } else if (error.code === 'INVALID_CARD_COMPANY') {
                  // 유효하지 않은 카드 코드에 대한 에러 처리
                  }
                  
               })
            })
            
            $('.view-payment-pop').on('click', function(){
               //$(this).hide();
               //return false;
            })
            

         })
         </script>
         <% } %><!--end if(loginMember!=null)-->

         <% if(type.equals("findReview")) { //종료된 모임 %>
            <%@ include file="./view_reply.jsp" %>
         <% }else { //종료되지 않은 모임 %>
            <% if(loginMember != null) { %>
               <% int temp = 0; %>
               <% for(TotalParty tp : totalPartyList) {%>
                  <% if(loginMember.getMeNum() == tp.getMeNum()){%>
                     <% temp = 1;%>
                  <% } %>
               <% } %>
               <%if(temp == 1){%>
                  <%@ include file="./view_chat.jsp" %>
               <%}else if(temp != 1){%>
                  <% if(loginMember.getMeNum() != member.getMeNum()) { %>
                  <section class="joinparty-allow">
                     <button class="joinparty-allow-btn" id="sendButton" onclick="joinParty()">참여하기</button>
                     <script>
                        function joinParty(){
                              location.href = "/comi/joinparty?loginmember=<%= loginMember.getMeNum() %>&party=<%= party.getPaNum() %>&act=<%= party.getPaAct() %>";
                           //alert("참여할 수 없습니다.");
                        }
                     </script>
                  </section> 
                  <% } %> 
               <% } %> 
            <%} %>
         <% } %>
                  
         <!-- 공유 모임 더 보기 -->
         <section class="article-party-share">
            <div class="article-party-share-box">
               <div class="article-party-share-title">인기공유모임</div>
               <a id="article-party-share-re" href="/comi/partysall?type=findParty">더보기</a>
            </div>
      
            <div class="main_portfolio" id="portf_box">
               <%@ include file="./view_list.jsp" %>
            </div>
         </section>
         <!-- 공유 모임 더 보기 end-->

      </div>

      

   </main>
   

   <!-- Footer Section Begin -->
   <footer id="footer_view">
      <%@ include file="../common/footer.jsp" %>   
   </footer>
   <!-- Footer Section End -->
</body>
</html>