package note.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import note.model.dao.NoteDao;
import note.model.vo.Note;

public class NoteService {
	private NoteDao dao = new NoteDao();
	
	public NoteService() {}
	
	//조회 
	public Note selectMessage(int messageid) {
		Connection conn = getConnection();
		Note message = dao.selectMessage(conn, messageid);
		close(conn);
		return message;
	}
	
	//전체 조회
	public ArrayList<Note> selectMessageAll() {
		Connection conn = getConnection();
		ArrayList<Note> list = dao.selectMessageAll(conn);
		close(conn);
		return list;
	}
	
	//삽입
	public int insertMessage(Note message) {
		Connection conn = getConnection();
		int result = dao.insertMessage(conn, message);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	//수정
	public int updateMessage(Note message) {
		Connection conn = getConnection();
		int result = dao.updateMessage(conn, message);
		
		if(result > 0) commit(conn);
		else rollback(conn);
			
		close(conn);
		return result;
	}
	
	//삭제
	public int deleteMessage(Note message) {
		Connection conn = getConnection();
		int result = dao.deleteMessage(conn, message);
		
		if(result > 0) commit(conn);
		else rollback(conn);
			
		close(conn);
		return result;
	}

	public Boolean hasMember(String recvNum) {
		Boolean resultBoolean = false;
		Connection conn = getConnection();
		int result = dao.hasMember(conn,recvNum);
		if(result > 0){
			resultBoolean = true;
		}
		close(conn);
		return resultBoolean;
	}

    public ArrayList<Note> selectNoteAll(int menum) {
		Connection conn = getConnection();
        ArrayList<Note> noteList = dao.selectNoteAll(conn,menum);
		close(conn);
		return noteList;
    }

    public int noteCount(int meNum) {
		Connection conn = getConnection();
        int noteCount = dao.noteCount(conn,meNum);
		close(conn);
		return noteCount;
    }

	public Note selectNote(int noNum) {
		Connection conn = getConnection();
		Note note = dao.selectNote(conn,noNum);
		close(conn);
		return note;
	}

    public int noteCheck(String string, int noNum) {
		Connection conn = getConnection();
        int result = dao.noteCheck(conn,string, noNum);
		if(result >0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;

    }
}

