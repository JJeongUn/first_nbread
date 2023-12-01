<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ page import="party.model.vo.Party"%>
<%@ page import="java.util.ArrayList"%>

<%
   Member member = (Member)request.getAttribute("member");
   int notecount = (Integer)request.getAttribute("notecount");
   int followersCount = (Integer)request.getAttribute("followersCount");
   ArrayList<Party> myReviewParty = (ArrayList<Party>)request.getAttribute("myReviewParty");
   
   
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, user-scalable=no" />
<title>nbread</title>
<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
   href="/comi/views/myinfo/myinfo.css" />
   <script>
            //인기도 증가  
            
      function popup(){
        //창 크기 지정
      var width = 500;
      var height = 500;
      
      //pc화면기준 가운데 정렬
      var left = (window.screen.width / 2) - (width/2);
      var top = (window.screen.height / 4);
      
        //윈도우 속성 지정
      var windowStatus = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=yes, titlebar=yes';
      const url = "/comi/views/note/note.jsp?sendnum="+"<%=member.getMeNum()%>";

      //등록된 url 및 window 속성 기준으로 팝업창을 연다.
      window.open(url, "hello popup", windowStatus);
      }
      function popup2() {
         // notelist를 사용하여 필요한 작업을 수행할 수 있습니다.
         // 예를 들어, 쪽지함 팝업을 열 때 이 데이터를 활용합니다.
         
         // 이제 notelist를 사용하여 필요한 작업을 수행할 수 있습니다.
         // 예를 들어, 쪽지함 팝업을 열 때 이 데이터를 활용합니다.
         //console.log(notelist);

         //창 크기 지정
         var width = 500;
         var height = 500;
         
         //pc 화면 기준 가운데 정렬
         var left = (window.screen.width / 2) - (width/2);
         var top = (window.screen.height / 4);
         
         //윈도우 속성 지정
         var windowStatus = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=yes, titlebar=yes';
         
         //연결하고 싶은 URL
         var url = "/comi/notelist?menum=<%= member.getMeNum() %>";

         //등록된 URL 및 window 속성 기준으로 팝업창을 연다.
         window.open(url, "hello popup", windowStatus);
      }
      function report(){
         var reportmember = '<%= member.getMeNum() %>';
         var reporaka = '<%= member.getMeAka() %>'
         alert(reporaka + "님을 신고 했습니다.")
         location.href ="/comi/report?menum="+reportmember;
      }

   </script>

<style> 
 
 a:link{
 color: black;
 }
 a:visited {
 color: #2E2E2D;
 }
 a:hover {
 color:    #FF9B36;
 }
 a:active {
 color:#FFE438;
 }

