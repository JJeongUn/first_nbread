<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="note.model.vo.Note"%>
<%@ page import="java.util.ArrayList"%>
<%
   ArrayList<Note> note = (ArrayList<Note>)request.getAttribute("notelist");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    
    <style>
body {
	background-color: #fff;
	margin: 0;
	padding: 0;
}

h2 {
	text-align: center;
	margin-top: 20px;
	color: #333;
}

h3 {
	text-align: center;
	margin-top: 10px;
	color: #333;
}

a {
	text-decoration: none;
	color: #007bff;
}


a:hover {
	text-decoration: underline;
	color: 	#9437FF;
}

.note-list {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}

.note-title {
   text-align: center; /* <div class="note-title"> 내의 내용을 가운데 정렬합니다. */
    margin: 20px;
}



</style>
    
    
    
    
    
    
    
</head>
<body>
    <h2>쪽지함</h2>
  <div class="note-title">
        <% if (note.size() > 0) { %>
            <% for (Note n : note) { %>
                <div class="note-link">
                    <a href="/comi/noselect?nonum=<%= n.getNoNum() %>"><%= n.getNoCon() %></a><br>
                </div>
            <% } %>
        <% } else { %>
            <h3>쪽지가 없습니다.</h3>
        <% } %>

</body>
</html>