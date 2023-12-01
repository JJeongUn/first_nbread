package business.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import business.model.vo.Business;
import common.Paging;
import business.model.vo.Business;

public class BusinessDao {
	public BusinessDao() {}
	
	public int getSearchTitleCount(Connection conn, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) "
				+ "from business "
				+ "where bu_title ?";
		
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
				+ "from business "
				+ "where bu_enroll between ? and ?";
		
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

	public ArrayList<Business> selectSearchTitle(Connection conn, String keyword, Paging paging) {
		ArrayList<Business> list = new ArrayList<Business>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "form (BU_NUM, ME_NUM, BU_TITLE, BU_PWD, BU_CON, BU_ENROLL, "
				+ "     BU_MOD_DATE, BU_DEL_DATE, BU_VIEWS, BU_LEV, BU_PH_NUM "
				+ "     from (select * from business)) "
				+ "where bu_title like ?  ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, paging.getStartRow());
			pstmt.setInt(3, paging.getEndRow());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Business business = new Business();
				
				business.setBuNum(rset.getInt("bu_num"));
				business.setMeNum(rset.getInt("me_num"));
				business.setBuTitle(rset.getString("bu_title"));
				business.setBuPwd(rset.getString("bu_pwd"));
				business.setBuCon(rset.getString("bu_con"));
				business.setBuEnroll(rset.getDate("bu_enroll"));
				business.setBuModDate(rset.getDate("bu_mod_date"));
				business.setBuDelDate(rset.getDate("bu_del_date"));
				business.setBuViews(rset.getInt("bu_views"));
				business.setBuLev(rset.getInt("bu_lev"));
				business.setBuPhNum(rset.getInt("bu_ph_num"));
				
				list.add(business);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Business> selectSearchDate(Connection conn, Date begin, Date end, Paging paging) {
		ArrayList<Business> list = new ArrayList<Business>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "form (BU_NUM, ME_NUM, BU_TITLE, BU_PWD, BU_CON, BU_ENROLL, "
				+ "     BU_MOD_DATE, BU_DEL_DATE, BU_VIEWS, BU_LEV, BU_PH_NUM "
				+ "     from (select * from business"
				+ "where ba_enroll between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, begin);
			pstmt.setDate(2, end);
			pstmt.setInt(3, paging.getStartRow());
			pstmt.setInt(4, paging.getEndRow());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Business business = new Business();
				
				business.setBuNum(rset.getInt("bu_num"));
				business.setMeNum(rset.getInt("me_num"));
				business.setBuTitle(rset.getString("bu_title"));
				business.setBuPwd(rset.getString("bu_pwd"));
				business.setBuCon(rset.getString("bu_con"));
				business.setBuEnroll(rset.getDate("bu_enroll"));
				business.setBuModDate(rset.getDate("bu_mod_date"));
				business.setBuDelDate(rset.getDate("bu_del_date"));
				business.setBuViews(rset.getInt("bu_views"));
				business.setBuLev(rset.getInt("bu_lev"));
				business.setBuPhNum(rset.getInt("bu_ph_num"));
				
				list.add(business);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from business";
		
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

	public ArrayList<Business> selectBuAll(Connection conn, int startPage, int endPage) {
		ArrayList<Business> list = new ArrayList<Business>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * "
				+ "form ((select rownum, BU_NUM, ME_NUM, BU_TITLE, BU_PWD, BU_CON, BU_ENROLL, "
				+ "     BU_MOD_DATE, BU_DEL_DATE, BU_VIEWS, BU_LEV, BU_PH_NUM "
				+ "     from (select * from  business "
				+ "     order by bu_enroll desc)) "
				+ "where rownum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, endPage);
			rset = pstmt.executeQuery();
					
			while(rset.next()) {
				Business business = new Business();
				
				business.setBuNum(rset.getInt("bu_num"));
				business.setMeNum(rset.getInt("me_num"));
				business.setBuTitle(rset.getString("bu_title"));
				business.setBuPwd(rset.getString("bu_pwd"));
				business.setBuCon(rset.getString("bu_con"));
				business.setBuEnroll(rset.getDate("bu_enroll"));
				business.setBuModDate(rset.getDate("bu_mod_date"));
				business.setBuDelDate(rset.getDate("bu_del_date"));
				business.setBuViews(rset.getInt("bu_views"));
				business.setBuLev(rset.getInt("bu_lev"));
				business.setBuPhNum(rset.getInt("bu_ph_num"));
				
				list.add(business);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Business> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Business> list = new ArrayList<Business>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
				+ "from ((select rownum rnum, BU_NUM, ME_NUM, BU_TITLE, BU_PWD, BU_CON, BU_ENROLL, "
				+ "     BU_MOD_DATE, BU_DEL_DATE, BU_VIEWS, BU_LEV, BU_PH_NUM "
				+ "     from (select * from  business order by bu_enroll desc))) "
				+ "where rnum >= ? and rnum <= ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Business business = new Business();
				
				business.setBuNum(rset.getInt("bu_num"));
				business.setMeNum(rset.getInt("me_num"));
				business.setBuTitle(rset.getString("bu_title"));
				business.setBuPwd(rset.getString("bu_pwd"));
				business.setBuCon(rset.getString("bu_con"));
				business.setBuEnroll(rset.getDate("bu_enroll"));
				business.setBuModDate(rset.getDate("bu_mod_date"));
				business.setBuDelDate(rset.getDate("bu_del_date"));
				business.setBuViews(rset.getInt("bu_views"));
				business.setBuLev(rset.getInt("bu_lev"));
				business.setBuPhNum(rset.getInt("bu_ph_num"));
				
				list.add(business);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBusiness(Connection conn, Business business) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into business values((select max(bu_num)+1 from business), ?, ?, ?, ?, sysdate, default, default, default, default, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, business.getMeNum());
			pstmt.setString(2, business.getBuTitle());
			pstmt.setString(3, business.getBuPwd());
			pstmt.setString(4, business.getBuCon());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBusiness(Connection conn, int buNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from business where bu_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, buNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBusiness(Connection conn, Business business) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update business "
				+ "set bu_title = ?, "
				+ "	   bu_con = ? "
				+ "where bu_num = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, business.getBuTitle());
			pstmt.setString(2, business.getBuCon());
			pstmt.setInt(3, business.getBuNum());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int addReadCount(Connection conn, int buNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update business "
				+ "set bu_views = bu_views + 1 "
				+ "where bu_num = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, buNum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Business selectBusiness(Connection conn, int buNum) {
		Business business = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from business "
				+ "where bu_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, buNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				business = new Business();
				
				business.setBuNum(rset.getInt("bu_num"));
				business.setMeNum(rset.getInt("me_num"));
				business.setBuTitle(rset.getString("bu_title"));
				business.setBuPwd(rset.getString("bu_pwd"));
				business.setBuCon(rset.getString("bu_con"));
				business.setBuEnroll(rset.getDate("bu_enroll"));
				business.setBuModDate(rset.getDate("bu_mod_date"));
				business.setBuDelDate(rset.getDate("bu_del_date"));
				business.setBuViews(rset.getInt("bu_views"));
				business.setBuLev(rset.getInt("bu_lev"));
				business.setBuPhNum(rset.getInt("bu_ph_num"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return business;
	}

	public ArrayList<Business> searchBusiness(Connection conn, String keyword) {
		ArrayList<Business> list = new ArrayList<Business>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from business where bu_title like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Business business = new Business();
				
				business.setBuNum(rset.getInt("bu_num"));
				business.setMeNum(rset.getInt("me_num"));
				business.setBuTitle(rset.getString("bu_title"));
				business.setBuPwd(rset.getString("bu_pwd"));
				business.setBuCon(rset.getString("bu_con"));
				business.setBuEnroll(rset.getDate("bu_enroll"));
				business.setBuModDate(rset.getDate("bu_mod_date"));
				business.setBuDelDate(rset.getDate("bu_del_date"));
				business.setBuViews(rset.getInt("bu_views"));
				business.setBuLev(rset.getInt("bu_lev"));
				business.setBuPhNum(rset.getInt("bu_ph_num"));
				
				list.add(business);
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
