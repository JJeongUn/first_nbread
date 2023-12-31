package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import member.model.service.MemberService;
import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSelectAllServlet
 */
@WebServlet("/noticesall")
public class NoticeSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 목록 보기 부분
		int currentPage = 1;

		if(request.getParameter("page") != null){
			currentPage=Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;
		
		if(request.getParameter("limit") != null) {
	         limit = Integer.parseInt(request.getParameter("limit"));
	      }
		
		NoticeService noService = new NoticeService();

		int listCount = noService.getListCount();
		//System.out.println(listCount);
		Paging paging = new Paging(listCount,currentPage,limit, "noticesall");
		paging.calculator();
		System.out.println(listCount + ", " + currentPage + ", " + limit);
		System.out.println(paging);
		ArrayList<Notice> list = noService.selectNoticeAll(paging.getStartRow(),paging.getEndRow());
		ArrayList<Member> memberlist = new MemberService().selectMemberAll();
		RequestDispatcher view = null;

		if(list.size() >= 0){
			view = request.getRequestDispatcher("views/notice/notice_view.jsp");
			request.setAttribute("list",list);
			request.setAttribute("memberlist",memberlist);
			request.setAttribute("paging",paging);
			request.setAttribute("currentPage", currentPage);
		}else{
			view = request.getRequestDispatcher("main.jsp");
			//request.setAttribute("message", "에러 페이지 만들어야함");
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
