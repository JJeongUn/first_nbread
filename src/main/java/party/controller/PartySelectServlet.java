package party.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import adver.model.vo.Adver;
import chat.model.service.ChatService;
import chat.model.vo.Chat;
import common.JsonReturn;
import common.Paging;
import member.model.service.MemberService;
import member.model.vo.Member;
import party.model.service.PartyService;
import party.model.vo.Party;
import partyCo.model.service.PartyCoService;
import partyCo.model.vo.PartyCo;
import photo.model.vo.Photo;
import totalparty.model.service.TotalPartyService;
import totalparty.model.vo.TotalParty;

import static common.PhotoTemplate.*;

/**
 * Servlet implementation class PartySelectAllServlet
 */
@WebServlet("/partysel")
public class PartySelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartySelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파티 클릭시 
		int panum = Integer.parseInt(request.getParameter("panum"));
		int totalCount = new TotalPartyService().getTotalCount(panum);
		RequestDispatcher view = null;
		String act = request.getParameter("act");//활성화 비활성화 여부
		String limitStr = request.getParameter("limit");//
		
		ArrayList<Chat> chatList = null;

		Party party = new PartyService().selectParty(panum);
		Member member = new MemberService().selectMember(party.getMeNum());
		Photo photo = searchPhoto(party,"party");
		PartyService pservice = new PartyService();
		Boolean hasChat = new ChatService().hasChat(panum); // 채팅이 있으면 true 없으면 false
		String type = (act.equals("Y")) ? "findParty" : "findReview";
		//맨처음 보여질 리스트들 6개만 보이니까 1~6
		ArrayList<Party> list = pservice.selectPartyList("open", 1, 6, Integer.toString(panum));
		System.out.println(hasChat);
		if(hasChat){
			chatList = new ChatService().selectChatList(panum);
			//System.out.println(chatList);
		}else{
			chatList = null;
		}
		ArrayList<TotalParty> totalPartyList = new TotalPartyService().selectTotalPartyList(panum);
		ArrayList<PartyCo> partyCoList = null;
		ArrayList<ArrayList<PartyCo>> coList = null;
		ArrayList<Photo> photoList = searchPhotoList("party");
		Paging paging = null;
		int listCountReply = 0;
		
		if(type == "findReview") {//후기 
			int currentPage = 1;//댓글의 카운트
			int limit = (limitStr == null) ? 10 : Integer.parseInt(limitStr);//한페이지당 목록 갯수
			
			//System.out.println("panum : " + panum);
			PartyCoService coservice = new PartyCoService();
			
			listCountReply = coservice.getListCount(panum);
			//System.out.println("==listCount : " + listCount);
			paging = new Paging(listCountReply, currentPage, limit);
			paging.calculator();
			//댓글 부분
			partyCoList = coservice.selectPartyCoList(panum, 1, limit);
			//System.out.println("==partyCoList : " + partyCoList);
			//System.out.println("==partyCoList size : " + partyCoList.size());
			//이중배열 만들어 보내기
			int count = 0;
			if(partyCoList.size() > 0) {
				
				coList = new ArrayList<ArrayList<PartyCo>>();
				ArrayList<PartyCo> cList = new ArrayList<PartyCo>();
				
				for(int i=0; i<partyCoList.size(); i++) {
					//System.out.println("i : " + i);
					int nextDepth = (i < partyCoList.size()-1) ? partyCoList.get(i+1).getComDepth() : -1;//다음번 뎁스
					
					cList.add(partyCoList.get(i));
					
					if(nextDepth == 1 || i == partyCoList.size()-1) {//마지막이거나 다음 뎁스가 1일때
						
						ArrayList<PartyCo> ctemp = (ArrayList<PartyCo>) cList.clone();
						coList.add(ctemp);
						cList.clear();
						count ++;
					}
				}
			}
			//System.out.println("coList : " + coList);
			
		}
		
		if(act != null) {
			String url = "views/party/party_click.jsp";
			//if(act.equals("N")) url = "views/party/party_click.jsp";
			
			view = request.getRequestDispatcher(url);
			//System.out.println("act : " + act);
			ArrayList<Adver> advertise = null;
			request.setAttribute("advertise", advertise);
			request.setAttribute("type", (act.equals("Y")) ? "findParty" : "findReview");
			request.setAttribute("party", party);
			request.setAttribute("partyList", list);
			request.setAttribute("member", member);
			request.setAttribute("photo",photo);
			request.setAttribute("totalcount",totalCount);
			request.setAttribute("photolist", photoList);
			request.setAttribute("totalpartylist",totalPartyList);
			request.setAttribute("chatlist",chatList);
			String categoryCheck = pservice.getCategory(party.getCatNum());
			request.setAttribute("category_check",categoryCheck);
			
			if(type == "findReview") {
				request.setAttribute("partyColistCount", listCountReply);
				request.setAttribute("partyCoPaging", paging);
				request.setAttribute("partyCoList", coList);
				//System.out.println(">>partyCoList : " + coList.size());
				//System.out.println(">>partyCoList : " + coList.get(0).get(0).getMeId());
				//System.out.println(">>partyColistCount : " + listCountReply);
			}
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "파티 리스트 불러오기 실패");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
