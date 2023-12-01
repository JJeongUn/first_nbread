package party.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import party.model.vo.Party;

public class PartyDao {

	public PartyDao() {
	}

	// 조회
	public Party selectParty(Connection conn, int partyid) {

		Party party = null;
		PreparedStatement ptst = null;
		ResultSet rset = null;

		try {

			String query = "select * from party where pa_num=?";
			ptst = conn.prepareStatement(query);
			ptst.setInt(1, partyid);

			rset = ptst.executeQuery();

			if (rset.next()) {
				party = makeParty(rset);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ptst);
			close(rset);
		}

		return party;
	}

	// 전체 조회
	public ArrayList<Party> selectPartyAll(Connection conn, String type, int start, int end) {
		ArrayList<Party> partyList = new ArrayList<Party>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select * "
					+ "from (select rownum rnum, PA_NUM, ME_NUM, PA_TIME, PA_TOTAL_AMOUNT, "
					+ "           PA_DEPOSIT, PA_PER_AMOUNT, PA_TITLE, PA_CON, PA_ENROLL, "
					+ "           PA_MOD_DATE, PA_DEL_DATE, PA_ACT, PA_VIEWS, PA_LIKE, "
					+ "           PA_COM_COUNT, PA_GENDER_SET, PA_LOCATION, PA_TOTAL_NUM, "
					+ "           PA_GENDER_LIMIT, PA_PAY_CK, PH_NUM, CAT_NUM "
					+ "     from (select * from party where PA_ACT = ? "
					+ "           order by PA_ENROLL desc)) "
					+ "where rnum >= ? and rnum <= ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Party party = makeParty(rset);
				// System.out.println("party : " + party);
				partyList.add(party);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return partyList;
	}

	public ArrayList<Party> selectPartyAll(Connection conn) {
		ArrayList<Party> partyList = new ArrayList<Party>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select * from party";

			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Party party = makeParty(rset);
				// System.out.println("party : " + party);
				partyList.add(party);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return partyList;
	}

	// 리스트 조회(나만 빼고)
	public ArrayList<Party> selectPartyList(Connection conn, String type, int start, int end, String panum) {
		ArrayList<Party> partyList = new ArrayList<Party>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "select * "
					+ "from (select rownum rnum, PA_NUM, ME_NUM, PA_TIME, PA_TOTAL_AMOUNT, "
					+ "           PA_DEPOSIT, PA_PER_AMOUNT, PA_TITLE, PA_CON, PA_ENROLL, "
					+ "           PA_MOD_DATE, PA_DEL_DATE, PA_ACT, PA_VIEWS, PA_LIKE, "
					+ "           PA_COM_COUNT, PA_GENDER_SET, PA_LOCATION, PA_TOTAL_NUM, "
					+ "           PA_GENDER_LIMIT, PA_PAY_CK, PH_NUM, CAT_NUM "
					+ "     from (select * from party where PA_ACT = ? and PA_NUM != ? "
					+ "           order by PA_ENROLL desc)) "
					+ "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "Y");
			pstmt.setString(2, panum);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Party party = makeParty(rset);
				// System.out.println("party : " + party);
				partyList.add(party);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return partyList;
	}

