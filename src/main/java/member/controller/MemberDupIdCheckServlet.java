package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDupIdCheckServlet
 */
@WebServlet("/dupid")
public class MemberDupIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDupIdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type"); // type 파라미터로 아이디 또는 별명 구분
        String value = request.getParameter("value"); // 중복 체크할 값
		String returnValue = null; // 중복 체크 결과를 저장할 변수

		if ("id".equals(type)) {
            // 아이디 중복 체크 로직
            int idCount = new MemberService().selectCheckId(value);
            if (idCount == 0) {
                returnValue = "ok";
            } else {
                returnValue = "duplicate";
            }
        } else if ("nick".equals(type)) {
			int akaCount = new MemberService().selectCheckAka(value);
            if (akaCount == 0) {
                returnValue = "ok";
            } else {
                returnValue = "duplicate";
            }
        }
		
		//ajax 통신은 네트워크 입출력임 : 별도의 스트림을 열어서 사용함
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
