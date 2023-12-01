package note.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import note.model.service.NoteService;
import note.model.vo.Note;

/**
 * Servlet implementation class MessageSelectServelt
 */
@WebServlet("/noselect")
public class NoteSelectServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteSelectServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int noNum = Integer.parseInt(request.getParameter("nonum"));
		int noteCheck = 0;
		Note note = new NoteService().selectNote(noNum);

		if(note.getNoCh().equals("N")){
			noteCheck = new NoteService().noteCheck("Y",note.getNoNum());
		}

		Member sendMember = new MemberService().selectMember(note.getSendNum());
		if(note != null && noteCheck >= 0){
			RequestDispatcher view = request.getRequestDispatcher("views/note/notedetail.jsp");
			request.setAttribute("note",note);
			request.setAttribute("sendmember",sendMember);
			view.forward(request,response);
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