	// 삽입
	public int insertParty(Connection conn, Party party) {

		PreparedStatement pstmt = null;
		int result = 0;

		try {

			String query = "insert into party " +
					"values ((select count(*) from party)+1,?,sysdate,?,?,?,?,?,default,NULL,NULL,default,default, " +
					"        default,default,default,?,?,default,?,?,?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, party.getMeNum());
			pstmt.setInt(2, party.getPaTotalAmount());
			pstmt.setInt(3, party.getPaDeposit());
			pstmt.setInt(4, party.getPaPerAmount());
			pstmt.setString(5, party.getPaTitle());
			pstmt.setString(6, party.getPaCon());
			pstmt.setString(7, party.getPaLocation());
			pstmt.setInt(8, party.getPaTotalNum());
			pstmt.setInt(9, party.getPaPayCk());
			pstmt.setInt(10, party.getPhNum());
			if (party.getCatNum() > 0) {
				pstmt.setInt(11, party.getCatNum());
			} else {
				pstmt.setInt(11, 0);
			}
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 수정
	public int updateParty(Connection conn, Party party) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update party "
				+ "set PA_TIME = ?, PA_TOTAL_AMOUNT = ?, PA_DEPOSIT = ?, PA_TITLE = ?, "
				+ "PA_CON = ?, PA_MOD_DATE = sysdate, PA_GENDER_SET = ?, "
				+ "PA_GENDER_LIMIT = ?, PA_LOCATION = ?, PA_TOTAL_NUM = ?, CAT_NUM = ? "
				+ "where pa_num = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, party.getPaTime());// 시간
			pstmt.setInt(2, party.getPaTotalAmount());// 총금액
			pstmt.setInt(3, party.getPaDeposit());// 예치금

			pstmt.setString(4, party.getPaTitle());// 제목
			pstmt.setString(5, party.getPaCon());// 내용

			pstmt.setString(6, party.getPaGenderSet());// 성별 세팅
			pstmt.setString(7, party.getPaGenderLimit());// all/m/f
			pstmt.setString(8, party.getPaLocation());// all/m/f
			pstmt.setInt(9, party.getPaTotalNum());// 전체 인원
			pstmt.setInt(10, party.getCatNum());// 카테고리

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 삭제
	public int deleteParty(Connection conn, Party party) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "delete from party "
				+ "set PA_TIME = ?, PA_TOTAL_AMOUNT = ?, PA_DEPOSIT = ?, PA_TITLE = ?, "
				+ "PA_CON = ?, PA_MOD_DATE = sysdate, PA_GENDER_SET = ?, "
				+ "PA_GENDER_LIMIT = ?, PA_LOCATION = ?, PA_TOTAL_NUM = ?, CAT_NUM = ? "
				+ "where pa_num = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, party.getPaTime());// 시간
			pstmt.setInt(2, party.getPaTotalAmount());// 총금액
			pstmt.setInt(3, party.getPaDeposit());// 예치금

			pstmt.setString(4, party.getPaTitle());// 제목
			pstmt.setString(5, party.getPaCon());// 내용

			pstmt.setString(6, party.getPaGenderSet());// 성별 세팅
			pstmt.setString(7, party.getPaGenderLimit());// all/m/f
			pstmt.setString(8, party.getPaLocation());// all/m/f
			pstmt.setInt(9, party.getPaTotalNum());// 전체 인원
			pstmt.setInt(10, party.getCatNum());// 카테고리

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 첨부파일 업로드
	public int loadParty(Connection conn, Party party) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 검색
	public ArrayList<Party> searchParty(Connection conn, HashMap<String, String> map) {

		ArrayList<Party> partyList = new ArrayList<Party>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String type = map.get("selType");
		String sort = map.get("sort");
		int classfy = Integer.parseInt(map.get("classfy"));
		String keyword = map.get("keyword");

		int start = Integer.parseInt(map.get("start"));
		int end = Integer.parseInt(map.get("end"));
		System.out.println("========type : " + type + " sort : " + sort
				+ " classfy : " + classfy + " keyword : " + keyword
				+ " start : " + start + " end : " + end);

		try {

			String query = null;

			if (classfy == -1) {
				if (sort.equals("current")) {
					System.out.println("============sort : current classfy : -1==================");

					query = "select * "
							+ "from (select rownum rnum, PA_NUM, ME_NUM, PA_TIME, PA_TOTAL_AMOUNT, "
							+ "           PA_DEPOSIT, PA_PER_AMOUNT, PA_TITLE, PA_CON, PA_ENROLL, "
							+ "           PA_MOD_DATE, PA_DEL_DATE, PA_ACT, PA_VIEWS, PA_LIKE, "
							+ "           PA_COM_COUNT, PA_GENDER_SET, PA_LOCATION, PA_TOTAL_NUM, "
							+ "           PA_GENDER_LIMIT, PA_PAY_CK, PH_NUM, CAT_NUM "
							+ "     from (select * from party "
							+ "           where PA_ACT = ? and ("
							+ "                 REPLACE(PA_TITLE,' ','') like ? "
							+ "            or   REPLACE(PA_CON,' ','') like ? "
							+ "            or   REPLACE(PA_LOCATION,' ','') like ?) "
							+ "           order by PA_ENROLL desc, PA_NUM desc)) "
							+ "where rnum >= ? and rnum <= ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, type);

					String kewordSql = "%" + keyword + "%";
					pstmt.setString(2, kewordSql);
					pstmt.setString(3, kewordSql);
					pstmt.setString(4, kewordSql);

					pstmt.setInt(5, start);
					pstmt.setInt(6, end);

				} else {
					System.out.println("============sort : interest classfy : -1==================");
					query = "select * "
							+ "from (select rownum rnum, PA_NUM, ME_NUM, PA_TIME, PA_TOTAL_AMOUNT, "
							+ "           PA_DEPOSIT, PA_PER_AMOUNT, PA_TITLE, PA_CON, PA_ENROLL, "
							+ "           PA_MOD_DATE, PA_DEL_DATE, PA_ACT, PA_VIEWS, PA_LIKE, "
							+ "           PA_COM_COUNT, PA_GENDER_SET, PA_LOCATION, PA_TOTAL_NUM, "
							+ "           PA_GENDER_LIMIT, PA_PAY_CK, PH_NUM, CAT_NUM "
							+ "     from (select * from party "
							+ "           where PA_ACT = ? and ("
							+ "                 REPLACE(PA_TITLE,' ','') like ? "
							+ "            or   REPLACE(PA_CON,' ','') like ? "
							+ "            or   REPLACE(PA_LOCATION,' ','') like ?) "
							+ "           order by PA_VIEWS desc, PA_ENROLL desc)) "
							+ "where rnum >= ? and rnum <= ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, type);

					String kewordSql = "%" + keyword + "%";
					pstmt.setString(2, kewordSql);
					pstmt.setString(3, kewordSql);
					pstmt.setString(4, kewordSql);

					pstmt.setInt(5, start);
					pstmt.setInt(6, end);
				}
			} else {
				if (sort.equals("current")) {

					query = "select * "
							+ "from (select rownum rnum, PA_NUM, ME_NUM, PA_TIME, PA_TOTAL_AMOUNT, "
							+ "           PA_DEPOSIT, PA_PER_AMOUNT, PA_TITLE, PA_CON, PA_ENROLL, "
							+ "           PA_MOD_DATE, PA_DEL_DATE, PA_ACT, PA_VIEWS, PA_LIKE, "
							+ "           PA_COM_COUNT, PA_GENDER_SET, PA_LOCATION, PA_TOTAL_NUM, "
							+ "           PA_GENDER_LIMIT, PA_PAY_CK, PH_NUM, CAT_NUM "
							+ "     from (select * from party "
							+ "			  where PA_ACT = ? and CAT_NUM = ? and ("
							+ "                 REPLACE(PA_TITLE,' ','') like ? "
							+ "            or   REPLACE(PA_CON,' ','') like ? "
							+ "            or   REPLACE(PA_LOCATION,' ','') like ?) "
							+ "           order by PA_ENROLL desc, PA_NUM desc)) "
							+ "where rnum >= ? and rnum <= ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, type);
					pstmt.setInt(2, classfy);

					String kewordSql = "%" + keyword + "%";
					pstmt.setString(3, kewordSql);
					pstmt.setString(4, kewordSql);
					pstmt.setString(5, kewordSql);

					pstmt.setInt(6, start);
					pstmt.setInt(7, end);
				} else {
					query = "select * "
							+ "from (select rownum rnum, PA_NUM, ME_NUM, PA_TIME, PA_TOTAL_AMOUNT, "
							+ "           PA_DEPOSIT, PA_PER_AMOUNT, PA_TITLE, PA_CON, PA_ENROLL, "
							+ "           PA_MOD_DATE, PA_DEL_DATE, PA_ACT, PA_VIEWS, PA_LIKE, "
							+ "           PA_COM_COUNT, PA_GENDER_SET, PA_LOCATION, PA_TOTAL_NUM, "
							+ "           PA_GENDER_LIMIT, PA_PAY_CK, PH_NUM, CAT_NUM "
							+ "     from (select * from party "
							+ "           where PA_ACT = ? and CAT_NUM = ? and ("
							+ "                 REPLACE(PA_TITLE,' ','') like ? "
							+ "            or   REPLACE(PA_CON,' ','') like ? "
							+ "            or   REPLACE(PA_LOCATION,' ','') like ?) "
							+ "           order by PA_VIEWS desc, PA_ENROLL desc)) "
							+ "where rnum >= ? and rnum <= ?";

					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, type);
					pstmt.setInt(2, classfy);

					String kewordSql = "%" + keyword + "%";
					pstmt.setString(3, kewordSql);
					pstmt.setString(4, kewordSql);
					pstmt.setString(5, kewordSql);

					pstmt.setInt(6, start);
					pstmt.setInt(7, end);
				}
			}

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Party party = makeParty(rset);
				// System.out.println("party : " + party);
				partyList.add(party);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return partyList;

	}

	// 정렬 - 관심사
	public ArrayList<Party> searchPartyInterest(Connection conn, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	// 정렬 - 카테고리
	public ArrayList<Party> sortPartyCategory(Connection conn, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	// 정렬 - 날짜
	public ArrayList<Party> sortPartyCurrent(Connection conn, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public Party makeParty(ResultSet rset) throws SQLException {
		Party party = new Party();

		party.setPaNum(rset.getInt("PA_NUM"));
		party.setMeNum(rset.getInt("ME_NUM"));
		party.setPaTime(rset.getDate("PA_TIME"));
		party.setPaTotalAmount(rset.getInt("PA_TOTAL_AMOUNT"));
		party.setPaDeposit(rset.getInt("PA_DEPOSIT"));
		party.setPaPerAmount(rset.getInt("PA_PER_AMOUNT"));
		party.setPaTitle(rset.getString("PA_TITLE"));
		party.setPaCon(rset.getString("PA_CON"));
		party.setPaEnroll(rset.getDate("PA_ENROLL"));
		party.setPaModDate(rset.getDate("PA_MOD_DATE"));
		party.setPaDelDate(rset.getDate("PA_DEL_DATE"));
		party.setPaAct(rset.getString("PA_ACT"));
		party.setPaViews(rset.getInt("PA_VIEWS"));
		party.setPaLike(rset.getInt("PA_LIKE"));
		party.setPaComCount(rset.getInt("PA_COM_COUNT"));
		party.setPaGenderSet(rset.getString("PA_GENDER_SET"));
		party.setPaLocation(rset.getString("PA_LOCATION"));
		party.setPaTotalNum(rset.getInt("PA_TOTAL_NUM"));
		party.setPaGenderLimit(rset.getString("PA_GENDER_LIMIT"));
		party.setPaPayCk(rset.getInt("PA_PAY_CK"));
		party.setPhNum(rset.getInt("PH_NUM"));
		party.setCatNum(rset.getInt("CAT_NUM"));

		return party;
	}

	public String getNowPartyNum(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String nowPartyNum = null;
		String query = "select count(*) from party";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				nowPartyNum = rset.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return nowPartyNum;
	}

	public int getListCount(Connection conn, String act) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from party where pa_act = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, act);// 활성화여부
			rset = pstmt.executeQuery();

			if (rset.next())
				listCount = rset.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public int completeParty(Connection conn, int panum) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {

			String query = "update party set PA_ACT = 'N' where PA_NUM = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, panum);// 시간
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateBanParty(Connection conn, int panum, String partyban) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update party set PA_ACT = ? where PA_NUM = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, partyban);
			pstmt.setInt(2, panum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Party> myReviewParty(Connection conn, int meNum) {
		ArrayList<Party> list = new ArrayList<Party>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * " +
				"from party " +
				"where pa_num in (select pa_num from totalparty where me_num = ?) " +
				"and pa_act = 'N'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, meNum);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Party party = new Party();
				
				party.setPaNum(rset.getInt("PA_NUM"));
				party.setMeNum(rset.getInt("ME_NUM"));
				party.setPaTime(rset.getDate("PA_TIME"));
				party.setPaTotalAmount(rset.getInt("PA_TOTAL_AMOUNT"));
				party.setPaDeposit(rset.getInt("PA_DEPOSIT"));
				party.setPaPerAmount(rset.getInt("PA_PER_AMOUNT"));
				party.setPaTitle(rset.getString("PA_TITLE"));
				party.setPaCon(rset.getString("PA_CON"));
				party.setPaEnroll(rset.getDate("PA_ENROLL"));
				party.setPaModDate(rset.getDate("PA_MOD_DATE"));
				party.setPaDelDate(rset.getDate("PA_DEL_DATE"));
				party.setPaAct(rset.getString("PA_ACT"));
				party.setPaViews(rset.getInt("PA_VIEWS"));
				party.setPaLike(rset.getInt("PA_LIKE"));
				party.setPaComCount(rset.getInt("PA_COM_COUNT"));
				party.setPaGenderSet(rset.getString("PA_GENDER_SET"));
				party.setPaLocation(rset.getString("PA_LOCATION"));
				party.setPaTotalNum(rset.getInt("PA_TOTAL_NUM"));
				party.setPaGenderLimit(rset.getString("PA_GENDER_LIMIT"));
				party.setPaPayCk(rset.getInt("PA_PAY_CK"));
				party.setPhNum(rset.getInt("PH_NUM"));
				party.setCatNum(rset.getInt("CAT_NUM"));

				list.add(party);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updatePayCk(Connection conn, int panum, int price) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update party set PA_PAY_CK = ? where PA_NUM = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, price);
			pstmt.setInt(2, panum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
