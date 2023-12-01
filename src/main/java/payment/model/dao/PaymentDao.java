package payment.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import payment.model.vo.Payment;

public class PaymentDao {

	public Payment selectPayment(Connection conn, int paymentid) {
		Payment payment = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from payment where PM_NUM = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, paymentid);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				payment = new Payment();
				
				payment.setPmNum(rset.getInt("PM_NUM"));
				payment.setMeNum(rset.getInt("ME_NUM"));
				payment.setPaNum(rset.getInt("PA_NUM"));
				payment.setPmHost(rset.getString("PM_HOST"));
				payment.setPmMethod(rset.getString("PM_METHOD"));
				payment.setPmAmount(rset.getInt("PM_AMOUNT"));
				payment.setPmCredits(rset.getInt("PM_CREDITS"));
				payment.setPmTotalAmount(rset.getInt("PM_TOTAL_AMOUNT"));
				payment.setPmDeposit(rset.getString("PM_DEPOSIT"));
				payment.setPmDate(rset.getDate("PM_DATE"));
				payment.setPmAcVer(rset.getString("PM_AC_VER"));
				payment.setPmPhVer(rset.getString("PM_PH_VER"));
				payment.setPmEasyPay(rset.getString("PM_EASY_PAY"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return payment;
	}

	public ArrayList<Payment> selectPaymentAll(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertPayment(Connection conn, Payment payment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into payment " + 
				"values ((select count(*)+1 from payment), ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "sysdate, default, default, default)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, payment.getMeNum());
			pstmt.setInt(2,payment.getPaNum());
			pstmt.setString(3, payment.getPmHost());
			pstmt.setString(4, payment.getPmMethod());
			pstmt.setInt(5, payment.getPmAmount());
			pstmt.setInt(6, 0);//payment.getPmCredits());
			pstmt.setInt(7, payment.getPmAmount());//payment.getPmTotalAmount());
			pstmt.setString(8, payment.getPmDeposit());
			System.out.println("payment.getMeNum() : " + payment.getMeNum());
			//pstmt.setString(9, payment.getPmAcVer());
			//pstmt.setString(10, payment.getPmPhVer());
			//pstmt.setString(11, payment.getPmEasyPay());
			
			result = pstmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deletePayment(Connection conn, Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
