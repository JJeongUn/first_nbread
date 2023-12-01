package totalparty.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import party.model.service.PartyService;
import totalparty.model.service.TotalPartyService;

/**
 * Servlet implementation class TotalPartyUpdateServlet
 */
@WebServlet("/totalpartyup")
public class TotalPartyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalPartyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int panum = Integer.parseInt(request.getParameter("panum"));
		int price = Integer.parseInt(request.getParameter("price"));
		int menum = Integer.parseInt(request.getParameter("menum"));
		
		int result = new TotalPartyService().updateTotalParty(panum, menum);
		
		if(result > 0) {
			
			int result2 = new PartyService().updatePayCk(panum, price);
			if(result2 > 0) {
				response.sendRedirect("/comi/partysel?panum="+panum+"&act=Y");
			}
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
