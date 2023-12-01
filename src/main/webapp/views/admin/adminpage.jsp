<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="member.model.vo.Member"%>
<%@ page import="party.model.vo.Party"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="report.model.vo.Report"%>
<%
    ArrayList<Party> partylist = (ArrayList<Party>) request.getAttribute("partylist");
    ArrayList<Member> memberlist = (ArrayList<Member>) request.getAttribute("memberlist");
    ArrayList<Report> reportlist = (ArrayList<Report>) request.getAttribute("reportlist");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
	<link rel="stylesheet" href="/comi/resources/css/main.css"/>
	<link rel="stylesheet" href="/comi/resources/css/review.css"/>
	<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="/comi/resources/js/common.js"></script>
	<script type="text/javascript" src="/comi/resources/js/question.js"></script>
 	<link rel="stylesheet" href="/comi/resources/css/noticeView.css"/> 
    <title>Document</title>
    <style>
        /* 초기에는 모든 관리 페이지와 신고 항목을 숨깁니다. */
        .management-page {
        display: none;
        }

        /* 선택된 관리 페이지를 표시합니다. */
        .management-page.active {   
        display: block;
        }

        .report-section h4 {
        margin-top: 20px;
        }

        /* 선택된 신고 항목을 표시합니다. */
        .report-section h4.active {
        display: block;
        }
             
    </style>
    <script>

        
        document.addEventListener("DOMContentLoaded", function() {
        // 라디오 버튼의 변경 이벤트를 감지하고 관리 페이지를 표시합니다.
            document.querySelectorAll('input[name="menu"]').forEach(function(radio) {
                radio.addEventListener('change', function() {
                    var selectedMenu = this.value;
                    console.log("selectedMenu : " + selectedMenu);
                // 선택한 관리 페이지만 표시합니다.
                    document.querySelectorAll('.management-page').forEach(function(page) {
                        page.classList.remove('active');
                    });
                document.querySelector('#' + selectedMenu + '-page').classList.add('active');
                });
            });
        });
        function changeLogin(element){
            //radio 의 체크 상태가 변경된(change) input 태그의 name 속성값에서 userid 를 분리 추출함
            var usernum = element.name.substring(8);
            console.log("usernum : " + usernum);
            
            if(element.checked == true && element.value == 'false'){
                //제한을 체크한 경우
                console.log("로그인 가능을 체크함");
                location.href = "/comi/loginok?usernum=" + usernum + "&ok=true";
            }else{
                //가능을 체크한 경우
                console.log("로그인 제한을 체크함");
                location.href = "/comi/loginok?usernum=" + usernum + "&ok=false";
            }
        }
        function changeParty(element){
            //radio 의 체크 상태가 변경된(change) input 태그의 name 속성값에서 userid 를 분리 추출함
            var panum = element.name.substring(14);
            var act = element.name.charAt(4);
            console.log("panum : " + panum);
            console.log("act : " + act);
            if(element.checked == true && element.value == 'false'){
                //제한을 체크한 경우
                console.log("모임 가능을 체크함");
                location.href = "/comi/partyban?panum=" + panum + "&ban=true&act=" + act;
            }else{
                //가능을 체크한 경우
                console.log("모임 금지를 체크함");
                location.href = "/comi/partyban?panum=" + panum + "&ban=false&act=" + act;
            }
        }
    </script>
