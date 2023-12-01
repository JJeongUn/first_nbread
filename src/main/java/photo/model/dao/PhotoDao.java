package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.*;
import photo.model.vo.Photo;
import static common.JDBCTemplate.*;
public class PhotoDao {
	public PhotoDao() {}

	public int insertPhoto(Connection conn, Photo photo, String saveDatabase) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		if(saveDatabase.toLowerCase().equals("noticephoto")) {
			query = "insert into noticephoto values (?,?,?,?,?,?)";
		}else if(saveDatabase.toLowerCase().equals("photo")) {
			query = "insert into photo values (?,?,?,?,?,?)";
		}else if(saveDatabase.toLowerCase().equals("adphoto")){
			query = "insert into adphoto values (?,?,?,?,?,?)";
		}
		//System.out.println("들어옴!");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, photo.getPhotonum());
			pstmt.setString(2, photo.getPhoto1());
			pstmt.setString(3, photo.getPhoto2());
			pstmt.setString(4, photo.getPhoto3());
			pstmt.setString(5, photo.getPhoto4());
			pstmt.setString(6, photo.getPhoto5());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProfilePhoto(Connection conn, Member member, String profilePhotoFileName) {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    String query = null;

	    query = "UPDATE member SET me_photo_add = ? WHERE me_num = ?";

	    try {
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, profilePhotoFileName);
	        pstmt.setInt(2, member.getMeNum());

	        result = pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}
	
	public int sequencePhotoNum(Connection conn, String saveDatabase) {
		int result = 0;
		PreparedStatement pstmt = null;
		//Statement stmt = null;
		ResultSet rset = null;
		System.out.println(saveDatabase.trim());
		String query = null;
		if(saveDatabase.toLowerCase().equals("noticephoto")){
			query = "select max(no_ph_num)+1 from noticephoto";
		}else if(saveDatabase.toLowerCase().equals("photo")) {
			query = "select max(ph_num)+1 from photo";
		}else if(saveDatabase.toLowerCase().equals("adphoto")){
			query = "select max(ad_ph_num)+1 from adphoto";
		}
		
		try {
			//stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
				System.out.println("result : " + result); 
			}
			
			//result = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Photo selectPhoto(Connection conn, int phNum, String saveDatabase) {
		Photo photo = new Photo();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;

		if(saveDatabase.toLowerCase().equals("notice")){
			query = "select * from noticephoto where no_ph_num = ?";
		}else if(saveDatabase.toLowerCase().equals("party")){
			query = "select * from photo where ph_num = ?";
		}else if(saveDatabase.toLowerCase().equals("qna")){
			query = "select * from qaphoto where qa_photo_num = ?";
		}else{
			return null;
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, phNum);
			rset = pstmt.executeQuery();

			if(rset.next()){
				photo.setPhotonum(phNum);
				photo.setPhoto1(rset.getString(2));
				photo.setPhoto2(rset.getString(3));
				photo.setPhoto3(rset.getString(4));
				photo.setPhoto4(rset.getString(5));
				photo.setPhoto5(rset.getString(6));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return photo;
	}

	public ArrayList<Photo> selectPhotoList(Connection conn) {
		ArrayList resultList = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from photo";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Photo photo = new Photo();
				photo.setPhotonum(rset.getInt(1));
				photo.setPhoto1(rset.getString(2));
				photo.setPhoto2(rset.getString(3));
				photo.setPhoto3(rset.getString(4));
				photo.setPhoto4(rset.getString(5));
				photo.setPhoto5(rset.getString(6));
				resultList.add(photo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return resultList;
	}

	public ArrayList<Photo> searchPhotoList(Connection conn, String saveDatabase) {
		ArrayList<Photo> list = new ArrayList<Photo>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = null;
		if(saveDatabase.equals("party")) {
			query = "select * from photo";
			System.out.println("dao : party : " + query);
		}else if(saveDatabase.equals("notice")){
			query = "select * from noticephoto";
			System.out.println("dao : notice : " + query);
		}else if(saveDatabase.equals("qna")) {
			query = "select * from qaphoto";
			System.out.println("dao : qa : " + query);
		}
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Photo photo = new Photo();
				photo.setPhotonum(rset.getInt(1));
				
				photo.setPhoto1(rset.getString(2));
				
				photo.setPhoto2(rset.getString(3));
				
				photo.setPhoto3(rset.getString(4));
			
				photo.setPhoto4(rset.getString(5));
				
				photo.setPhoto5(rset.getString(6));
				
				list.add(photo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			query = null;
			close(rset);
			close(stmt);
		}
		return list;
	}
	
	
}
