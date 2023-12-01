package partyCo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.JsonReturn;
import common.Paging;
import partyCo.model.service.PartyCoService;
import partyCo.model.vo.PartyCo;

/**
 * Servlet implementation class PartyReSelectAllServlet
 */
@WebServlet("/partycosort2")
public class PartyCoSortServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartyCoSortServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		int panum = Integer.parseInt(request.getParameter("panum"));
		int currentPage = 1;
		int limit = 10;
		//전송온 페이지 값이 있다면 추출함
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		PartyCoService coservice = new PartyCoService();
		
		//한페이지당 목록 갯수
		
		int listCount = coservice.getListCount(panum);
		System.out.println("??listCount : " + listCount);
		Paging paging = new Paging(listCount, currentPage, limit);
		paging.calculator();
		
		//댓글 부분
		ArrayList<PartyCo> partyCoList
		= coservice.sortPartyCo(panum, paging.getStartRow(), paging.getEndRow(), type);
		
		System.out.println("??partyCoList : " + partyCoList);
		
		//이중배열 만들어 보내기
		ArrayList<ArrayList<PartyCo>> coList = null;
		JSONArray jcoList = null;
		
		int count = 0;
		if(partyCoList.size() > 0) {
			
			coList = new ArrayList<ArrayList<PartyCo>>();
			ArrayList<PartyCo> cList = new ArrayList<PartyCo>();
			
			for(int i=0; i<partyCoList.size(); i++) {
				//System.out.println("i : " + i);
				int nextDepth = (i < partyCoList.size()-1) ? partyCoList.get(i+1).getComDepth() : -1;//다음번 뎁스
				
				//cList.add(new JsonReturn().returnPartyCo(partyCoList.get(i)));
				cList.add(partyCoList.get(i));
				if(nextDepth == 1 || i == partyCoList.size()-1) {//마지막이거나 다음 뎁스가 1일때
					
					ArrayList<PartyCo> ctemp = (ArrayList<PartyCo>) cList.clone();
					coList.add(ctemp);
					cList.clear();
					count ++;
				}
			}
			System.out.println("type : " + type);
			System.out.println("sort coList 1 : " + coList);
			
			Collections.sort(coList, (o1, o2) -> {
				return o2.get(0).getComViews() - o1.get(0).getComViews();
			});
			
			System.out.println("sort coList 2 : " + coList);
			
			jcoList = new JSONArray();
			//제이슨 배열로 변경
			for(int i=0; i<coList.size(); i++) {
				JSONArray jsubList = new JSONArray();
				for(int j=0; j<coList.get(i).size(); j++) {
					jsubList.add(new JsonReturn().returnPartyCo(coList.get(i).get(j)));
				}
				jcoList.add(jsubList);
			}
		}
		JSONObject pagingJason = new JsonReturn().returnPaging(paging);
		
		JSONObject sendJson = new JSONObject();
		sendJson.put("paging", pagingJason);
		sendJson.put("list", jcoList);
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
