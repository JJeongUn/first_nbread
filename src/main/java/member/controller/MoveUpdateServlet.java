package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MoveUpdateServlet
 */
@WebServlet("/moveup")
public class MoveUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원정보 수정페이지로 이동 처리용 컨트롤러
		
		//2. 전송온 값 꺼내서 변수 또는 객체에 저장하기
		
		String meid = request.getParameter("meid");
		//int menum = Integer.parseInt(request.getParameter("ME_NUM"));
		
		//3. 모델 서비스의 메소드로 값 전달 실행하고 결과 받기
		//int menum = 1;

		Member member = new MemberService().selectMember(meid);
		
		//4. 받은 결과로 성공/실패 페이지 내보내기
		RequestDispatcher view = null;
		
		if(member != null) {
			view = request.getRequestDispatcher("views/member/member_update.jsp");
			request.setAttribute("member", member);
			System.out.println(member);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("message", meid + " 회원정보 조회 실패!");
			
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
