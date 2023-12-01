package party.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import totalparty.model.service.TotalPartyService;

/**
 * Servlet implementation class PartyJoinServlet
 */
@WebServlet("/joinparty")
public class PartyJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartyJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int joinmember = Integer.parseInt(request.getParameter("loginmember"));
		int partyNum = Integer.parseInt(request.getParameter("party"));
		String actParty = request.getParameter("act");
		System.out.println("joinMember = " + joinmember + ", partyNumber : " + partyNum + " actParty : " + actParty);
		int totalCount = new TotalPartyService().getTotalCount(partyNum);
		int joinResult = new TotalPartyService().joinParty(partyNum,joinmember);

		if(joinResult > 0){
			response.sendRedirect("/comi/partysel?panum="+ partyNum + "&act="+ actParty);
		}else{
			response.sendRedirect("/comi/main.jsp");
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
