package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.Base64;

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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mupdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원정보 수정 처리용 컨트롤러
		
		//1. 전송온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("UTF-8");
				
		//2. 전송온 값 꺼내서 변수 또는 객체에 저장하기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		Member member = new Member();
		
		loginMember.setMeId(request.getParameter("id"));
		String userpwd = request.getParameter("pwd");
		//loginMember.setMePwd(request.getParameter("pwd"));
		loginMember.setMeEmail(request.getParameter("email"));
		loginMember.setMePhone(request.getParameter("phone"));
		loginMember.setMeGender(request.getParameter("gender"));
		loginMember.setMeAka(request.getParameter("aka"));
		
		String cryptoUserpwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			//패스워드 문자열을 암호문으로 바꾸려면, byte[] 로 변환해야 함
			byte[] pwdValues = userpwd.getBytes(Charset.forName("UTF-8"));
			//암호문으로 바꾸기
			md.update(pwdValues);
			//암호화된 byte[] 을 String 으로 바꿈 : 암호문 상태임
			cryptoUserpwd = Base64.getEncoder().encodeToString(pwdValues);
			
			//확인
			//System.out.println("암호화된 패스워드 : " + cryptoUserpwd);
			//System.out.println("글자길이 : " + cryptoUserpwd.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		member.setMePwd(cryptoUserpwd);
		
		//3. 모델 서비스 메소드로 값 또는 객체 전달 실행하고 결과받기
		int result = new MemberService().updateMember(loginMember);
		
		//4. 받은 결과로 내보낼 뷰 선택 처리
		if(result > 0) {
			response.sendRedirect("/comi/main.jsp");
			//response.sendRedirect("/comi/myinfo?meid=" + loginMember.getMeId());
		} else {			
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
		
			request.setAttribute("message", loginMember.getMeId() + "님 정보 수정 실패!");
		
			view.forward(request, response);
			
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
