package business.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import business.model.service.BusinessService;
import business.model.vo.Business;
import member.model.vo.Member;

/**
 * Servlet implementation class BusinessInsertServlet
 */
@WebServlet("/binsert")
public class BusinessInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 10;
		
		//업로드되는 파일의 저장 폴더를 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/business_upfiles");
		
		//request 를 MultipartRequest 로 변환
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		//데이터베이스 business 테이블에 기록할 값 추출
		Business business = new Business();
		Member member = new Member();
		
		business.setBuTitle(mrequest.getParameter("title"));
		business.setMeNum(Integer.parseInt(mrequest.getParameter("writer")));
		business.setBuCon(mrequest.getParameter("content"));
		
		member.setMeAka(mrequest.getParameter("writer"));
		
		// 모델 서비스 메소드로 전달하고 결과받기
		int result = new BusinessService().insertBusiness(business);
		System.out.println(result);
		//받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			System.out.println("들어옴");
			// 서블릿에서 서블릿을 실행 
			view = request.getRequestDispatcher("/blist");
			request.setAttribute("page", 1);
			request.setAttribute("member", member);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "새 사업제휴 등록 실패.");
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
