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

/**
 * Servlet implementation class BusinessUpdateServlet
 */
@WebServlet("/bupdate")
public class BusinessUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 원글 수정 처리용 컨트롤러
		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 10;
		
		//업로드되는 파일의 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/business_upfiles");
		
		//request 를 MultipartRequest 로 변환해야 함
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
		new DefaultFileRenamePolicy());
		
		//데이터베이스 QA 테이블에 기록할 값 추출
		Business business = new Business();
		
		business.setBuTitle(mrequest.getParameter("title"));
		business.setBuCon(mrequest.getParameter("content"));
		business.setBuNum(Integer.parseInt(mrequest.getParameter("bnum")));
		
		int currentPage = Integer.parseInt(mrequest.getParameter("page"));
		
		// 모델 서비스 메소드로 전달하고 결과받기
		int result = new BusinessService().updateBusiness(business);
		
		if (result > 0) {
			response.sendRedirect("/comi/bdetail?bnum=" + business.getBuNum() + "&page=" + currentPage);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", business.getBuNum() + "번 원글 수정 실패.");

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
