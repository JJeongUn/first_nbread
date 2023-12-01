package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/like")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String likeCountString = request.getParameter("likeCount");
        String likeMemberString = request.getParameter("likeMember");
		int likeCount = 0;
        int likeMember = 0;
        JSONObject jsonResponse = new JSONObject();

        try {
            likeCount = Integer.parseInt(likeCountString);
            likeMember = Integer.parseInt(likeMemberString);
            jsonResponse.put("message", "Like 처리 완료!");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResponse.put("message", "파싱 오류 발생");
        }
		int result = new MemberService().likeCountUp(likeMember);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
     
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
