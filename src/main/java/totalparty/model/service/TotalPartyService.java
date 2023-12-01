package totalparty.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import totalparty.model.dao.TotalPartyDao;
import totalparty.model.vo.TotalParty;

public class TotalPartyService {
	private TotalPartyDao dao = new TotalPartyDao();
	
    public TotalPartyService() {
		super();
	}
    
    public int createTotalPartyNum(int partyNum, int meNum) {
        Connection conn = getConnection();
		int result = dao.createTotalPartyNum(conn,partyNum,meNum);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
    }

    public TotalParty selcetTotalPart(int partyNum) {
        Connection conn = getConnection();
		TotalParty result = dao.selcetTotalPart(conn,partyNum);
		close(conn);
		return result;
    }

	public int getTotalCount(int partyNum) {
		int totalCount = 0;
		Connection conn = getConnection();
		totalCount = dao.getTotalCount(conn,partyNum);
		close(conn);
		return totalCount;
		
	}

	public int joinParty(int partyNum, int joinmember) {
		Connection conn = getConnection();
		int joinResult = dao.joinParty(conn,partyNum,joinmember);
		if(joinResult > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return joinResult;
	}

	public ArrayList<TotalParty> selectTotalPartyList(int panum) {
		Connection conn = getConnection();
		ArrayList<TotalParty> list = dao.selectTotalPartyList(conn, panum);
		close(conn);
		return list;
	}

	public int updateTotalParty(int panum, int menum) {
		Connection conn = getConnection();
		int result = dao.updateTotalParty(conn, panum, menum);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
