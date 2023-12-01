package business.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.model.service.BusinessService;
import business.model.vo.Business;
import common.Paging;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class BusinessListServlet
 */
@WebServlet("/blist")
public class BusinessListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      request.setCharacterEncoding("utf-8");
      
      int currentPage = 1;
      
      if(request.getParameter("page") != null) {
         currentPage = Integer.parseInt(request.getParameter("page"));
      }
      
      int limit = 10;
      
      if(request.getParameter("limit") != null) {
         limit = Integer.parseInt(request.getParameter("limit"));
      }
      System.out.println("business list servlet");
      
      BusinessService bservice = new BusinessService();
      
      int listCount = bservice.getListCount();
      
      Paging paging = new Paging(listCount, currentPage, limit, "blist");
      paging.calculator();
      
      ArrayList<Business> list = bservice.selectList(paging.getStartRow(), paging.getEndRow());
      ArrayList<Member> memberlist = new MemberService().selectMemberAll();
      RequestDispatcher view = null;
      
      System.out.println(list.size());
      if(list.size() >= 0) {
         System.out.println("전송완료");
         view = request.getRequestDispatcher("views/business/business.jsp");
         request.setAttribute("list", list);
         request.setAttribute("paging", paging);
         request.setAttribute("currentPage", currentPage);
         request.setAttribute("memberlist",memberlist);
      }else {
         view = request.getRequestDispatcher("views/common/error.jsp");
         
         request.setAttribute("message", currentPage + "사업제휴 페이지에 대한 목록 조회 실패");
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