package report.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.jsp.views.common.location_jsp;

import report.model.vo.Report;
import static common.JDBCTemplate.*;
public class ReportDao {

	public Report selectReport(Connection conn, int reportid) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Report> selectReportAll(Connection conn) {
		ArrayList<Report> reportList = new ArrayList<Report>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from report";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()){
				Report report = new Report();
				report.setRpNum(rset.getInt("RP_NUM"));
				report.setRpType(rset.getString("RP_TYPE"));
				report.setRpDate(rset.getDate("RP_DATE"));
				report.setRpCon(rset.getString("RP_CON"));
				report.setRpProc(rset.getString("RP_PROC"));
				report.setRpProcDate(rset.getDate("RP_PROC_DATE"));
				report.setMeNum(rset.getInt("ME_NUM"));
				report.setPaNum(rset.getInt("PA_NUM"));
				report.setComNum(rset.getInt("COM_NUM"));
				report.setAdNum(rset.getInt("AD_NUM"));	
				reportList.add(report);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(stmt);
		}
		return reportList;
	}

	public int insertReport(Connection conn, Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateReport(Connection conn, Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteReport(Connection conn, Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

    public int reportInsert(Connection conn, int reportNum, String reportType, String reportcon) {
        int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into report values ((select count(*)+1 from report),?,sysdate,?,'N', null, ";
		if(reportType.equals("member")){
			query += "?, null,null,null)";
		}else if(reportType.equals("party")){
			query += "null, ?,null,null)";
		}else if(reportType.equals("com")){
			query += "null, null,?,null)";
		}else if(reportType.equals("adver")){
			query += "null, null,null,?)";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reportType);
			pstmt.setString(2,reportcon);
			pstmt.setInt(3, reportNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
    }

}
