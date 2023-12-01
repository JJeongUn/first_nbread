package party.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import party.model.dao.PartyDao;
import party.model.vo.Party;

import static common.JDBCTemplate.*;

public class PartyService {
	
	private PartyDao dao = new PartyDao();
	
	public PartyService() {}
	
	//조회 
	public Party selectParty(int partyid) {
		Connection conn = getConnection();
		Party party = dao.selectParty(conn, partyid);
		close(conn);
		return party;
	}
	
	//전체 조회
	public ArrayList<Party> selectPartyAll(String type, int start, int end) {
		Connection conn = getConnection();
		ArrayList<Party> list = dao.selectPartyAll(conn, type, start, end);
		close(conn);
		return list;
	}
	public ArrayList<Party> selectPartyAll() {
		Connection conn = getConnection();
		ArrayList<Party> list = dao.selectPartyAll(conn);
		close(conn);
		return list;
	}

	//몇개만 조회
	public ArrayList<Party> selectPartyList(String type, int start, int end, String panum) {
		Connection conn = getConnection();
		ArrayList<Party> list = dao.selectPartyList(conn, type, start, end, panum);
		close(conn);
		return list;
	}
	
	//삽입
	public int insertParty(Party party) {
		Connection conn = getConnection();
		int result = dao.insertParty(conn, party);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	//수정
	public int updateParty(Party party) {
		Connection conn = getConnection();
		int result = dao.updateParty(conn, party);
		
		if(result > 0) commit(conn);
		else rollback(conn);
			
		close(conn);
		return result;
	}
	
	//삭제
	public int deleteParty(Party party) {
		Connection conn = getConnection();
		int result = dao.deleteParty(conn, party);
		
		if(result > 0) commit(conn);
		else rollback(conn);
			
		close(conn);
		return result;
	}
	
	//첨부파일 업로드
	public int loadParty(Party party) {
		Connection conn = getConnection();
		int result = dao.loadParty(conn, party);
		if(result > 0) commit(conn);
		else rollback(conn);
			
		close(conn);
		return result;
	}
	
	//검색
	public ArrayList<Party> searchParty(HashMap<String, String> map){
		Connection conn = getConnection();
		ArrayList<Party> list = dao.searchParty(conn, map);
		close(conn);
		return list;
	}
	
	//정렬 - 관심사
	public ArrayList<Party> sortPartyInterest(String keyword){
		Connection conn = getConnection();
		ArrayList<Party> list = dao.searchPartyInterest(conn, keyword);
		close(conn);
		return list;
	}
	
	//정렬 - 카테고리
	public ArrayList<Party> sortPartyCategory(String keyword){
		Connection conn = getConnection();
		ArrayList<Party> list = dao.sortPartyCategory(conn, keyword);
		close(conn);
		return list;
	}
	
	//정렬 - 날짜
	public ArrayList<Party> sortPartyCurrent(String keyword){
		Connection conn = getConnection();
		ArrayList<Party> list = dao.sortPartyCurrent(conn, keyword);
		close(conn);
		return list;
	}

	public String getNowPartyNum() {
		Connection conn = getConnection();
		String result = dao.getNowPartyNum(conn);
		close(conn);
		return result;
	}

	public int setCategory(String categoryCheck) {
		int category = -1;
		if(categoryCheck.equals("모임")) {
			category = 1;
		}else if(categoryCheck.equals("공구")) {
			category = 2;
		}else if(categoryCheck.equals("렌탈")) {
			category = 3;
		}else if(categoryCheck.equals("게임")) {
			category = 4;
		}else if(categoryCheck.equals("OTT")) {
			category = 5;
		}else if(categoryCheck.equals("기타")) {
			category = 6;
		}else {
			category = 6;
		}
		return category;
	}
	
	public String getCategory(int num) {
		String category = null;
		if(num == 1) {
			category = "모임";
		}else if(num == 2) {
			category = "공구";
		}else if(num == 3) {
			category = "렌탈";
		}else if(num == 4) {
			category = "게임";
		}else if(num == 5) {
			category = "OTT";
		}else if(num == 6) {
			category = "기타";
		}else {
			category = "기타";
		}
		return category;
	}

	public int getListCount(String act) {
		Connection conn = getConnection();
		int listCount = dao.getListCount(conn, act);
		close(conn);
		return listCount;
	}

	public int completeParty(int panum) {
		Connection conn = getConnection();
		int result = dao.completeParty(conn, panum);
		
		if(result > 0) commit(conn);
		else rollback(conn);
			
		close(conn);
		return result;
	}

    public int updateBanParty(int panum, String partyban) {
        Connection conn = getConnection();
		int result = dao.updateBanParty(conn,panum,partyban);
		if(result > 0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;
    }

    public ArrayList<Party> myReviewParty(int meNum) {
		Connection conn = getConnection();
        ArrayList<Party> party = dao.myReviewParty(conn,meNum);
		close(conn);
		return party;
    }

	public int updatePayCk(int panum, int price) {
		Connection conn = getConnection();
		int result = dao.updatePayCk(conn, panum, price);
		if(result > 0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
}
