package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import member.model.service.MemberService;
import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaListServlet
 */
@WebServlet("/qlist")
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 페이지별로 출력되는 게시글 목록 조회 요청처리용 컨트롤러
      
      //출력할 페이지 지정
      int currentPage = 1;
      //전송온 페이지 값이 있다면 추출함
      if(request.getParameter("page") != null) {
         currentPage = Integer.parseInt(request.getParameter("page"));
      }
      
      //한 페이지당 출력할 목록 개수 지정
      int limit = 10;
      //전송온 목록 갯수가 있다면 추출함
      if(request.getParameter("limit") != null) {
         limit = Integer.parseInt(request.getParameter("limit"));
      }
      System.out.println("qna list servlet");
      
      //조회용 모델측 서비스 객체 생성
      QnaService qservice = new QnaService();
      
      //총 페이지 수 계산을 위한 전체 목록 개수 조회
      int listCount = qservice.getListCount();
      
      //뷰 페이지에서 사용할 페이징 관련 값 계산처리
      Paging paging = new Paging(listCount, currentPage, limit, "qlist");
      paging.calculator();
      
      //모델 서비스로 해당 페이지를 출력할 게시글만 조해회 옴
      ArrayList<Qna> list = qservice.selectList(paging.getStartRow(), paging.getEndRow());
      ArrayList<Member> memberlist = new MemberService().selectMemberAll();
      
      
      //int menum = 14;
      
      //Member member = new MemberService().infoMember(menum); 
      
      //받은 결과에 따라 성공 or 실패 페이지 내보내기
      RequestDispatcher view = null;
      if(list.size() >= 0) {
         //view = request.getRequestDispatcher("views/qna/qnaListView.jsp");
         System.out.println("전송완료");
         view = request.getRequestDispatcher("views/qna/qna.jsp");
         request.setAttribute("list", list);
         request.setAttribute("paging", paging);
         request.setAttribute("memberlist",memberlist);
         request.setAttribute("currentPage", currentPage);
         //request.setAttribute("member",member );
      }else {
         view = request.getRequestDispatcher("views/common/error.jsp");
         
         request.setAttribute("message", currentPage + "qna 페이지에 대한 목록 조회 실패");
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
