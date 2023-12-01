package party.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.JsonReturn;
import party.model.service.PartyService;
import party.model.vo.Party;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class PartySelectList
 */
@WebServlet("/partylist")
public class PartySelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartySelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startStr = request.getParameter("start");
		String endStr = request.getParameter("end");
		int panum = Integer.parseInt(request.getParameter("panum"));
		int start = (startStr == null) ? 1 : Integer.parseInt(startStr);
		int end = (endStr == null) ? 10 : Integer.parseInt(endStr);
		
		System.out.println("panum : " + panum + ", start : " + start);
		
		ArrayList<Party> list = new PartyService().selectPartyList("open", start, end, Integer.toString(panum));//활성화
		ArrayList<Photo> photoList = new PhotoService().selectPhotoList();
		System.out.println("list : " + list);
		System.out.println("photoList : " + photoList );
		
		JSONArray jarr = new JSONArray();
		
		for(Party party : list) {
			JSONObject json = new JsonReturn().returnParty(party);
			jarr.add(json);
		}
		JSONObject sendJson = new JSONObject();
		sendJson.put("list", jarr);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(sendJson);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
