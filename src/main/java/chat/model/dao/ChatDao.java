package chat.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import chat.model.vo.Chat;
import static common.JDBCTemplate.*;

public class ChatDao {

	public ChatDao() {
		super();
		// TODO Auto-generated constructor stub
	}

    public int insertChat(Connection conn, Chat chat) {
        int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into chat values ((select max(chat_num)+1 from chat),?,?,?,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chat.getPaNum());
			pstmt.setString(2,chat.getMeName());
			pstmt.setString(3,chat.getChatCon());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
    }

	public int hasChat(Connection conn, int panum) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select case when exists  " +  
				"( select 1 from chat  where pa_num = ?)  " +
				"then 'Y' else 'N' end hasMember from dual";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			rset = pstmt.executeQuery();
			if(rset.next()){
				if(rset.getString(1).equals("Y")){
					result = 1;
				}else{
					result = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Chat> selectChatList(Connection conn, int panum) {
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from chat where pa_num = ? order by chat_num";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			rset = pstmt.executeQuery();

			while(rset.next()){
				Chat chat = new Chat();
				chat.setChatNum(rset.getInt(1));
				chat.setPaNum(rset.getInt(2));
				chat.setMeName(rset.getString(3));
				chat.setChatCon(rset.getString(4));
				chat.setChatDate(rset.getDate(5));
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return chatList;
	}
    
}