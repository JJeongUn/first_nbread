package chat.model.service;

import chat.model.dao.ChatDao;
import chat.model.vo.Chat;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
public class ChatService {

    ChatDao dao = new ChatDao();

	public ChatService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insertChat(Chat chat) {
		Connection conn = getConnection();
		int result = 0;
		result = dao.insertChat(conn,chat);

		if(result > 0){
			commit(conn);
		}else{
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Boolean hasChat(int panum) {
		Connection conn = getConnection();
		Boolean hasChat = false;
		int result = dao.hasChat(conn,panum);

		if(result > 0){
			hasChat = true;
		}
		close(conn);
		return hasChat;
		
	}

	public ArrayList<Chat> selectChatList(int panum) {
		Connection conn = getConnection();
		ArrayList<Chat> chatList = dao.selectChatList(conn,panum);
		close(conn);
		return chatList;
	}
    
}
