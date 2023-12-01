package party.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import adver.model.service.AdverService;
import adver.model.vo.Adver;
import common.JsonReturn;
import common.Paging;
import party.model.service.PartyService;
import party.model.vo.Party;
import photo.model.vo.Photo;
import static common.PhotoTemplate.*;

/**
 * Servlet implementation class MenuLinkServlet
 */
@WebServlet("/partysall")
public class PartySelectAllServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartySelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */   
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");//정렬 current, interest
		String classfy = request.getParameter("classfy");//분류 -1, 1, 2, 3, 4, 5
		String limitStr = request.getParameter("limit");//정렬 current, interest
		String send = request.getParameter("send");//send타입 
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int limit = (limitStr == null) ? 11 : Integer.parseInt(limitStr);
		String sendType = (send == null) ? "servlet" : send;
		
		PartyService pservice = new PartyService();
		ArrayList<Photo> photoList = searchPhotoList("party");
		String act = (type.equals("findParty")) ? "Y" : "N";
		int listCount = pservice.getListCount(act);
		Paging paging = new Paging(listCount, currentPage, limit);
		paging.calculator();
		
		int start = paging.getStartRow(); 
		int end = paging.getEndRow();
		
		String selType = (type.equals("findParty")) ? "Y" : "N";
		ArrayList<Party> list = null;//활성화
		HashMap<String, String> map = null;
		System.out.println("--type : " + type + ", start : " + start + ", end : " + end
				+ " currentPage : " + currentPage + " listCount : " + listCount
				+ " sendType : " + sendType + " keyword : " + keyword
				+ " sort : " + sort + " classfy : " + classfy);
		
		if(keyword == null) list = pservice.selectPartyAll(selType, start, end);//활성화
		else {
			
			map = new HashMap<String, String>();
			map.put("selType", selType);
			map.put("sort", sort);
			map.put("classfy", classfy);
			map.put("keyword", keyword);
			map.put("start", String.valueOf(start));
			map.put("end", String.valueOf(end));
			list = pservice.searchParty(map);//활성화
		}
		
		System.out.println("list : " + list);
		
		RequestDispatcher view = null;
		
		sort = (sort == null) ? "current" : sort;
		classfy = (classfy == null) ? "-1" : classfy;
		
		ArrayList<Adver> advertise = 
				new AdverService().selectAllAdver();
		if(advertise != null && advertise.size() > 1) {
			advertise.remove(0);
		}
		
		if(sendType.equals("ajax")) {
			//ajax로 보내기
			JSONArray jarr = new JSONArray();
			JSONArray phjarr = new JSONArray();
			JSONArray adjarr = new JSONArray();
			
			for(Party party : list) {
				JSONObject json = new JsonReturn().returnParty(party);
				jarr.add(json);
			}
			
			for(Photo ph  : photoList) {
				JSONObject phjson = new JsonReturn().returnPhoto(ph);
				phjarr.add(phjson);
			}
			
			for(Adver ad  : advertise) {
				JSONObject adjson = new JsonReturn().returnAdvertise(ad);
				adjarr.add(adjson);
			}
			
			JSONObject sendJson = new JSONObject();
			sendJson.put("advertise", adjarr);
			sendJson.put("photo", phjarr);
			sendJson.put("list", jarr);
			sendJson.put("listCount", listCount);
			System.out.println("ajax : " + jarr);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(sendJson);
			out.flush();
			
		}else {
			//jsp로 연결
			view = request.getRequestDispatcher("views/party/party_view.jsp");
			request.setAttribute("advertise", advertise);
			request.setAttribute("keyword", keyword);
			request.setAttribute("sort", sort);
			request.setAttribute("limit", limit);
			request.setAttribute("listCount", listCount);
			request.setAttribute("classfy", Integer.parseInt(classfy));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("type", type);
			request.setAttribute("partyList", list);
			request.setAttribute("photolist", photoList);
			System.out.println("sort : " + sort + "  classfy : " + classfy);
			if(list != null && list.size() > 0) {
			}else {
				//view = request.getRequestDispatcher("views/common/error.jsp");
				//request.setAttribute("message", "파티 리스트 불러오기 실패");
			}
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
