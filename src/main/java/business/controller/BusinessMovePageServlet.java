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

/**
 * Servlet implementation class BusinessMovePageServlet
 */
@WebServlet("/bmovepage")
public class BusinessMovePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessMovePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 수정페이지 출력 요청 처리용 컨트롤러
		// 전송받은 글번호를 조회해서 수정페이지 수정할 글 내용 출력되게 전달
		
		int buNum = Integer.parseInt(request.getParameter("bnum"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		Business business = new BusinessService().selectBusiness(buNum);
		
		request.setAttribute("business", business);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher view = null;
		if(business != null) {
			view = request.getRequestDispatcher("views/business/buUpdateView.jsp");
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", buNum + "번 게시 원글 조회 실패.");
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
