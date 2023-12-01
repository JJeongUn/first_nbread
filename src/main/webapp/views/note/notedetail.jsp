<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="note.model.vo.Note"%>
<%@ page import="member.model.vo.Member"%>
<%
    Note note = (Note)request.getAttribute("note");    
    Member sendmember = (Member)request.getAttribute("sendmember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nbread note</title>

<style>
body {
	font-family: Arial, sans-serif; /* 폰트 설정 */
	background-color: 	rgba(255, 248, 207, 0.2); /* 배경색 설정 */
	padding: 20px; /* 여백 설정 */
	max-width: 600px; /* 최대 너비 설정 */
	margin: 0 auto; /* 중앙 정렬 */
	border: 1px solid #ccc; /* 테두리 설정 */
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 설정 */
}

/* 특정 요소에 스타일을 적용하려면 요소의 클래스나 ID를 사용하세요. */
/* 예를 들어, 보낸 사람과 보낸 시간 스타일링을 위해 다음과 같이 클래스를 추가할 수 있습니다. */
.sender-info {
	font-weight: bold; /* 굵은 글꼴 설정 */
}

.sent-time {
	color: #777; /* 시간 텍스트의 색상 설정 */
}

.message-content {
	margin-top: 20px; /* 쪽지 내용 위쪽 여백 설정 */
	line-height: 1.2; /* 줄 간격 설정 */
}

.btn {
	background-color: #FFCC76;
	color: /* #917443; */ #000000;
	padding: 5px 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 15px;
	text-decoration: none;
	transition: background-color 0.3s ease;
	width: 100px;
	margin-top: 20px;
	margin: 2px;
	text-align: center;
}

.btn:hover {
	background-color: #fca005;
	color: #ffffff;
}

a {
	text-decoration-line: none;
	text-align: center;
	color: black;
}
</style>





</head>
<body>

    <p class="sender-info">보낸 사람 <%=sendmember.getMeAka()%></p>
    <p class="sent-time">보낸 시간 <%=note.getNoSenTime()%></p>

    <p class="message-content">
        쪽지내용 <br><br> <%=note.getNoCon()%>
    </p>

	   <button class="btn" onclick="window.close();">닫기</button>  &nbsp; 


</body>
</html>
