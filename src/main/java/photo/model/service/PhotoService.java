package photo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.vo.Member;
import photo.model.dao.PhotoDao;
import photo.model.vo.Photo;

import static common.JDBCTemplate.*;
public class PhotoService {
	PhotoDao dao = new PhotoDao();
	
	public PhotoService() {}


	public int insertPhoto(Photo photo, String saveDatabase) {
		Connection conn = getConnection();
		int result = dao.insertPhoto(conn, photo,saveDatabase);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 프로필 사진 추가
	public int updateProfilePhoto(Member member, String profilePhotoFileName) {
	    Connection conn = getConnection();
	    int result = dao.updateProfilePhoto(conn, member, profilePhotoFileName);
	    if (result > 0) {
	        commit(conn);
	    } else {
	        rollback(conn);
	    }
	    close(conn);
	    return result;
	}
	
	public int sequencePhotoNum(String saveDatabase) {
		Connection conn = getConnection();
		int result =  dao.sequencePhotoNum(conn,saveDatabase );
		close(conn);
		return result;
	}


	public Photo selectPhoto(int phNum, String saveDatabase) {
		Connection conn = getConnection();
		Photo resultPhoto = dao.selectPhoto(conn,phNum,saveDatabase);
		close(conn);
		return resultPhoto;
	}


	public ArrayList<Photo> selectPhotoList() {
		Connection conn = getConnection();
		ArrayList<Photo> resultList = dao.selectPhotoList(conn);
		close(conn);
		return resultList;
	}


	public ArrayList<Photo> searchPhotoList(String saveDatabase) {
		ArrayList<Photo> list = null;
		System.out.println("53번째 줄 photoService");
		Connection conn = getConnection();
		list = dao.searchPhotoList(conn,saveDatabase);
		close(conn);
		return list;
	}	
}
