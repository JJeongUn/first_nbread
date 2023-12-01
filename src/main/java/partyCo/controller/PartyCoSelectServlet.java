package partyCo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.JsonReturn;
import common.Paging;
import party.model.service.PartyService;
import partyCo.model.service.PartyCoService;
import partyCo.model.vo.PartyCo;

/**
 * Servlet implementation class PartyReSelectAllServlet
 */
@WebServlet("/partycosel")
public class PartyCoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartyCoSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int panum = Integer.parseInt(request.getParameter("panum"));
		int currentPage = 1;
		//전송온 페이지 값이 있다면 추출함
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		PartyCoService coservice = new PartyCoService();
		
		//한페이지당 목록 갯수
		int limit = 10;//한페이지당 목록 갯수
		
		int listCount = coservice.getListCount(panum);
		System.out.println("??listCount : " + listCount);
		Paging paging = new Paging(listCount, currentPage, limit);
		paging.calculator();
		
		//댓글 부분
		ArrayList<PartyCo> partyCoList = coservice.selectPartyCoList(panum, paging.getStartRow(), paging.getEndRow());
		System.out.println("??partyCoList : " + partyCoList);
		
		//이중배열 만들어 보내기
		JSONArray coList = null;
		//ArrayList<ArrayList<PartyCo>> coList = null;
		int count = 0;
		if(partyCoList.size() > 0) {
			
			//coList = new ArrayList<ArrayList<PartyCo>>();
			coList = new JSONArray();
			//ArrayList<PartyCo> cList = new ArrayList<PartyCo>();
			JSONArray cList = new JSONArray();
			
			for(int i=0; i<partyCoList.size(); i++) {
				//System.out.println("i : " + i);
				int nextDepth = (i < partyCoList.size()-1) ? partyCoList.get(i+1).getComDepth() : -1;//다음번 뎁스
				
				cList.add(new JsonReturn().returnPartyCo(partyCoList.get(i)));
				
				if(nextDepth == 1 || i == partyCoList.size()-1) {//마지막이거나 다음 뎁스가 1일때
					
					JSONArray ctemp = (JSONArray) cList.clone();
					coList.add(ctemp);
					cList.clear();
					count ++;
				}
			}
		}
		JSONObject pagingJason = new JsonReturn().returnPaging(paging);
		
		
		JSONObject sendJson = new JSONObject();
		sendJson.put("paging", pagingJason);
		sendJson.put("list", coList);
		sendJson.put("listCount", listCount);
		System.out.println("sendJson : " + sendJson);
		
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
