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
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class BusinessSearchServlet
 */
@WebServlet("/bsearch")
public class BusinessSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String keyword = request.getParameter("keyword");
		
		ArrayList<Business> blist = new BusinessService().searchBusiness(keyword);
		ArrayList<Member> memberlist = new MemberService().selectMemberAll();
		RequestDispatcher view = null;
	
		if(blist.size() >= 0) {
			view = request.getRequestDispatcher("views/business/business.jsp");
			request.setAttribute("list", blist);
			request.setAttribute("memberlist", memberlist);
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
