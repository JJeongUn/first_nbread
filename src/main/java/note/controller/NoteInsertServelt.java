package note.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;
import note.model.service.NoteService;
import note.model.vo.Note;

/**
 * Servlet implementation class MessageInsertServelt
 */
@WebServlet("/noinsert")
public class NoteInsertServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteInsertServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int sendNum = Integer.parseInt(request.getParameter("sendnum"));
		String recvNum = request.getParameter("recvnum");
		String con = request.getParameter("note");
		int sendSuccess = 0;
		Note note = new Note();
		String isSuccess = "fail";
		String result = "fail";
		System.out.println("보내는 사람 : " + sendNum + " 받는 사람 : " + recvNum + " 쪽지 내용 : " + con);
		
		Boolean hasRecvNum = new NoteService().hasMember(recvNum);
		System.out.println("hasRecvNum : " + hasRecvNum);
		Member sendMember = new MemberService().selectMember(sendNum);
		Member recvMember = new MemberService().selectMemberAka(recvNum);
		if(sendMember.getMeAka() == recvNum){
			isSuccess = "same";
		}
		if(!hasRecvNum){
			isSuccess = "empty";
		}

		if(isSuccess.equals("fail") ){
			note.setSendNum(sendMember.getMeNum());
			note.setRecvNum(recvMember.getMeNum());
			note.setNoCon(con);
			sendSuccess = new NoteService().insertMessage(note);
			if(sendSuccess == 1){
				result = "success";
				isSuccess = "success";
			}
		}
		System.out.println("isSuccess : " + isSuccess);
		System.out.println("result : " + result);
		// JSON 형식으로 응답을 설정
		JSONObject sendJson = new JSONObject();
		sendJson.put("result", result);
		sendJson.put("isSuccess", isSuccess);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(sendJson.toJSONString());
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
