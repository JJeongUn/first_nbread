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
 * Servlet implementation class AdminBanControllerServlet
 */
@WebServlet("/loginok")
public class AdminBanControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBanControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int usernum = Integer.parseInt(request.getParameter("usernum"));
		String ok = request.getParameter("ok");
		
		String loginok = null;
		if(ok.equals("true")) {
			loginok = "Y";
		}else {
			loginok = "N";
		}
		MemberService memberService = new MemberService();
		int result = memberService.updateBan(usernum,loginok);
		ArrayList<Party> partylist = new PartyService().selectPartyAll();
		ArrayList<Report> reportlist = new RepotService().selectReportAll();
		ArrayList<Member> list = null;
		System.out.println("AdminBanController result : " + result);
		if(result > 0){
			list = memberService.selectMemberAll();
		}

		RequestDispatcher view = null;
		if(result > 0 && list.size() > 0) {
			view = request.getRequestDispatcher("views/admin/adminpage.jsp");
			request.setAttribute("memberlist", list);
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
