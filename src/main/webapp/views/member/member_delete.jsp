<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
    Member member = (Member)request.getAttribute("member");
    Member loginMember = (Member)session.getAttribute("loginMember");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member_delete</title>
<script>
function checkAndSubmit() {
    // 체크박스 상태 확인
    var checkbox = document.getElementById("agreeCheckbox");
    if (checkbox.checked) {
        // 체크되었을 경우 제출
        document.getElementById("deleteForm").submit();
    } else {
        // 체크되지 않았을 경우 경고 메시지 출력
        alert("안내사항에 동의하지 않았습니다.");
    }
}
</script>
</head>
<body>
    <form id="deleteForm" action="/comi/mdelete" method="post">
        <input type="hidden" name="meid" value="<%= loginMember.getMeId() %>">
        
        <div id="delete_account_container">
            <h2>탈퇴 안내</h2>
            <li><a>회원 탈퇴를 신청하기 전에 주의 사항을 꼭 확인해주세요.</a></li> <br>
            <li><a>사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</a></li> <br>
            <li><a>탈퇴 후 회원 정보 및 서비스 이용기록, 포인트는 모두 삭제됩니다.</a></li> <br>
            <li><a>회원 정보, 이용기록, 포인트 모두 삭제되며 절대 복구되지 않습니다.</a></li> <br>
            
            <input type="checkbox" id="agreeCheckbox"><a>안내 사항을 모두 확인하였으며, 이에 동의합니다.</a> <br><br><br><br><br><br>
            
            <input type="button" value="탈퇴하기" onclick="checkAndSubmit()"> &nbsp; 
        </div>
    </form>
</body>
</html>