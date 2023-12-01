package adver.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import adver.model.vo.Adver;
import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class AdverDao {

	public AdverDao() {
		super();
		// TODO Auto-generated constructor stub
	}

    public int getListCount(Connection conn) {
        int result = 0;
        Statement stmt = null;
        ResultSet rset = null;
        String query = "select count(*) from advertisement";

        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                result = rset.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(rset);
            close(stmt);
        }
        return result;
    }

    public ArrayList<Adver> selectNoticeAll(Connection conn, int startPage, int endPage) {
        ArrayList<Adver> list = new ArrayList<Adver>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = "select * " + //
                "from(select rownum,ad_num,ad_title,ad_url,ad_pay,ad_sign_off_on,ad_enroll,ad_sta_date,ad_end_date,ad_lev,me_num,ad_ph_num " + //
                "    from(select * from advertisement  " + //
                "    order by ad_enroll desc )) " + //
                "where rownum between ? and ?";

        try {
            pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, endPage);
			rset = pstmt.executeQuery();

            while(rset.next()){
                Adver adver = new Adver();
                adver.setAdNum(rset.getInt(2));
                adver.setAdTitle(rset.getString(3));
                adver.setAdUrl(rset.getString(4));
                adver.setAdPay(rset.getInt(5));
                adver.setAdSignOffOn(rset.getString(6));
                adver.setAdEnroll(rset.getDate(7));
                adver.setAdStaDate(rset.getDate(8));
                adver.setAdEndDate(rset.getDate(9));
                adver.setAdLev(rset.getInt(10));
                adver.setMeNum(rset.getInt(11));
                adver.setAdPhNum(rset.getInt(12));
                list.add(adver);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(rset);
            close(pstmt);
        }
        return list;

    }

    public int insertAdver(Connection conn, Adver adver, int photoNum) {
        int result = 0;
        PreparedStatement pstmt = null;
        String query = "insert into advertisement " + //
                "values ((select max(ad_num)+1 from advertisement),? , ?,0,default,sysdate,?,?,1,?,?)";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, adver.getAdTitle());
            pstmt.setString(2,adver.getAdUrl());
            pstmt.setDate(3, adver.getAdStaDate());
            pstmt.setDate(4, adver.getAdEndDate());
            pstmt.setInt(5, adver.getMeNum());
            pstmt.setInt(6, adver.getAdPhNum());

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(pstmt);
        }
        return result;
    }

	public ArrayList<Adver> selectAllAdver(Connection conn) {
		ArrayList<Adver> list = new ArrayList<Adver>();
        Statement stmt = null;
        ResultSet rset = null;
        String query = "select * "
        		+ "from advertisement "
        		+ "left join adphoto using(AD_PH_NUM) "
        		+ "where sysdate between AD_STA_DATE and AD_END_DATE "
        		+ "order by ad_lev desc, ad_pay desc";

        try {
        	stmt = conn.createStatement();
        	rset = stmt.executeQuery(query);
            
            while(rset.next()){
                Adver adver = new Adver();
                
                adver.setAdNum(rset.getInt("AD_NUM"));
                adver.setAdTitle(rset.getString("AD_TITLE"));
                adver.setAdUrl(rset.getString("AD_URL"));
                adver.setAdPay(rset.getInt("AD_PAY"));
				adver.setAdSignOffOn(rset.getString("AD_SIGN_OFF_ON"));
				adver.setAdEnroll(rset.getDate("AD_ENROLL"));
				adver.setAdStaDate(rset.getDate("AD_STA_DATE"));
				adver.setAdEndDate(rset.getDate("AD_END_DATE"));
				adver.setAdLev(rset.getInt("AD_LEV"));
				adver.setMeNum(rset.getInt("ME_NUM"));
				adver.setAdPhNum(rset.getInt("AD_PH_NUM"));
                adver.setAdPhoto1(rset.getString("AD_PHOTO1"));
                adver.setAdPhoto2(rset.getString("AD_PHOTO2"));
                adver.setAdPhoto3(rset.getString("AD_PHOTO3"));
                adver.setAdPhoto4(rset.getString("AD_PHOTO4"));
                adver.setAdPhoto5(rset.getString("AD_PHOTO5"));
				
                list.add(adver);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(rset);
            close(stmt);
        }
        return list;
	}
    
}