</head>
<body>
    <div class="container" style="text-align: center;">
    <header id="header_view">
        <%@ include file="../common/header.jsp" %>
     </header>
     <main>
        <div class="menu-select">
            <input type="radio" name="menu" value="party" checked> 파티 관리
            <input type="radio" name="menu" value="member"> 회원 관리
            <input type="radio" name="menu" value="delete"> 삭제 관리
            <input type="radio" name="menu" value="report"> 신고 관리
          </div>
          <div class="management-page" id="party-page">
            <h3>파티 관리</h3>
    <% for(Party p : partylist){ %>
        <%= p.getPaTitle()%>
        <%if (p.getPaAct().equals("Y")){%>
            모집중
        <%}else if(p.getPaAct().equals("N")){%>
            후기
        <%}else{ %>
            금지
        <%}%>
        <% if(p.getPaAct().equals("Y") || p.getPaAct().equals("N")){ %>
            <input type="radio" name="act_<%=p.getPaAct() %>partyban_<%= p.getPaNum() %>" value="true" 
            onchange="changeParty(this);" checked> 가능 &nbsp;
            <input type="radio" name="act_<%=p.getPaAct() %>partyban_<%= p.getPaNum() %>" value="false" 
            onchange="changeParty(this);" > 금지<br>
        <% }else{ %>
            <input type="radio" name="act_<%=p.getPaAct() %>partyban_<%= p.getPaNum() %>" value="true" 
            onchange="changeParty(this);" > 가능 &nbsp;
            <input type="radio" name="act_<%=p.getPaAct() %>partyban_<%= p.getPaNum() %>" value="false" 
            onchange="changeParty(this);" checked> 금지 <br>
        <% } %>
    <%}%>
    </div>
    <div class="management-page" id="member-page">
        <h3>회원 관리</h3>
    <% for(Member m : memberlist){ %>
        <%= m.getMeName()%>
        <%if( m.getMeLoginType().equals("N")){%>
            <%if(m.getMeBan().equals("N")){ %>
                <input type="radio" name="loginok_<%= m.getMeNum() %>" value="true" 
                onchange="changeLogin(this);" checked> 가능 &nbsp;
                <input type="radio" name="loginok_<%= m.getMeNum() %>" value="false" 
                onchange="changeLogin(this);" > 제한<br>
            <% }else{ %>
                <input type="radio" name="loginok_<%= m.getMeNum() %>" value="true" 
                onchange="changeLogin(this);" > 가능 &nbsp;
                <input type="radio" name="loginok_<%= m.getMeNum() %>" value="false" 
                onchange="changeLogin(this);" checked> 제한<br>
            <% } %>
        <% } %>
    <%}%>
    </div>
    <div class="management-page" id="delete-page">
        <h3>삭제 관리</h3>
        <% for(Member m : memberlist){ %>
            <%if( m.getMeLoginType().equals("Y")){%>
                <%= m.getMeName()%> &nbsp;
                <%
                Date currentDate = new Date(); // 현재 날짜
                Date modDate = m.getMeModDate(); // 회원 수정 날짜
                if (modDate.after(currentDate)) {
                    Date temp = currentDate;
                    currentDate = modDate;
                    modDate = temp;
                }
                long timeDifferenceMillis = currentDate.getTime() - modDate.getTime();
                long daysDifference = Math.abs(timeDifferenceMillis / (1000 * 60 * 60 * 24)); // 음수를 양수로 변환하여 일로 변환
                out.print("&nbsp;" + daysDifference + "일<br>");


                %>
                <%if(daysDifference > 10){ %>
                    <a href="/comi/membercomdel?usernum=<%= m.getMeNum() %>">완전삭제</a><br>
                <%}%>
            <% } %>
        <%}%>
    </div>
    <div class="management-page" id="report-page">
        <h3>신고 관리</h3>
        <h4>회원 신고</h4>
        <% for(Report r : reportlist){ %>
            <%if(r.getRpType().equals("member")){%>
                신고 넘버 : <%= r.getRpNum()%> / 신고 내용 <%=r.getRpCon()%><br>
            <%}%>
        <%}%>
        <h4>파티 신고</h4>
        <% for(Report r : reportlist){ %>
            <%if(r.getRpType().equals("party")){%>
                신고 넘버 : <%= r.getRpNum()%> / 신고당한 사람 :  <%=r.getRpCon()%><br>
            <%}%>
        <%}%>
        <h4>댓글 신고</h4>
        <% for(Report r : reportlist){ %>
            <%if(r.getRpType().equals("com")){%>
                댓글 테이블 넘버 <%= r.getRpNum()%> : <a href="/comi/partyupdate?conum=<%=r.getComNum()%>"><%=r.getRpCon()%></a><br>
            <%}%>
        <%}%>
    </div>
</div>
</main>
    <footer id="footer_view">
        <%@ include file="../common/footer.jsp" %>   
     </footer>
</body>
</html>