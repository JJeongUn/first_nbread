package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

import static common.PhotoTemplate.*;
/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/noticedel")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		int noNum = Integer.parseInt(request.getParameter("nonum"));
		System.out.println(noNum);
		NoticeService noticeService = new NoticeService();
		Notice deleteNotice = noticeService.selectNotice(noNum);
		int result = noticeService.deleteNotice(noNum);
		if(deleteNotice.getNoPhotoNum() != 0){
			deletePhoto(request.getSession().getServletContext().getRealPath("/resources/noticefile"),
				deleteNotice.getNoPhotoNum());
		}
		
		if(result > 0){
			response.sendRedirect("/comi/noticesall");
		}else {
			//에러 페이지 급구 
			RequestDispatcher view = request.getRequestDispatcher("main.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
