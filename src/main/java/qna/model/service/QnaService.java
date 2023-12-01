package qna.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import common.Paging;
import qna.model.dao.QnaDao;
import qna.model.vo.Qna;

public class QnaService {
	private QnaDao qdao = new QnaDao();

	public QnaService() {
	}

	// 조회
	public Qna selectQna(int qanum) {
		Connection conn = getConnection();
		Qna qna = qdao.selectQna(conn, qanum);
		close(conn);
		return qna;
	}
	
	//전체 조회
	public ArrayList<Qna> selectQnaAll(int startPage, int endPage) {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectQnaAll(conn, startPage, endPage);
		close(conn);
		return list;
	}
	
	//삽입
	public int insertQna(Qna qna) {
		Connection conn = getConnection();
		int result = qdao.insertQna(conn, qna);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}
	
	//수정
	public int updateQna(Qna qna) {
		Connection conn = getConnection();
		int result = qdao.updateQna(conn, qna);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}
	
	//삭제
	public int deleteQna(int qnaNum) {
		Connection conn = getConnection();
		int result = qdao.deleteQna(conn, qnaNum);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}
	
	//첨부파일 업로드
	public int loadQna(Qna qna) {
		Connection conn = getConnection();
		int result = qdao.loadQna(conn, qna);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return result;
	}
	
	//검색
	public ArrayList<Qna> searchQna(String keyword){
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.searchQna(conn, keyword);
		close(conn);
		return list;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = qdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Qna> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public void addReadCount(int qnaNum) {
		Connection conn = getConnection();
		int result = qdao.addReadCount(conn, qnaNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
	}

	public ArrayList<Qna> selectTop3() {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectTop3(conn);
		close(conn);
		return list;
	}

	public int getSearchTitleCount(String keyword) {
		Connection conn = getConnection();
		int listCount = qdao.getSearchTitleCount(conn, keyword);
		close(conn);
		return listCount;
	}

	public int getSearchDateCount(Date begin, Date end) {
		Connection conn = getConnection();
		int listCount = qdao.getSearchDateCount(conn, begin, end);
		close(conn);
		return listCount;
	}

	public ArrayList<Qna> selectSearchTitle(String keyword, Paging paging) {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectSearchTitle(conn, keyword, paging);
		close(conn);
		return list;
	}

	public ArrayList<Qna> selectSearchDate(Date begin, Date end, Paging paging) {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectSearchDate(conn, begin, end, paging);
		close(conn);
		return list;
	}
	
}
