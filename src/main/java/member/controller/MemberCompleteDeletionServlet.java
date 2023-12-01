package member.controller;

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
import party.model.service.PartyService;
import party.model.vo.Party;
import report.model.service.RepotService;
import report.model.vo.Report;

/**
 * Servlet implementation class MemberCompleteDeletionServlet
 */
@WebServlet("/membercomdel")
public class MemberCompleteDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCompleteDeletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNum = Integer.parseInt(request.getParameter("usernum"));

		int result = new MemberService().memberComDel(userNum);
		ArrayList<Member> memberList = new MemberService().selectMemberAll();
		ArrayList<Party> partyList = new PartyService().selectPartyAll();
		ArrayList<Report> reportlist = new RepotService().selectReportAll();
		RequestDispatcher view = null;
		if(result > 0){
			view = request.getRequestDispatcher("views/admin/adminpage.jsp");
			request.setAttribute("memberlist",memberList);
			request.setAttribute("partylist",partyList);
			request.setAttribute("reportlist",reportlist);
			view.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
