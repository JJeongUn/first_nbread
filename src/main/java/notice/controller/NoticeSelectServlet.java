package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import static common.PhotoTemplate.*;

/**
 * Servlet implementation class NoticeSelectServlet
 */
@WebServlet("/noticesel")
public class NoticeSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noNum = Integer.parseInt(request.getParameter("nonum"));
		int currentPage = Integer.parseInt(request.getParameter("page"));

		
		Notice notice = new NoticeService().selectNotice(noNum);
		Member member = new MemberService().selectMember(notice.getMeNum());
		Photo photo = searchPhoto(notice, "notice");
		RequestDispatcher view = null;
		new NoticeService().addReadCount(noNum);


		if(notice != null){
			view = request.getRequestDispatcher("views/notice/notice_detail.jsp");
			request.setAttribute("notice",notice);
			request.setAttribute("photo",photo);
			request.setAttribute("member",member);
			request.setAttribute("currentPage",currentPage);

		}else{
			view = request.getRequestDispatcher("main.jsp");
			// 에러페이지 급구!!!
		}
		view.forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
