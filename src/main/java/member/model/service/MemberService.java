package member.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import static common.JDBCTemplate.close;

public class MemberService {
	
	//DI(Dependency Injection : 의존성 주입)
	private MemberDao mdao = new MemberDao();
	
	public MemberService() {}

	//조회
	public Member selectMember(int meNum) {
		Connection conn = getConnection();
		Member member = mdao.selectMember(conn, meNum);
		close(conn);
		return member;
	}
	
	public Member selectMember(String meNum) {
		Connection conn = getConnection();
		Member member = mdao.selectMember(conn, meNum);
		close(conn);
		return member;
	}
	
	//전체 조회
	public ArrayList<Member> selectList() {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectList(conn);
		close(conn);
		return list;
	}
	
	//삽입
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = mdao.insertMember(conn, member);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//수정
	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = mdao.updateMember(conn, member);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//삭제
	public int deleteMember(int userid) {
		Connection conn = getConnection();
		int result = mdao.deleteMember(conn, userid);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//검색 아이디
	public ArrayList<Member> selectSearchUserid(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchUserid(conn, keyword);
		close(conn);
		return list;
	}
	
	//검색 성별
	public ArrayList<Member> selectSearchGender(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchGender(conn, keyword);
		close(conn);
		return list;
	}
	
	//검색 연령대
	public ArrayList<Member> selectSearchAge(int keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchAge(conn, keyword);
		close(conn);
		return list;
	}
	
	//검색 가입날짜
	public ArrayList<Member> selectSearchEnrollDate(Date begin, Date end) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchEnrollDate(conn, begin, end);
		close(conn);
		return list;
	}

	//인포
	public Member infoMember(String menum) {
		
		Connection conn = getConnection();
		Member member = mdao.infoMember(conn, menum);
		close(conn);
		return member;
	}
	
	//인포
		public Member infoMember(int menum) {
			
			Connection conn = getConnection();
			Member member = mdao.infoMember(conn, menum);
			close(conn);
			return member;
		}
	
	//로그인
	public Member selectLogin(String meId, String mePwd) {
		Connection conn = getConnection();
		Member loginMember = mdao.selectLogin(conn, meId, mePwd);
		close(conn);
		return loginMember;
	}

	public ArrayList<Member> selectMemberAll() {
		Connection conn = getConnection();
		ArrayList<Member> memberList = mdao.selectMemberAll(conn);
		close(conn);
		return memberList;
	}

    public int updateBan(int usernum, String ok) {
        Connection conn = getConnection();
		int result = mdao.updateBan(conn,usernum,ok);
		if(result > 0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;
    }

    public int selectCheckId(String userid) {
        Connection conn = getConnection();
		int result = mdao.selectCheckId(conn,userid);
		close(conn);
		return result;
    }

	public int selectCheckAka(String value) {
		Connection conn = getConnection();
		int result = mdao.selectCheckAka(conn,value);
		close(conn);
		return result;
	}

    public Member selectMemberAka(String recvAka) {
        Connection conn = getConnection();
		Member member = mdao.selectMemberAka(conn,recvAka);
		close(conn);
		return member;
    }

    public int likeCountUp(int likeMember) {
		Connection conn = getConnection();

        int result = mdao.likeCountUp(conn,likeMember);
		if(result >0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;
    }

    public int following(int followingMember, int followersMember) {
        Connection conn = getConnection();

        int result = mdao.following(conn,followingMember,followersMember);
		if(result >0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;
    }

	public int followersCount(int meNum) {
		Connection conn = getConnection();
		int result = mdao.followersCount(conn,meNum);
		close(conn);
		return result;

	}

    public int block(int block, int meNum) {
		Connection conn = getConnection();
        int result = mdao.block(conn,block,meNum);
		if(result > 0){
			commit(conn);
		}else{
			rollback(conn);
		}
		close(conn);
		return result;
    }

	public String bolckCheck() {
		// TODO Auto-generated method stub
		return null;
	}

    public int memberComDel(int userNum) {
        Connection conn = getConnection();
		int result = mdao.memberComDel(conn, userNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
    }
	
}
