package business.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import business.model.dao.BusinessDao;
import business.model.vo.Business;
import common.Paging;
import qna.model.vo.Qna;

public class BusinessService {
	private BusinessDao bdao = new BusinessDao();
	
	public BusinessService() {
		super();
	}

	public int getSearchTitleCount(String keyword) {
		Connection conn = getConnection();
		int listCount = bdao.getSearchTitleCount(conn, keyword);
		close(conn);
		return listCount;
	}

	public int getSearchDateCount(Date begin, Date end) {
		Connection conn = getConnection();
		int listCount = bdao.getSearchDateCount(conn, begin, end);
		close(conn);
		return listCount;
	}

	public ArrayList<Business> selectSearchTitle(String keyword, Paging paging) {
		Connection conn = getConnection();
		ArrayList<Business> list = bdao.selectSearchTitle(conn, keyword, paging);
		close(conn);
		return list;
	}

	public ArrayList<Business> selectSearchDate(Date begin, Date end, Paging paging) {
		Connection conn = getConnection();
		ArrayList<Business> list = bdao.selectSearchDate(conn, begin, end, paging);
		close(conn);
		return list;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = bdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Business> selectBuAll(int startPage, int endPage) {
		Connection conn = getConnection();
		ArrayList<Business> list = bdao.selectBuAll(conn, startPage, endPage);
		close(conn);
		return list;
	}

	public ArrayList<Business> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Business> list = bdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public int insertBusiness(Business business) {
		Connection conn = getConnection();
		int result = bdao.insertBusiness(conn, business);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}

	public int deleteBusiness(int buNum) {
		Connection conn = getConnection();
		int result = bdao.deleteBusiness(conn, buNum);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}

	public int updateBusiness(Business business) {
		Connection conn = getConnection();
		int result = bdao.updateBusiness(conn, business);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}

	public void addReadCount(int buNum) {
		Connection conn = getConnection();
		int result = bdao.addReadCount(conn, buNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public Business selectBusiness(int buNum) {
		Connection conn = getConnection();
		Business business = bdao.selectBusiness(conn, buNum);
		close(conn);
		return business;
	}

	public ArrayList<Business> searchBusiness(String keyword) {
		Connection conn = getConnection();
		ArrayList<Business> list = bdao.searchBusiness(conn, keyword);
		close(conn);
		return list;
	}

	

}
