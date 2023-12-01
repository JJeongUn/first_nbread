package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mdelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		 HttpSession session = request.getSession();
		 Member loginMember = (Member) session.getAttribute("loginMember");
		 RequestDispatcher view = null;

		 if (loginMember != null) {
            int result = new MemberService().deleteMember(loginMember.getMeNum());
            if (result > 0) { 
                session.invalidate(); 
                response.sendRedirect("/comi/views/member/member_delete_notification.html");
            } else {
                view = request.getRequestDispatcher("views/common/error.jsp");
                request.setAttribute("message", loginMember.getMeId() + " 회원 삭제 안돼요");
                
                view.forward(request, response);
            }
	            
	        }
	    }

	    private boolean performMemberDeletion(Member loginMember) {
	        return true;
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
