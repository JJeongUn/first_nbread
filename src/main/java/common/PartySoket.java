package common;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import chat.model.service.ChatService;
import chat.model.vo.Chat;

@ServerEndpoint("/comisoket")
public class PartySoket {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("웹 소켓 연결 개수: " + sessions.size());
    
        // 클라이언트 세션에 파티 번호를 설정
        String queryString = session.getQueryString();
        String partyNumber = queryString.substring(queryString.indexOf("partyNumber=") + "partyNumber=".length());
        session.getUserProperties().put("partynumber", partyNumber);
        System.out.println("참여한 파티 넘버 : " + partyNumber);
        }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // JSON 데이터를 파싱하여 JSON 객체로 변환
        Chat chat = new Chat();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonMessage = (JSONObject) parser.parse(message);
    
            // JSON 객체에서 필드 추출
            String partynumberStr = (String) jsonMessage.get("partynumber");
            int partynumberValue = Integer.parseInt(partynumberStr); 
            chat.setPaNum(partynumberValue);
            chat.setMeName((String) jsonMessage.get("username"));
            chat.setChatCon((String) jsonMessage.get("text"));
            
            int chatSaveChack = new ChatService().insertChat(chat);
    
            // 특정 파티 번호와 일치하는 클라이언트에게만 메시지 전송
            for (Session s : sessions) {
                String clientPartynumber = (String) s.getUserProperties().get("partynumber");
                if (clientPartynumber != null && clientPartynumber.equals(partynumberStr)) {
                    s.getBasicRemote().sendText(message);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }    
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("웹 소켓 연결 종료");
    }
}