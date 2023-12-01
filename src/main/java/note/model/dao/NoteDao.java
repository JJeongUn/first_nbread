package note.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import note.model.vo.Note;
import static common.JDBCTemplate.*;
public class NoteDao {

	public Note selectMessage(Connection conn, int messageid) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Note> selectMessageAll(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertMessage(Connection conn, Note message) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "insert into note values ((select max(no_num)+1 count from note),?,SYSDATE,'N',?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, message.getNoCon());
			pstmt.setInt(2, message.getSendNum());
			pstmt.setInt(3, message.getRecvNum());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
                close(pstmt);
				System.out.println("notedao insertMessage pstmt 닫힘");
            }
		}		
		
		return result;
	}

	public int updateMessage(Connection conn, Note message) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteMessage(Connection conn, Note message) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int hasMember(Connection conn, String recvNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select case when exists ( " + //
				"    select 1 from member " + //
				"    where me_aka = ?) " + //
				"then 'Y' else 'N' end hasMember " + //
				"from dual";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, recvNum);
			rset = pstmt.executeQuery();
			if(rset.next()){
				if(rset.getString(1).equals("Y")){
					result = 1;
					System.out.println("notedao : result = " + result);
				}
			}

		} catch (Exception e) {
			System.out.println("error : NoteDay.hasMember");
			e.printStackTrace();
		} finally{
			close(rset);
			System.out.println("notedao hasMember rset 닫힘");
			close(pstmt);
			System.out.println("notedao hasMember pstmt 닫힘");
		}
		return result;
	}

    public ArrayList<Note> selectNoteAll(Connection conn, int menum) {
        ArrayList<Note> list = new ArrayList<Note>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from note where recv_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, menum);
			rset = pstmt.executeQuery();

			while(rset.next()){
				Note note = new Note();
				note.setNoNum(rset.getInt("NO_NUM"));
				note.setNoCon(rset.getString("NO_CON"));
				note.setNoSenTime(rset.getDate("NO_SEN_TIME"));
				note.setNoCh(rset.getString("NO_CH"));
				note.setSendNum(rset.getInt("SEND_NUM"));
				note.setRecvNum(rset.getInt("RECV_NUM"));
				list.add(note);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
    }

    public int noteCount(Connection conn, int meNum) {
		int noteCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
        String query = "select count(*) from note where recv_num = ? and no_ch = 'N'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meNum);
			rset = pstmt.executeQuery();

			if(rset.next()){
				noteCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return noteCount;
    }

	public Note selectNote(Connection conn, int noNum) {
		Note note = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from note where no_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noNum);	
			rset = pstmt.executeQuery();

			if(rset.next()){
				note = new Note();
				note.setNoNum(rset.getInt(1));
				note.setNoCon(rset.getString(2));
				note.setNoSenTime(rset.getDate(3));
				note.setNoCh(rset.getString(4));
				note.setSendNum(rset.getInt(5));
				note.setRecvNum(rset.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return note;

	}

	public int noteCheck(Connection conn, String string, int noNum) {
		int noteCheck = 0;
		PreparedStatement pstmt = null;
		String query = "update note set no_ch = ? where no_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, string);
			pstmt.setInt(2, noNum);
			noteCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return noteCheck;
	}

}
