<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
    Member member = (Member) request.getAttribute("member");
    Member loginMember = (Member) session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>프로필 사진 수정 페이지</title>
    <style>
       /* 기본 스타일 */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
    color: #333; /* 텍스트 색상 변경 */
}

/* 제목 스타일 */
h1 {
    text-align: center;
    color: #FFCC76; /* 제목 색상 변경 */
    margin-top: 20px; /* 위 여백 추가 */
}

/* 폼 스타일 */
form.profile-form {
    text-align: center;
    margin: 20px auto; /* 가운데 정렬 및 상하 여백 추가 */
    padding: 20px;
    background-color: #FFCC76; /* 백그라운드 색상 추가 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
    border-radius: 5px; /* 모서리 둥글게 */
}

table.profile-table {
    margin: 0 auto;
}

th, td {
    padding: 10px;
    border-bottom: 1px solid #ccc; /* 테이블 셀 경계선 추가 */
}

/* 입력 필드 스타일 */
input[type="file"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px; /* 입력 필드 간격 조정 */
    border: 1px solid #ccc;
    border-radius: 3px;
}

/* 수정하기 버튼 스타일 */
input[type="submit"] {
    background-color: #FFCC76;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 3px;
    cursor: pointer;
    font-weight: bold;
}

/* 하단 링크 스타일 */
.member-bottom-box {
    text-align: center;
    margin-top: 20px;
}

.member-bottom-box a {
    margin: 0 10px;
    text-decoration: none;
    color: #FFCC76 /* 링크 색상 변경 */
}
table {
   margin: 50px auto;
   margin-bottom: 100px;
}
    </style>
</head>
<body>
    <h1>프로필 사진 수정 페이지</h1>
    <br>
    <form action="/comi/upphoto" method="post" enctype="multipart/form-data">
        <table id="outer" width="500" cellspacing="5" cellpadding="0">
            <tr>
                <th colspan="2">사진 변경 후 수정하기 버튼을 누르세요.</th>
            </tr>
            <tr>
                <th>프로필 사진</th>
                <td><input type="file" name="photo" value="<%= loginMember.getMePhotoAdd() %>"></td>
            </tr>
        </table>
        <div class="member-bottom-box">
            <input type="submit" value="수정하기">
            <a href="javascript:history.go(-1);">이전 페이지로 이동</a>
            <a href="/comi/main.jsp">시작페이지로 이동</a>
        </div>
    </form>
    <footer id="footer_view">   
   <%@ include file="../common/footer.jsp" %>
   </footer>
</body>
</html>