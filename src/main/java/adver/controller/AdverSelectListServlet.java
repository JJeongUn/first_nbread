package adver.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adver.model.service.AdverService;
import adver.model.vo.Adver;
import common.Paging;

/**
 * Servlet implementation class AdverSelectListServlet
 */
@WebServlet("/adsall")
public class AdverSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdverSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentPage = 1;

		if(request.getParameter("page") != null){
			currentPage=Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;

		AdverService adService = new AdverService();
		int listCount = adService.getListCount();
		System.out.println(listCount);
		Paging paging = new Paging(listCount,currentPage,limit);
		paging.calculator();
		
		ArrayList<Adver> list = adService.selectNoticeAll(paging.getStartRow(),paging.getEndRow());
		
		RequestDispatcher view = null;

		if(list.size() >= 0){
			view = request.getRequestDispatcher("views/adver/adver_view.jsp");
			request.setAttribute("list",list);
			request.setAttribute("paging",paging);
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
