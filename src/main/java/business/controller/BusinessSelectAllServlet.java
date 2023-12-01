package business.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.model.service.BusinessService;
import business.model.vo.Business;
import common.Paging;

/**
 * Servlet implementation class BusinessSelectAllServlet
 */
@WebServlet("/bselectall")
public class BusinessSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessSelectAllServlet() {
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
		
		BusinessService bservice = new BusinessService();
		
		int listCount = bservice.getListCount();
		System.out.println(listCount);
		Paging paging = new Paging(listCount,currentPage,limit);
		paging.calculator();
		
		ArrayList<Business> list = bservice.selectBuAll(paging.getStartRow(),paging.getEndRow());
		
		RequestDispatcher view = null;
		
		if(list.size() >= 0) {
			view = request.getRequestDispatcher("views/business/buListView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
		}else {
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
