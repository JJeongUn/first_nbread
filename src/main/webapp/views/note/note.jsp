<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, note.model.vo.Note"%>   
<%
	// 쿼리 문자열을 가져옵니다.
	String queryString = request.getQueryString();

	// 쿼리 문자열이 null이 아닌 경우 "=" 이후의 값을 추출합니다.
	String valueAfterEquals = null;
	if (queryString != null) {
		int indexOfEquals = queryString.indexOf("=");
		if (indexOfEquals != -1) {
			valueAfterEquals = queryString.substring(indexOfEquals + 1);
		}
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nbread note</title>

<link rel="stylesheet" type="text/css" href="/comi/views/note/note.css"/>
<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
</head>


<body>

	<form id="note">
		<table id="outer" align="center" width="400" cellspacing="5"
			cellpadding="0">
			<tr>
				<th colspan="2">쪽지 쓰기</th>
				<input type="hidden" name="sendnum" value="<%= valueAfterEquals %>">
			</tr>
			<tr>
				<th width="120">받는 사람(별명)</th>
				<td><input type="text" name="recvnum" value=""></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="30" rows="10" name="note"></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="보내기">
					&nbsp; <input type="reset" value="취 소"> &nbsp;</th>
			</tr>
		</table>
	</form>
	<script>
		$(document).ready(function(){
			$('#note').submit(function(event){
				event.preventDefault();
				$.ajax({
					url : '/comi/noinsert',
					type : 'POST',
					data: $('#note').serialize(),
					success : function(data){
						if(data.isSuccess === "same"){
							alert("자기 자신에게 보낼수 없습니다.");
							window.close();
							// 실패한 경우에 수행할 코드
						} else if(data.isSuccess === "empty"){
							alert("받는 사람이 없습니다.");
							window.close();
						} else if(data.isSuccess === "fail"){
							alert("쪽지 전송 실패");
							window.close();
						} else{
							alert("쪽지 전송 완료");
							window.close();
						}
						
					},
					error : function(error,data){
						if(data.isSuccess === "same"){
							alert("error : 자기 자신에게 보낼수 없습니다.");
							window.close();
							// 실패한 경우에 수행할 코드
						} else if(data.isSuccess === "empty"){
							alert("error : 받는 사람이 없습니다.");
							window.close();
						} else if(data.isSuccess === "fail"){
							alert("error : 쪽지 전송 실패");
							window.close();
						} else{
							alert("error : 알수없는 에러");
							window.close();
						}
						// alert("쪽지 전송 에러");
						// window.close();
					}
				})
			});
		});
	</script>
</body>
</html>