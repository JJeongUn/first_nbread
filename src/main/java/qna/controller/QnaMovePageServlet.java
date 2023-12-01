package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaMovePageServlet
 */
@WebServlet("/qnamovepage")
public class QnaMovePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaMovePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 수정페이지 출력 요청 처리용 컨트롤러
		// 전송받은 글번호를 조회해서 수정페이지에 수정할 글 내용이 출력되게 전달해야함
		
		int qnaNum = Integer.parseInt(request.getParameter("qnum"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		Qna qna = new QnaService().selectQna(qnaNum);
		
		request.setAttribute("qna", qna); // qna 변수에 해당하는 데이터를 전달
		request.setAttribute("currentPage", currentPage); // currentPage 변수에 해당하는 데이터를 전달
		
		RequestDispatcher view = null;
		if(qna != null) {
			view = request.getRequestDispatcher("views/qna/qnaUpdateView.jsp");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", qnaNum + "번 게시 원글 조회 실패.");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
