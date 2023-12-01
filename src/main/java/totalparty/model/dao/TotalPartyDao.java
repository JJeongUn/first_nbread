package totalparty.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import totalparty.model.vo.TotalParty;

import static common.JDBCTemplate.*;
public class TotalPartyDao {

	public TotalPartyDao() {
		super();
	}
    public int createTotalPartyNum(Connection conn, int partyNum, int meNum) {
        int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into totalparty values (?,?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,partyNum);
			pstmt.setInt(2,meNum);
			pstmt.setString(3,"N");
		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error : createTotalPartyNum");
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
    }
    public TotalParty selcetTotalPart(Connection conn, int partyNum) {
		TotalParty result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from totalparty where pa_num = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, partyNum);
			rset = pstmt.executeQuery();
			if(rset.next()){
				result = new TotalParty();
				result.setPaNum(partyNum);
			}
		} catch (Exception e) {
			System.out.println("error : selcetTotalPart");
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
    }
	public int getTotalCount(Connection conn, int partyNum) {
		int totalCount = 0;
		PreparedStatement pstmt = null;

		String query = "select count(*) from totalparty where pa_num = ?" ;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, partyNum);
			totalCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error : getTotalCount");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return totalCount;
	}
    public int joinParty(Connection conn, int partyNum, int joinmember) {
        int joinResult = 0;
		PreparedStatement pstmt = null;
		String query = "insert into totalparty values (?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, partyNum);
			pstmt.setInt(2, joinmember);
			pstmt.setString(3, "N");
			joinResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("joinParty");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return joinResult;
    }
	public ArrayList<TotalParty> selectTotalPartyList(Connection conn, int panum) {
		ArrayList<TotalParty> list = new ArrayList<TotalParty>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from totalparty where pa_num = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			rset = pstmt.executeQuery();

			while(rset.next()){
				TotalParty tp = new TotalParty();
				tp.setPaNum(rset.getInt(1));
				tp.setMeNum(rset.getInt(2));
				tp.setPayOk(rset.getString(3));
				list.add(tp);
			}
		} catch (Exception e) {
			System.out.println("error : selectTotalPartyList");
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int updateTotalParty(Connection conn, int panum, int menum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update totalparty "
				+ "set pay_ok = 'Y' "
				+ "where pa_num = ? and me_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);
			pstmt.setInt(2, menum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("joinParty");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
