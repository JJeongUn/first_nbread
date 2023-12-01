package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Servlet implementation class FollowingServlet
 */
@WebServlet("/following")
public class FollowingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String following = request.getParameter("following");
        String followers = request.getParameter("followers");
		int followersMember = 0;
        int followingMember = 0;
        JSONObject jsonResponse = new JSONObject();

        try {
            followersMember = Integer.parseInt(followers);
            followingMember = Integer.parseInt(following);
            jsonResponse.put("message", "Like 처리 완료!");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResponse.put("message", "파싱 오류 발생");
        }
		int result = new MemberService().following(followingMember,followersMember);

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
