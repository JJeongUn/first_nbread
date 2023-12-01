package party.controller;

import static common.PhotoTemplate.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.service.MemberService;
import member.model.vo.Member;
import party.model.service.PartyService;
import party.model.vo.Party;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;
import totalparty.model.service.TotalPartyService;
import totalparty.model.vo.TotalParty;

/**
 * Servlet implementation class partyMakeServlet
 */
@WebServlet("/partyi")
public class PartyInsertServlet extends HttpServlet {

	private static final long serialVersionUID = -1011000182097991224L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartyInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int photoNum = seqPhotoNum("photo");
		String inputFileName = null;
		int photoResult = -1;
		PartyService partyService = new PartyService();
		RequestDispatcher view = null;
		// RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 에러 페이지 급구!
			// view = request.getRequestDispatcher("views/common/error.jsp");
			// request.setAttribute("message", "form의 enctype='multipart/form-data' 속성
			// 누락됨.");
			// view.forward(request, response);
			System.out.println("에러! : enctype='multipart/form-data'");
		}

		int maxSize = 1024 * 1024 * 10;
		String savePath = savePathChange(request.getSession().getServletContext().getRealPath("/resources/partyfile"),
				Integer.toString(photoNum));

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Photo photo = new Photo();
		Party party = new Party();
		party.setPaTitle(mrequest.getParameter("pa_title"));
		party.setPaCon(mrequest.getParameter("pa_con"));
		party.setMeNum(Integer.parseInt(mrequest.getParameter("usernum")));
		// 회원가입 완료후 수정부분
		party.setPaLocation(mrequest.getParameter("address"));
		party.setPaTotalAmount(Integer.parseInt(mrequest.getParameter("pa_total_amount")));
		party.setPaDeposit(Integer.parseInt(mrequest.getParameter("pa_deposit")));
		party.setPaTotalNum(Integer.parseInt(mrequest.getParameter("pa_total_num")));
		party.setPaPerAmount((Integer.parseInt(mrequest.getParameter("pa_total_amount"))
				- Integer.parseInt(mrequest.getParameter("pa_deposit")))
				/ Integer.parseInt(mrequest.getParameter("pa_total_num")));
		party.setPaAct("Y");
		party.setPaTotalNum(Integer.parseInt(mrequest.getParameter("pa_total_num")));
		
		int categoryCheck = partyService.setCategory(mrequest.getParameter("category_check"));
		party.setCatNum(categoryCheck);
		//System.out.println(categoryCheck);
		String partyTime = mrequest.getParameter("pa_time_1");
		//+" "+ mrequest.getParameter("pa_time_2");
		ArrayList<Photo> photoList = searchPhotoList("party");
		//System.out.println(partyTime);
		party.setPaTime(Date.valueOf(partyTime));
		//System.out.println(categoryCheck);
		if ((inputFileName = mrequest.getFilesystemName("pa_img1")) != null) {
			party.setPhNum(photoNum);
			photo.setPhotonum(photoNum);
			photo.setPhoto1(inputFileName);
			inputFileName = mrequest.getFilesystemName("pa_img2");
			photo.setPhoto2(inputFileName);
			inputFileName = mrequest.getFilesystemName("pa_img3");
			photo.setPhoto3(inputFileName);
			inputFileName = mrequest.getFilesystemName("pa_img4");
			photo.setPhoto4(inputFileName);
			inputFileName = mrequest.getFilesystemName("pa_img5");
			photo.setPhoto5(inputFileName);
			photoResult = new PhotoService().insertPhoto(photo, "photo");
		}else{
			if(deletePhotoDir(savePath)){
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			photoResult = 1;
			photoNum = -1;
		}
		int result = partyService.insertParty(party);
		String panum = partyService.getNowPartyNum();
		
		int resultPartyTotal = new TotalPartyService().createTotalPartyNum(Integer.parseInt(panum),party.getMeNum());
		
		int totalCount = 0;
		if( resultPartyTotal > 0){
			totalCount = new TotalPartyService().getTotalCount(Integer.parseInt(panum));
		}
	
		ArrayList<TotalParty> totalpartylist = new TotalPartyService().selectTotalPartyList(Integer.parseInt(panum));
		Member member = new MemberService().selectMember(party.getMeNum());
		ArrayList<Party> list = partyService.selectPartyList("open", 1, 6, panum);
		System.out.println(party.getPaNum());
		if (result > 0 && photoResult > 0) {
			String url = "views/party/party_click.jsp";
			// 오픈 URL 주소
			// if(act.equals("N")) url = "views/party/party_click.jsp";

			view = request.getRequestDispatcher(url);
			//System.out.println("act : " + party.getPaAct());
			// 파티 활성화 여부
			request.setAttribute("type", (party.getPaAct().toUpperCase().equals("Y")) ? "findParty" : "findReview");
			// 파티 활성화 여부에 따라 보여줄 페이지 선택
			request.setAttribute("party", party);
			// 파티 정보
			request.setAttribute("category_check",mrequest.getParameter("category_check"));
			request.setAttribute("partyList", list);
			request.setAttribute("member", member);
			request.setAttribute("photo", photo);
			request.setAttribute("totalcount", totalCount);
			request.setAttribute("photolist", photoList);
			request.setAttribute("totalpartylist",totalpartylist);
			// 파티 글 쓴 회원 정보

		} else if (result > 0 && photoResult == 0) {
			// 사진 저장오류
			System.out.println("사진저장 실패");
			response.sendRedirect("/comi/partysall");
		} else {
			// 오류!
			System.out.println("저장 실패");
			System.out.println("result : " + result);
			System.out.println("photoResult : " + photoResult);
			response.sendRedirect("/comi/partysall");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
