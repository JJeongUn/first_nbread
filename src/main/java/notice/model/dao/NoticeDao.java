package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import notice.model.vo.Notice;
import static common.JDBCTemplate.*;

public class NoticeDao {

	public Notice selectNotice(Connection conn, int noticeid) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from notice where no_num = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeid);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				notice = new Notice();

				notice.setNoNum(rset.getInt("no_num"));
				notice.setMeNum(rset.getInt("me_num"));
				notice.setNoTitle(rset.getString("no_title"));
				notice.setNoCon(rset.getString("no_con"));
				notice.setNoEnroll(rset.getDate("no_enroll"));
				notice.setNoModDate(rset.getDate("no_mod_date"));
				notice.setNoDelDate(rset.getDate("no_del_date"));
				notice.setNoViews(rset.getInt("no_views"));
				notice.setNoComCount(rset.getInt("no_com_count"));
				notice.setNoPhotoNum(rset.getInt("no_ph_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return notice;
	}

	public ArrayList<Notice> selectNoticeAll(Connection conn, int startRow, int endRow) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * "  
				+ "from(select rownum rnum,no_num,me_num,no_title,no_con,no_enroll,no_mod_date, no_del_date,no_views,no_com_count,no_ph_num "
				+ "    from(select * from notice " 
				+ "        order by no_enroll desc, no_num desc)) " 
				+ "where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Notice notice = new Notice();

				notice.setNoNum(rset.getInt("no_num"));
				notice.setMeNum(rset.getInt("me_num"));
				notice.setNoTitle(rset.getString("no_title"));
				notice.setNoCon(rset.getString("no_con"));
				notice.setNoEnroll(rset.getDate("no_enroll"));
				notice.setNoModDate(rset.getDate("no_mod_date"));
				notice.setNoDelDate(rset.getDate("no_del_date"));
				notice.setNoViews(rset.getInt("no_views"));
				notice.setNoComCount(rset.getInt("no_com_count"));
				notice.setNoPhotoNum(rset.getInt("no_ph_num"));
				list.add(notice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertNotice(Connection conn, Notice notice, int photoNum) {
		// 공지사항 추가 부분
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		if(photoNum != -1) {
			query = "insert into notice " + //
					"values(seq_no_num.nextval,?,?,?,default, " + //
					"default, default, default, default " + //
					", ?)";	
		}else {
			query = "insert into notice " + //
					"values(seq_no_num.nextval,?,?,?,default, " + //
					"default, default, default, default " + //
					", default)";
		}
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, notice.getMeNum());
			pstmt.setString(2, notice.getNoTitle());
			pstmt.setString(3, notice.getNoCon());
			if(photoNum != -1) {
				pstmt.setInt(4, photoNum);
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update notice " + 
				"set no_title = ?, no_con = ? , no_mod_date = sysdate " + 
				"where no_num = ?";
		//수정가능한 항목 제목 내용 수정 날짜 데이터
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoTitle());
			pstmt.setString(2, notice.getNoCon());
			pstmt.setInt(3, notice.getNoNum());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int deleteNotice(Connection conn, int noNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from notice where no_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int loadNotice(Connection conn, Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Notice> searchNotice(Connection conn, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select count(*) from notice";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

    public int addReadCount(Connection conn, int noNum) {
        int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update notice set no_views = no_views + 1 where no_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noNum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
    }
}
