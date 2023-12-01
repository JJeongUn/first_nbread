<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
    Member member = (Member)request.getAttribute("member");
    Member loginMember = (Member)session.getAttribute("loginMember");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정 페이지</title>
    <link rel="stylesheet" href="/comi/resources/css/member_update.css">
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #FFCC76;
            margin-top: 20px;
        }

        form {
            text-align: center;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table th {
            background-color: rgba(255, 237, 118, 0.6);
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        input[type="text"],
        input[type="radio"],
        input[type="tel"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"],
        .btn {
            background-color: #FFCC76;
            color: #000;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 15px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            width: 100px;
            margin-top: 3px;
            margin: 2px;
            text-align: center;
        }

        input[type="submit"]:hover,
        .btn:hover {
            background-color: #fca005;
            color: #fff;
        }

        a {
            text-decoration: none;
            text-align: center;
            color: black;
        }
        a:hover {
           color: #fff;
        }
    </style>
</head>
<body>
    <h1>회원 정보 수정 페이지</h1>
    <form action="/comi/mupdate" method="post">
        <table align="center" width="500" cellspacing="5" cellpadding="0">
            <tr>
                <th colspan="2">정보 변경 후 수정하기 버튼을 누르세요.</th>
            </tr>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" value="<%= loginMember.getMeName() %>" readonly></td>
            </tr>
            <tr>
                <th>아이디</th>
                <td><input type="text" name="id" value="<%= loginMember.getMeId() %>" readonly></td>
            </tr>
            <tr>
                <th>별명</th>
                <td><input type="text" name="aka" value="<%= loginMember.getMeAka() %>"></td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="gender" value="<%= loginMember.getMeGender() %>" checked> 남자 &nbsp; 
                    <input type="radio" name="gender" value="<%= loginMember.getMeGender() %>"> 여자
                </td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td><input type="tel" name="phone" value="<%= loginMember.getMePhone() %>"></td>
            </tr>
            <tr>
                <th>이메일</th>
                <td><input type="email" name="email" value="<%= loginMember.getMeEmail() %>"></td>
            </tr>
        </table>
        <div class="meber-bottom-box">
            <input type="submit" value="수정하기" class="btn"> 
            <button class="btn"><a href="javascript:history.go(-1);">이전 페이지</a></button>
            <button class="btn"><a href="/comi/main.jsp">시작페이지</a></button>
            <button class="btn"><a href="/comi/views/member/member_delete.jsp">탈퇴하기</a></button>
        </div>
    </form>
    <script src="/comi/resources/js/member_update.js"></script>
    <footer id="footer_view">   
   <%@ include file="../common/footer.jsp" %>
   </footer>
</body>
</html>