.category{
    display: flex;
    text-align: center;
    align-content: stretch;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

</style>



</head>

<body>
   <header>
      <%@ include file="../common/header.jsp"%>
   </header>
   <div class="myinfo_container">
   
   <nav class="infonav" >
      <ul>
         <br>
         <%if(member.getMeNum() == loginMember.getMeNum()){%>
         <button class ="note" onclick="popup()" >
            <!-- <a href="javascript:popup()">  -->쪽지 보내기</a> </button>
            <br><br>
         <button class ="note" onclick="javascript:popup2();" >
            <%if(notecount == 0){%>
               받은 쪽지함
            <%}else{%>
               <%=notecount%>개의 신규 쪽지
            <%}%>
                  </a> </button>
         <br><br>
         <!-- <button class ="note">포인트
               <%=loginMember.getMePoint()%>원 </button>  -->
         <%}else{%>
            <label onclick="report()">
               <button class ="note">신고하기</button>
            </label> <br><br><br>
            <label for="" onclick="javascript:location.href='/comi/block?block=<%=member.getMeNum()%>&menum=<%= loginMember.getMeNum()%>'">
               <button class ="note">차단하기</button>
            </label>
         <%}%>
      </ul>
   </nav>

   
      <section class="myinfo_main" >
      
         <div class="myinfo_main-top">
            <h1>
               &nbsp;&nbsp;<%=member.getMeAka()%>님의 프로필
            </h1>
         

            <div class="myinfo_detail">
               <div class="card">
                  
                  <div class="myinfo_photos">
                     <% if(member.getMePhotoAdd() == null){ %>
                        <% if(member.getMeNum() == loginMember.getMeNum()){ %>
                           <label onclick="javascript:location.href='/comi/mphoto?meid=<%=member.getMeId()%>';">
                        <%}else{%>
                           <label>
                        <%}%>
                           <img class="myinfo_photo" src="/comi/resources/images/launcher.png">
                        </label>
                     <%}else{%>
                        <% if(member.getMeNum() == loginMember.getMeNum()){ %>
                           <label onclick="javascript:location.href='/comi/mphoto?meid=<%=member.getMeId()%>';">
                        <%}else{%>
                           <label>
                        <%}%>
                           <img class="myinfo_photo" src="/comi/resources/profile_photo_upfiles/<%=member.getMeNum()%>/<%=member.getMePhotoAdd()%>">
                        </label>
                     <% } %>
                     
                  </div>
            
                  <h4>
                     <%=member.getMeAka()%>
                  </h4>
                  <% if(member != null){ %>
                     <%if(member.getMeNum() == loginMember.getMeNum()){%>
                     <button class="profile_edit"
                        onclick="javascript:location.href='/comi/moveup?meid=<%=member.getMeId()%>';">프로필편집
                     </button>
                     <%}%>
                  <% } %>
               </div>

               <div class="category" >

                  <h3>#<%=member.getCatNum()%></h3>
                  <table >
                     <tr>
                        <td>#<%=member.getCatNum()%></td>
                        <td>#<%=member.getCatNum()%></td>
                     </tr>
                     <tr>
                        <td>#<%=member.getCatNum()%></td>
                        <td>#<%=member.getCatNum()%></td>
                     </tr>


                  </table>
                  <br>
                  <%if(member.getMeNum() == loginMember.getMeNum()){%>
                  <button class="category_btn"
                     onclick="javascript:location.href='/comi/movecategory?meId=<%=member.getMeId()%>';">관심사
                     편집</button>
                     <%}%>
               </div>
               <div class="like">

               

               </script type="text/javascript">
                     <h3>인기도</h3>
                  <p>
                     <%=member.getMeLike()%>점
                  </p>
                  <button onclick="likeCount()" id="likeButton" class="like_button" style="background-color:transparent"  > 
                  <img src="/comi/resources/images/like_resize.png" class="like_button_icon" >
                  <!-- Like --></button>
               </div>
               <div class="following">

                  <h3>팔로잉</h3>
                 <h2> <p id="following"><%= followersCount%></p> </h2>
                  <%if(!(loginMember.getMeNum() == member.getMeNum())){%>
                     <button class ="note" id="followingButton" onclick="following()">팔로잉</button>
                  <%}%>
                  <script>
                     $(document).ready(function(){
            // JSP에서 likeCount와 likeMember 값을 JavaScript 변수로 가져옵니다.
            var likeCount = '<%= member.getMeLike() %>';
            var likeMember = '<%= member.getMeNum() %>';
            var following = '<%= member.getMeNum() %>';
            var followers = '<%= loginMember.getMeNum()%>';
            
            $('#likeButton').click(function(){
               // likeCount와 likeMember 변수를 사용하여 AJAX 요청을 보냅니다.
               $.ajax({
                     url: "/comi/like",
                     type: "GET",
                     data: { 
                        likeCount: likeCount,
                        likeMember: likeMember // likeMember도 서버로 전송합니다.
                     },
                     dataType: "json",
                     success: function(response) {
                        // 서버에서의 응답을 처리합니다
                        document.querySelector(".like p").innerText = likeCount + 1 + "점"; // 표시된 like 수를 업데이트합니다
                        //인기도 처리해야함
                     },
                     error: function(xhr, status, error) {
                        // 오류 처리 (있는 경우)
                        console.error("Ajax 요청이 실패했습니다: " + error);
                     }
               });
            });
            $('#followingButton').click(function(){
               // likeCount와 likeMember 변수를 사용하여 AJAX 요청을 보냅니다.
               $.ajax({
                     url: "/comi/following",
                     type: "GET",
                     data: { 
                        following: following,
                        followers: followers // likeMember도 서버로 전송합니다.
                     },
                     dataType: "json",
                     success: function(response) {
                        // 서버에서의 응답을 처리합니다
                        document.querySelector("#following").innerText = followersCount + 1 + "명"; // 표시된 like 수를 업데이트합니다
                     },
                     error: function(xhr, status, error) {
                        // 오류 처리 (있는 경우)
                        console.error("Ajax 요청이 실패했습니다: " + error);
                     }
               });
            });
         });

                  </script>
               </div>
            </div>
         </div>
         <!-- 하단-->
         <br> 
         <section class="review_recommend">

            <div class="course-box">

               <div class="course">
                  <div class="box_comment">
                     <table class="box_comment_table" align="center">
                        <thead>
                           <tr  >
                              <th class="tg-0lax" ><h3 style="text-align: center;"><%=member.getMeAka()%>님의 모임에 대한 후기</h3>
                              </th>
                           </tr>
                        </thead>

                        <tbody class="comment-click">
                           <%for(Party p : myReviewParty){%>
                              <tr>
                                 <td ><a href="/comi/partysel?panum=<%= p.getPaNum()%>&act=N" style="text-decoration-line: none;" ><%= p.getPaTitle()%></a></td>
                              </tr>
                           <%}%>
                        </tbody>
                     </table>

                     <br>
                  <!--    <button class="continue_btn">continue</button> -->

                  </div>

                  <div class="box_recommend">

                     <table class="recommend"  align="center">
                        <thead>
                           <tr>
                        <th class="recommend_title" colspan="2"><h3>새로운 공유 활동 추천</h3></th>
                           </tr>
                        </thead>
                        <tbody>
                           <tr>
                              <td class="tg-0lax"></td>
                              <td class="tg-0lax"></td>
                           </tr>
                           <tr>
                              <td class="tg-0lax"></td>
                              <td class="tg-0lax"></td>
                           </tr>
                           <tr>
                              <td class="tg-0lax"></td>
                              <td class="tg-0lax"></td>
                           </tr>
                        </tbody>
                     </table>
                     <br>
                  <!--    <button class="continue_btn" >continue</button> -->

                  </div>
               </div>
            </div>
         </section>
      </section>

   </div>
   <footer>
      <%@ include file="../common/footer.jsp"%>
   </footer>
</body>

</html>
