package partyCo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import static common.JDBCTemplate.close;
import partyCo.model.vo.PartyCo;

public class PartyCoDao {

	public PartyCo selectPartyCo(Connection conn, int panum) {
		PartyCo partyCo = new PartyCo();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from comments where com_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			rset = pstmt.executeQuery();

			if(rset.next()){
				partyCo.setComNum(rset.getInt("COM_NUM"));
				partyCo.setPaNum(rset.getInt("PA_NUM"));
				partyCo.setComParent(rset.getInt("COM_PARENT"));
				partyCo.setComDepth(rset.getInt("COM_DEPTH"));
				partyCo.setComCon(rset.getString("COM_CON"));
				partyCo.setComViews(rset.getInt("COM_VIEWS"));
				partyCo.setComCount(rset.getInt("COM_COUNT"));
				partyCo.setComEnroll(rset.getDate("COM_ENROLL"));
				partyCo.setComModDate(rset.getDate("COM_MOD_DATE"));
				partyCo.setComDelDate(rset.getDate("COM_DEL_DATE"));
				partyCo.setComPhotoNum(rset.getInt("COM_PHOTO_NUM"));
				partyCo.setMeNum(rset.getInt("ME_NUM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return partyCo;
		
	}
	
	public ArrayList<PartyCo> selectPartyCoAll(Connection conn, int panum) {
		ArrayList<PartyCo> list = new ArrayList<PartyCo>();
		
		return list;
	}
	
	public int getListCount(Connection conn, int panum) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from comments where pa_num = ? and COM_DEPTH = 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) listCount = rset.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<PartyCo> selectPartyCoList(Connection conn, int panum, int startRow, int endRow) {
		ArrayList<PartyCo> list = new ArrayList<PartyCo>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "select * "
					+ "from comments "
					+ "left join member using(me_num) "
					+ "where COM_PARENT in "
					+ "(select COM_NUM "
					+ "from (select rownum rnum, COM_NUM, PA_NUM, COM_PARENT, COM_DEPTH, "
					+ "           COM_CON, COM_VIEWS, COM_COUNT, COM_ENROLL, COM_MOD_DATE, "
					+ "           COM_DEL_DATE, COM_PHOTO_NUM "
					+ "     from (select * from comments where PA_NUM = ? and COM_DEPTH = 1 "
					+ "           order by COM_PARENT desc, COM_DEPTH asc, COM_NUM desc) "
					+ "         "
					+ "    ) "
					+ "where rnum >= ? and rnum <= ?) "
					+ "order by COM_PARENT desc, COM_DEPTH asc, COM_NUM desc";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PartyCo partyCo = new PartyCo();
				
				partyCo.setComNum(rset.getInt("COM_NUM"));
				partyCo.setPaNum(rset.getInt("PA_NUM"));
				partyCo.setComParent(rset.getInt("COM_PARENT"));
				partyCo.setComDepth(rset.getInt("COM_DEPTH"));
				partyCo.setComCon(rset.getString("COM_CON"));
				partyCo.setComViews(rset.getInt("COM_VIEWS"));
				partyCo.setComCount(rset.getInt("COM_COUNT"));
				partyCo.setComEnroll(rset.getDate("COM_ENROLL"));
				partyCo.setComModDate(rset.getDate("COM_MOD_DATE"));
				partyCo.setComDelDate(rset.getDate("COM_DEL_DATE"));
				partyCo.setComPhotoNum(rset.getInt("COM_PHOTO_NUM"));
				partyCo.setMeNum(rset.getInt("ME_NUM"));
				partyCo.setMeAka(rset.getString("ME_AKA"));
				partyCo.setMePhotoAdd(rset.getString("ME_PHOTO_ADD"));
				partyCo.setMeId(rset.getString("ME_ID"));
				//System.out.println("board : " + board);
				list.add(partyCo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertPartyCo(Connection conn, PartyCo partyCo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = null;
			if(partyCo.getComParent() == -1) {
				query = "insert into comments values "
					+ "((select max(com_num) from comments) + 1, ?, "
					+ "(select max(com_num) from comments) + 1, "
					+ "?, ?, ?, ?, sysdate, null, null, ?, null)";
				//System.out.println("partyCo.getPaNum() : " + partyCo.getPaNum());
				//System.out.println("partyCo.getComDepth() : " + partyCo.getComDepth());
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, partyCo.getPaNum());
				pstmt.setInt(2, partyCo.getComDepth());
				pstmt.setString(3, partyCo.getComCon());
				pstmt.setInt(4, partyCo.getComViews());
				pstmt.setInt(5, partyCo.getComCount());
				pstmt.setInt(6, partyCo.getMeNum());
			}else {
				query = "insert into comments values " 
						+ "((select max(com_num) from comments) + 1, "
						+ "?, ?, ?, ?, ?, ?, sysdate, null, null, ?, null)";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, partyCo.getPaNum());
				pstmt.setInt(2, partyCo.getComParent());
				pstmt.setInt(3, partyCo.getComDepth());
				pstmt.setString(4, partyCo.getComCon());
				pstmt.setInt(5, partyCo.getComViews());
				pstmt.setInt(6, partyCo.getComCount());
				pstmt.setInt(7, partyCo.getMeNum());
			}
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<PartyCo> sortPartyCo(Connection conn, int panum, int startRow, int endRow, String type) {
		
		ArrayList<PartyCo> list = new ArrayList<PartyCo>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = null;
			//System.out.println("???type : " + type);
			if(type == "count") {
				//댓글순
				query = "select * "
						+ "from comments "
						+ "left join member using(me_num) "
						+ "where COM_NUM in ( "
						+ "    select COM_NUM "
						+ "	   from(select COM_NUM "
						+ "        from comments "
						+ "        where COM_PARENT in "
						+ "        (select COM_NUM "
						+ "        from (select rownum rnum, COM_NUM, PA_NUM, COM_PARENT, COM_DEPTH, "
						+ "                   COM_CON, COM_VIEWS, COM_COUNT, COM_ENROLL, COM_MOD_DATE, "
						+ "                   COM_DEL_DATE, COM_PHOTO_NUM "
						+ "             from (select * from comments where PA_NUM = ? and COM_DEPTH = 1 "
						+ "                   order by COM_COUNT desc, COM_PARENT desc, COM_NUM desc) "
						+ "              ) "
						+ "        where rnum >= ? and rnum <= ? "
						+ "        ) "
						+ "    ) "
						+ ")"
						+ "order by COM_PARENT desc, COM_DEPTH asc, COM_NUM desc";
				
			}
			else {
				//공감순
				query = "select * "
						+ "from comments "
						+ "left join member using(me_num) "
						+ "where COM_NUM in ( "
						+ "    select COM_NUM "
						+ "    from(select COM_NUM "
						+ "        from comments "
						+ "        where COM_PARENT in "
						+ "        (select COM_NUM "
						+ "        from (select rownum rnum, COM_NUM, PA_NUM, COM_PARENT, COM_DEPTH, "
						+ "                   COM_CON, COM_VIEWS, COM_COUNT, COM_ENROLL, COM_MOD_DATE, "
						+ "                   COM_DEL_DATE, COM_PHOTO_NUM "
						+ "             from (select * from comments where PA_NUM = ? and COM_DEPTH = 1 "
						+ "                   order by COM_VIEWS desc, COM_PARENT desc, COM_NUM desc) "
						+ "              ) "
						+ "        where rnum >= ? and rnum <= ? "
						+ "        ) "
						+ "    ) "
						+ ")"
						+ "order by COM_PARENT desc, COM_DEPTH asc, COM_NUM desc";
				
			}
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PartyCo partyCo = new PartyCo();
				
				partyCo.setComNum(rset.getInt("COM_NUM"));
				partyCo.setPaNum(rset.getInt("PA_NUM"));
				partyCo.setComParent(rset.getInt("COM_PARENT"));
				partyCo.setComDepth(rset.getInt("COM_DEPTH"));
				partyCo.setComCon(rset.getString("COM_CON"));
				partyCo.setComViews(rset.getInt("COM_VIEWS"));
				partyCo.setComCount(rset.getInt("COM_COUNT"));
				partyCo.setComEnroll(rset.getDate("COM_ENROLL"));
				partyCo.setComModDate(rset.getDate("COM_MOD_DATE"));
				partyCo.setComDelDate(rset.getDate("COM_DEL_DATE"));
				partyCo.setComPhotoNum(rset.getInt("COM_PHOTO_NUM"));
				partyCo.setMeNum(rset.getInt("ME_NUM"));
				partyCo.setMeAka(rset.getString("ME_AKA"));
				partyCo.setMePhotoAdd(rset.getString("ME_PHOTO_ADD"));
				partyCo.setMeId(rset.getString("ME_ID"));
				//System.out.println("board : " + board);
				list.add(partyCo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<PartyCo> sortPartyCoCount(Connection conn, int panum, int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PartyCo> sortPartyCoCurrent(Connection conn, int panum, int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updatePartyCo(Connection conn, int comnum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update comments set com_con = '관리자에 의해 삭제된 댓글입니다.' where com_num = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comnum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int deletePartyCo(Connection conn, int comnum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = "delete from comments "
					+ "where com_num = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comnum);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int loadPartyCo(Connection conn, PartyCo partyCo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ArrayList<PartyCo> searchPartyCo(Connection conn, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int updatePartyCoCount(Connection conn, int comParent, int comCount) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = "update comments "
					+ "set com_count = com_count + ? "
					+ "where COM_NUM = ? "
					+ "and com_depth = 1";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comCount);
			pstmt.setInt(2, comParent);
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updatePartyCoView(Connection conn, int comnum, int plusnum) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("comnum : " + comnum + " plusnum : " + plusnum);
		try {
			String plus = (plusnum == 1) ? "+" : "-";
			String query = "update comments set com_views = com_views " +plus+ " ? where COM_NUM = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Math.abs(plusnum));
			pstmt.setInt(2, comnum);
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}
