package report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import party.model.service.PartyService;
import partyCo.model.service.PartyCoService;
import report.model.service.RepotService;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reportNum = Integer.parseInt(request.getParameter("num"));
		String reportpartyname = request.getParameter("reportpartyname");
		String reportcon = null;
		if(reportpartyname.equals("member")){
			reportcon = new MemberService().selectMember(reportNum).getMeAka();
		}else if(reportpartyname.equals("party")){
			reportcon = new PartyService().selectParty(reportNum).getPaCon();
		}else if(reportpartyname.equals("com")){
			reportcon = new PartyCoService().selectPartyCo(reportNum).getComCon();
		}
		int resultReport = new RepotService().reportInsert(reportNum,reportpartyname,reportcon);
		if(resultReport > 0){
			response.sendRedirect("main.jsp");
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
