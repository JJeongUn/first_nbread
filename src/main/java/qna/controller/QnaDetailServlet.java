package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/qdetail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Qna 상세보기 처리용 컨트롤러 
		int qaNum = Integer.parseInt(request.getParameter("qnum"));
		
		//페이징 처리를 위한 페이지 변수
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//모델 서비스 객체 생성
		QnaService qservice = new QnaService();
		
		//해당 게시글에 대한 조회수 1증가 처리 : update
		qservice.addReadCount(qaNum);
		
		//해당 게시글 리턴 받음 : select
		Qna qna = qservice.selectQna(qaNum);

		Member member = new MemberService().selectMember(qna.getMeNum());

		RequestDispatcher view = null;
		if(qna != null)  {
			view = request.getRequestDispatcher("views/qna/qnaDetailView.jsp");
			request.setAttribute("member",member);
			request.setAttribute("qna", qna);
			request.setAttribute("currentPage", currentPage);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");

			request.setAttribute("message", qaNum + "번 글 상세조회 실패!");
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
