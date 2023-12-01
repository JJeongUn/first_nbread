package qna.controller;

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
import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaSelectAllServlet
 */
@WebServlet("/qnasall")
public class QnaSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaSelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 목록 보기 부분
		request.setCharacterEncoding("utf-8");
		
		int currentPage = 1;

		if(request.getParameter("page") != null){
			currentPage=Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;

		QnaService qservice = new QnaService();

		int listCount = qservice.getListCount();
		System.out.println(listCount);
		Paging paging = new Paging(listCount,currentPage,limit);
		paging.calculator();
		//System.out.println(listCount + ", " + currentPage + ", " + limit);

		ArrayList<Qna> list = qservice.selectQnaAll(paging.getStartRow(),paging.getEndRow());
		ArrayList<Member> memberlist = new MemberService().selectMemberAll();
		RequestDispatcher view = null;

		if(list.size() >= 0){
			view = request.getRequestDispatcher("views/qna/qnalistview.jsp");
			request.setAttribute("list",list);
			request.setAttribute("memberlist",memberlist);
			request.setAttribute("paging",paging);
		}else{
			view = request.getRequestDispatcher("veiws/common/error.jsp");
			request.setAttribute("message", "실패");
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
