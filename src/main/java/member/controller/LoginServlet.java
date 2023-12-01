package member.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String meId = request.getParameter("meid");
		String mePwd = request.getParameter("mepwd");
		
		String cryptoUserpwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			//패스워드 문자열을 암호문으로 바꾸려면, byte[] 로 변환해야 함
			byte[] pwdValues = mePwd.getBytes(Charset.forName("UTF-8"));
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
		
		//3. 서비스 메소드로 값 전달 실행하고 결과받기
		//Member loginMember = new MemberService().selectLogin(meId, mePwd);
		Member loginMember = new MemberService().selectLogin(meId, cryptoUserpwd);
		
		//4. 받은 결과를 가지고 성공/실패 페이지 내보내기
		if(loginMember != null && loginMember.getMeBan().equals("N") &&  loginMember.getMeLoginType().equals("N")) {
		   	//로그인 성공
			//로그인 상태 확인용 세션 객체 생성함
			HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(30 * 60);  //30분동안 활동이 없으면 자동 파기함(로그아웃됨)
			
			//System.out.println("생성된 세션 객체의 id : " + session.getId());
			
			//로그인한 회원의 정보를 세션객체에 저장함
			session.setAttribute("loginMember", loginMember);
			
			//로그인 성공시 내보낼 페이지 지정
			response.sendRedirect("main.jsp");
			
		}else {  //로그인 실패
			//클라이언트 브라우저로 내보낼 뷰 파일과 메세지 지정
			//서블릿의 위치는 모두 root 에서 실행되고 있음
			
			//상대경로만 사용할 수 있음
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			
			//지정한 뷰로 내보낼 값이 있다면
			if(loginMember != null && loginMember.getMeBan().equals("Y")) {
				request.setAttribute("message", "로그인이 제한된 회원입니다. 관리자에게 문의하세요.");
			}
			if(loginMember != null && loginMember.getMeLoginType().equals("Y")) {
				request.setAttribute("message", "삭제된 회원입니다.");
			}
			
			if(loginMember == null) {
				request.setAttribute("message", "로그인 실패! 아이디 또는 암호를 다시 확인하고 로그인하세요.");
			}
			
			//요청한 클라이언트로 전송함
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
