<%@page import="chat.model.vo.Chat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>party_view</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/view_chat.css" />
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/util.js"></script>
</head>
<style>


</style>
<body>
    <!-- 채팅 리뷰-->
    <section id="chating">
        <!--채팅 헤드-->
        <div class="chating-head">
            <span class="chating-head-title">엔브레드톡</span>
        </div>
        <!--채팅 헤드 end-->

        <!--채팅 바디-->

        <div class="chating-body" id="chat">
            <div class="chating-body-box" id="chating_box">
 
             <!--채팅 바디end-->
             <% if(chatlist != null){%>
                <% 
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String previousName = null; // 이전 채팅을 한 사람의 이름을 저장하는 변수
                String previousTime = null; // 이전 채팅의 시간 정보를 저장하는 변수
                %>
                <% for (Chat ch : chatlist) { %>
                    <%if(loginMember.getMeName().equals(ch.getMeName()) || (loginMember.getMeAka() != null) && loginMember.getMeAka().equals(ch.getMeName())){%>
                        <%
                        //내 메시지
                            String currentTime = dateFormat.format(ch.getChatDate());
                            String imgSrc = "/comi/resources/images/launcher.png";
                            if (!ch.getMeName().equals(previousName)) {
                                //out.println("<span style=\"white-space: nowrap;\">" + currentTime + " </span>");
                            } else if (!currentTime.equals(previousTime)) {
                                //out.println("<span style=\"white-space: nowrap;\">" + currentTime + " </span>");
                            }
                            //out.println("<p>" + ch.getChatCon() + "</p>");
                            out.println("<div class=\"chating-body-msg my-msg\">"
                                +"<div class=\"chating-body-msg-w\">"
                                +    "<div class=\"chating-body-msg-profile\">"
                                +        "<img class=\"chating-body-msg-profile-img\" src=\""+ imgSrc +"\">"
                                +        "<div class=\"chating-body-msg-profile-name\">마이프레셔스</div>"
                                +    "</div>"
                                +    "<div class=\"chating-body-msg-box\">"
                                +        "<div class=\"chating-body-msg-box-read bubble-msg\">"
                                            
                                            + ch.getChatCon() 
                                            
                                +        "</div>"
                                +        "<div class=\"chating-body-msg-box-date\">"
                                            
                                            + currentTime 
                                            
                                +        "</div>"
                                +        "<button class=\"chating-body-msg-box-pop\">"
                                +            "<img class=\"chating-body-msg-box-pop-img\" src=\"/comi/resources/images/msg_pop_img.png\">"
                                +        "</button>"
                                +    "</div>"
                                +"</div>"
                            +"</div>");
                            previousName = ch.getMeName(); // 현재 이름을 이전 이름으로 저장
                            previousTime = currentTime; // 현재 시간을 이전 시간으로 저장
                        %>
                    <%}else{%>
                        <%
                            String currentTime = dateFormat.format(ch.getChatDate());
                            String imgSrc = "/comi/resources/images/launcher.png";
                            String continueTag = "continue-msg";
                            if (!ch.getMeName().equals(previousName)) {
                                continueTag = "";
                                //out.println("<span style=\"white-space: nowrap;\">" + currentTime + " </span>");
                            } else if (!currentTime.equals(previousTime)) {
                                //out.println("<span style=\"white-space: nowrap;\">" + currentTime + " </span>");
                            }
                            //out.println("<p>" + ch.getMeName() + " : " + ch.getChatCon() + "</p>");
                            out.println("<div class=\"chating-body-msg " + continueTag +"\">"
                                +"<div class=\"chating-body-msg-w\">"
                                +    "<div class=\"chating-body-msg-profile\">"
                                +        "<img class=\"chating-body-msg-profile-img\" src=\""+ imgSrc +"\">"
                                +        "<div class=\"chating-body-msg-profile-name\">"+ch.getMeName()+"</div>"
                                +    "</div>"
                                +    "<div class=\"chating-body-msg-box\">"
                                +        "<div class=\"chating-body-msg-box-read bubble-msg\">"
                                            
                                            + ch.getChatCon() 
                                            
                                +        "</div>"
                                +        "<div class=\"chating-body-msg-box-date\">"
                                            
                                            + currentTime 
                                            
                                +        "</div>"
                                +        "<button class=\"chating-body-msg-box-pop\">"
                                +            "<img class=\"chating-body-msg-box-pop-img\" src=\"/comi/resources/images/msg_pop_img.png\">"
                                +        "</button>"
                                +    "</div>"
                                +"</div>"
                            +"</div>");
                            
                            previousName = ch.getMeName(); // 현재 이름을 이전 이름으로 저장
                            previousTime = currentTime; // 현재 시간을 이전 시간으로 저장
                        %>
                    <%}%>
                <% } %>
            <%}%>
            </div>
        </div>
        <!--채팅 입력-->
        <div class="chating-input" id="chating-input-talk">
            <div class="chating-input-area">
                <input type="text" placeholder="엔브레드톡에 참여해보세요" id="messageInput" class="chating-input-area-write">
                <button type="button" class="chating-post-stickerbtn" id="sendButton">
                    <img class="chating-post-sendbtn-image" src="/comi/resources/images/send_btn.png">
                </button>
            </div>
            
        </div>
        <!--채팅 입력 end-->

        <!--채팅 바디end-->
    </section>
    <!--참여 버튼 버튼을 클릭해야 채팅을 할 수 있음 - 참여하지 않으면 채팅기록만 볼수 있음-->
    
    <script>
        function setHtmlTag(name, con, date, mychat, continued, img){
            let myTag = (mychat) ? 'my-msg' : '';
            let continueTag = (continued) ? 'continue-msg' : '';
            let imgSrc = (img == null || img == undefined) ? `/comi/resources/images/launcher.png` : img;
            let html = `
            <div class="chating-body-msg `+myTag+` `+continueTag+`">
                <div class="chating-body-msg-w">
                    <div class="chating-body-msg-profile">
                        <img class="chating-body-msg-profile-img" src="`+imgSrc+`">
                        <div class="chating-body-msg-profile-name">`+name+`</div>
                    </div>
                    <div class="chating-body-msg-box">
                        <div class="chating-body-msg-box-read bubble-msg">
                            `+con+`
                        </div>
                        <div class="chating-body-msg-box-date">
                            `+date+`
                        </div>
                        <button class="chating-body-msg-box-pop">
                            <img class="chating-body-msg-box-pop-img" src="/comi/resources/images/msg_pop_img.png">
                        </button>
                    </div>
                </div>
            </div>
            `;

            return html;
        }


        var partyNumber = '<%= party.getPaNum()%>';
        const socket = new WebSocket("ws://localhost:8080/comi/comisoket?partyNumber=" + partyNumber); // 웹 소켓 서버 주소와 포트로 연결
        var username = '<%= loginMember.getMeName()%>';
        var partyNumber = '<%= party.getPaNum()%>';
        // 이전 메시지의 사용자 이름을 저장할 변수
        let lastMessageUsername = null;
        // var name = member.getMename();
        //     console.log(name);
        socket.onopen = () => {
            console.log("웹 소켓 연결 성공");
            console.log(partyNumber);
        };

        socket.onmessage = (event) => {
            let message = JSON.parse(event.data);
            let messagesDiv = document.getElementById("chating_box");
            
            // 현재 사용자의 이름과 메시지를 보낸 사용자의 이름 비교
            if (message.username === username) {
                messagesDiv.innerHTML += 
                setHtmlTag(message.username, message.text, '', true, false);
            } else {
                // 이전 메시지의 사용자 이름과 현재 메시지의 사용자 이름 비교
                if (lastMessageUsername === null || lastMessageUsername !== message.username) {
                    messagesDiv.innerHTML += 
                    setHtmlTag(message.username, message.text, '', true, true);;
                } else {
                    messagesDiv.innerHTML += 
                    setHtmlTag(message.username, message.text, '', true, false);
                }
                
                // lastMessageUsername 업데이트
                lastMessageUsername = message.username;
            }
            // 스크롤을 항상 아래로 내립니다.
            scrollToBottom();
        };

        document.getElementById("sendButton").addEventListener("click", () => {
            messagInput();
        });

        function messagInput(){
            let messageInput = document.getElementById("messageInput");

            let message = messageInput.value;
            if (message.trim() !== "") {
                
                let dataToSend = {
                    partynumber: partyNumber,
                    username: username,
                    text: message,
                    
                };

                socket.send(JSON.stringify(dataToSend));

                messageInput.value = "";
            }
        }

        $('#messageInput').on('keyup', function(event){
            if ( event.keyCode == 13 || event.which == 13 ) {
                messagInput();
            }
        })

        function scrollToBottom() {
            const messagesDiv = document.getElementById("chating_box");
            messagesDiv.scrollTop = messagesDiv.scrollHeight;
        }

    </script>
    <!--참여 버튼 end-->
</body>

</html>