package qna.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.Paging;
import notice.model.vo.Notice;
import qna.model.vo.Qna;

public class QnaDao {
	public QnaDao() {}
	
	public Qna selectQna(Connection conn, int qanum) {
		// TODO Auto-generated method stub
		Qna qna = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from qa "
				+ "where qa_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qanum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qna = new Qna();
				
				qna.setQaNum(rset.getInt("qa_num"));
				qna.setMeNum(rset.getInt("me_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaPwd(rset.getString("qa_pwd"));
				qna.setQaCon(rset.getString("qa_con"));
				qna.setQaCategory(rset.getString("qa_category"));
				qna.setQaEnroll(rset.getDate("qa_enroll"));
				qna.setQaModDate(rset.getDate("qa_mod_date"));
				qna.setQaDelDate(rset.getDate("qa_del_date"));
				qna.setQaViews(rset.getInt("qa_views"));
				qna.setQaComCount(rset.getInt("qa_com_count"));
				qna.setQaAttDate(rset.getDate("qa_att_date"));
				qna.setQaPhotoNum(rset.getInt("qa_photo_num"));
				qna.setQaLev(rset.getInt("qa_lev"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return qna;
	}

	public ArrayList<Qna> selectQnaAll(Connection conn, int startPage, int endPage) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * "
				+ "from (select rownum, QA_NUM, ME_NUM, QA_TITLE, QA_PWD, QA_CON, QA_CATEGORY, QA_ENROLL, "
				+ "     QA_MOD_DATE, QA_DEL_DATE, QA_VIEWS, QA_COM_COUNT, "
				+ "     QA_ATT_DATE, QA_PHOTO_NUM, QA_LEV "
				+ "    from(select * "
				+ "        from qa "
				+ "        order by qa_enroll desc)) "
				+ "where rownum between ? and ?";
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, endPage);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQaNum(rset.getInt("qa_num"));
				qna.setMeNum(rset.getInt("me_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaPwd(rset.getString("qa_pwd"));
				qna.setQaCon(rset.getString("qa_con"));
				qna.setQaCategory(rset.getString("qa_category"));
				qna.setQaEnroll(rset.getDate("qa_enroll"));
				qna.setQaModDate(rset.getDate("qa_mod_date"));
				qna.setQaDelDate(rset.getDate("qa_del_date"));
				qna.setQaViews(rset.getInt("qa_views"));
				qna.setQaComCount(rset.getInt("qa_com_count"));
				qna.setQaAttDate(rset.getDate("qa_att_date"));
				qna.setQaPhotoNum(rset.getInt("qa_photo_num"));
				qna.setQaLev(rset.getInt("qa_lev"));
				
				list.add(qna);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertPartyCo(Connection conn, Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertQna(Connection conn, Qna qna) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into qa values (SEQ_QA_NUM.nextval,?,?,?,?,?,"
				+ "sysdate, default, default, default, "
				+ "default,default,default,default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, qna.getMeNum());
			pstmt.setString(2, qna.getQaTitle());
			pstmt.setString(3, qna.getQaPwd());
			pstmt.setString(4, qna.getQaCon());
			pstmt.setString(5, qna.getQaCategory());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateQna(Connection conn, Qna qna) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update qa "
				+ "set qa_title = ?, "
				+ "    qa_con = ? "
				+ "where qa_num = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, qna.getQaTitle());
			pstmt.setString(2, qna.getQaCon());
			pstmt.setInt(3, qna.getQaNum());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteQna(Connection conn, int qnaNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from qa where qa_num = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete");
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int loadQna(Connection conn, Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Qna> searchQna(Connection conn, String keyword) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from qa where qa_title like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();

			while(rset.next()){
				Qna qna = new Qna();

				qna.setQaNum(rset.getInt("qa_num"));
				qna.setMeNum(rset.getInt("me_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaPwd(rset.getString("qa_pwd"));
				qna.setQaCon(rset.getString("qa_con"));
				qna.setQaCategory(rset.getString("qa_category"));
				qna.setQaEnroll(rset.getDate("qa_enroll"));
				qna.setQaModDate(rset.getDate("qa_mod_date"));
				qna.setQaDelDate(rset.getDate("qa_del_date"));
				qna.setQaViews(rset.getInt("qa_views"));
				qna.setQaComCount(rset.getInt("qa_com_count"));
				qna.setQaAttDate(rset.getDate("qa_att_date"));
				qna.setQaPhotoNum(rset.getInt("qa_photo_num"));
				qna.setQaLev(rset.getInt("qa_lev"));
				
				list.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		//sql 가서 QNA 테이블에서 전체 복사해오기
		String query = "select count(*) from qa";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
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

	public ArrayList<Qna> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from (select rownum rnum, QA_NUM, ME_NUM, QA_TITLE, QA_PWD, QA_CON, "
				+ "     QA_CATEGORY, QA_ENROLL, QA_MOD_DATE, QA_DEL_DATE, QA_VIEWS, "
				+ "     QA_COM_COUNT, "
				+ "     QA_ATT_DATE, QA_PHOTO_NUM, QA_LEV"
				+ "     from (select * from qa order by QA_ENROLL desc)) "
				+ "where rnum >= ? and rnum <= ?";
			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQaNum(rset.getInt("qa_num"));
				qna.setMeNum(rset.getInt("me_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaPwd(rset.getString("qa_pwd"));
				qna.setQaCon(rset.getString("qa_con"));
				qna.setQaCategory(rset.getString("qa_category"));
				qna.setQaEnroll(rset.getDate("qa_enroll"));
				qna.setQaModDate(rset.getDate("qa_mod_date"));
				qna.setQaDelDate(rset.getDate("qa_del_date"));
				qna.setQaViews(rset.getInt("qa_views"));
				qna.setQaComCount(rset.getInt("qa_com_count"));
				qna.setQaAttDate(rset.getDate("qa_att_date"));
				qna.setQaPhotoNum(rset.getInt("qa_photo_num"));
				qna.setQaLev(rset.getInt("qa_lev"));
				
				list.add(qna);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int addReadCount(Connection conn, int qnaNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update qa "
				+ "set qa_views = qa_views + 1 "
				+ "where qa_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Qna> selectTop3(Connection conn) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from (select rownum rnum, qa_num, qa_title, qa_views "
				+ "     from (select * from qa "
				+ "order by qa_views desc)) "
				+ "where rnum <= 3";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQaNum(rset.getInt("qa_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaViews(rset.getInt("qa_views"));
				
				list.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int getSearchTitleCount(Connection conn, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) "
				+ "from qa "
				+ "where qa_title ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public int getSearchDateCount(Connection conn, Date begin, Date end) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) "
				+ "from qa "
				+ "where qa_enroll between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, begin);
			pstmt.setDate(2, end);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return listCount;
	}

	public ArrayList<Qna> selectSearchTitle(Connection conn, String keyword, Paging paging) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from (QA_NUM, ME_NUM, QA_TITLE, QA_PWD, QA_CON, QA_CATEGORY, QA_ENROLL, "
				+ "     QA_MOD_DATE, QA_DEL_DATE, QA_VIEWS, QA_COM_COUNT, "
				+ "     QA_ATT_DATE, QA_PHOTO_NUM "
				+ "     from (select * from qa)) "
				+ "where qa_title like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, paging.getStartRow());
			pstmt.setInt(3, paging.getEndRow());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQaNum(rset.getInt("qa_num"));
				qna.setMeNum(rset.getInt("me_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaPwd(rset.getString("qa_pwd"));
				qna.setQaCon(rset.getString("qa_con"));
				qna.setQaCategory(rset.getString("qa_category"));
				qna.setQaEnroll(rset.getDate("qa_enroll"));
				qna.setQaModDate(rset.getDate("qa_mod_date"));
				qna.setQaDelDate(rset.getDate("qa_del_date"));
				qna.setQaViews(rset.getInt("qa_views"));
				qna.setQaComCount(rset.getInt("qa_com_count"));
				qna.setQaAttDate(rset.getDate("qa_att_date"));
				qna.setQaPhotoNum(rset.getInt("qa_photo_num"));
				qna.setQaLev(rset.getInt("qa_lev"));
				
				list.add(qna);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Qna> selectSearchDate(Connection conn, Date begin, Date end, Paging paging) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from (select rownum rnum, QA_NUM, ME_NUM, QA_TITLE, QA_PWD, QA_CON, QA_CATEGORY "
				+ "     QA_ENROLL, QA_MOD_DATE, QA_DEL_DATE, QA_VIEWS, QA_COM_COUNT, "
				+ "     QA_ATT_DATE, QA_PHOTO_NUM "
				+ "     from (select * from qa\n"
				+ "where qa_enroll between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, begin);
			pstmt.setDate(2, end);
			pstmt.setInt(3, paging.getStartRow());
			pstmt.setInt(4, paging.getEndRow());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna();
				
				qna.setQaNum(rset.getInt("qa_num"));
				qna.setMeNum(rset.getInt("me_num"));
				qna.setQaTitle(rset.getString("qa_title"));
				qna.setQaPwd(rset.getString("qa_pwd"));
				qna.setQaCon(rset.getString("qa_con"));
				qna.setQaCategory(rset.getString("qa_category"));
				qna.setQaEnroll(rset.getDate("qa_enroll"));
				qna.setQaModDate(rset.getDate("qa_mod_date"));
				qna.setQaDelDate(rset.getDate("qa_del_date"));
				qna.setQaViews(rset.getInt("qa_views"));
				qna.setQaComCount(rset.getInt("qa_com_count"));
				qna.setQaAttDate(rset.getDate("qa_att_date"));
				qna.setQaPhotoNum(rset.getInt("qa_photo_num"));
				qna.setQaLev(rset.getInt("qa_lev"));
				
				list.add(qna);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
}
