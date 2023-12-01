package adver.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import adver.model.dao.AdverDao;
import adver.model.vo.Adver;
import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class AdverService {
	private AdverDao dao = new AdverDao();
	public AdverService() {
		super();
		// TODO Auto-generated constructor stub
	}
    public int getListCount() {
        int result = 0;
        Connection conn = getConnection();
        result = dao.getListCount(conn);
        close(conn);
        return result;
    }
    public ArrayList<Adver> selectNoticeAll(int startPage, int endPage) {
        Connection conn = getConnection();
		ArrayList<Adver> list = dao.selectNoticeAll(conn,startPage,endPage);
		close(conn);
		return list;
    }
    public int insertAdver(Adver adver, int photoNum) {
        int result = 0;
        Connection conn = getConnection();
        result = dao.insertAdver(conn,adver,photoNum);
        if(result > 0){
            commit(conn);
        }else{
            rollback(conn);
        }
        close(conn);
        return result;
    }
	public ArrayList<Adver> selectAllAdver() {
		Connection conn = getConnection();
		ArrayList<Adver> list = dao.selectAllAdver(conn);
		close(conn);
		return list;
	}
    
}
