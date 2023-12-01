package business.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.model.service.BusinessService;
import business.model.vo.Business;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class BusinessDetailServlet
 */
@WebServlet("/bdetail")
public class BusinessDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Qna 상세보기 처리용 컨트롤러 
		int buNum = Integer.parseInt(request.getParameter("bnum"));
		
		//페이징 처리를 위한 페이지 변수
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		//모델 서비스 객체 생성
		BusinessService bservice = new BusinessService();
		
		//해당 게시글에 대한 조회수 1증가 처리 : update
		bservice.addReadCount(buNum);
		
		//해당 게시글 리턴 받음 : select
		Business business = bservice.selectBusiness(buNum);
		
		Member member = new MemberService().selectMember(business.getMeNum());
		
		RequestDispatcher view = null;
		if(business != null) {
			view = request.getRequestDispatcher("views/business/buDetailView.jsp");
			request.setAttribute("member",member);
			request.setAttribute("business", business);
			request.setAttribute("currentPage", currentPage);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
