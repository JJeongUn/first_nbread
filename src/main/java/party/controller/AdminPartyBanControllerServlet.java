package party.controller;

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
 * Servlet implementation class AdminPartyBanControllerServlet
 */
@WebServlet("/partyban")
public class AdminPartyBanControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPartyBanControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int panum = Integer.parseInt(request.getParameter("panum"));
		String ban = request.getParameter("ban");
		String act = request.getParameter("act");
		System.out.println("ban : " + ban);
		System.out.println("act : " + act);
		String partyban = null;
		if(ban.equals("true")){
			if(act.equals("B")){
				partyban = "Y";
			}else{
				partyban = "B";
			}
		}else{
			if(act.equals("B")){
				partyban = "N";
			}
		}

		PartyService partyService = new PartyService();
		int result = partyService.updateBanParty(panum,partyban);
		ArrayList<Member> memberlist = new MemberService().selectMemberAll();
		ArrayList<Report> reportlist = new RepotService().selectReportAll();
		ArrayList<Party> partylist = null;
		if(result > 0){
			partylist = partyService.selectPartyAll();
		}
		RequestDispatcher view = null;
		if(result > 0 && partylist.size() > 0) {
			view = request.getRequestDispatcher("views/admin/adminpage.jsp");
			request.setAttribute("memberlist", memberlist);
			request.setAttribute("partylist",partylist);
			request.setAttribute("reportlist",reportlist);
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "회원 로그인 제한/해제 처리 실패!");
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
