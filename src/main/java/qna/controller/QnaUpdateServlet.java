package qna.controller;

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

import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/qnaup")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 원글 수정 처리용 컨트롤러
		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		int maxSize = 1024 * 1024 * 10;
		
		//업로드되는 파일의 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/qna_upfiles");
		
		//request 를 MultipartRequest 로 변환해야 함
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
		new DefaultFileRenamePolicy());
		
		//데이터베이스 QA 테이블에 기록할 값 추출
		Qna qna = new Qna();
		
		qna.setQaTitle(mrequest.getParameter("title"));
		qna.setQaCon(mrequest.getParameter("content"));
		qna.setQaNum(Integer.parseInt(mrequest.getParameter("qnum")));

		int currentPage = Integer.parseInt(mrequest.getParameter("page"));
		
		
		//이전 첨부파일에 대한 삭제여부 값 추출
//		String deleteFlag = mrequest.getParameter("deleteFlag");
		
		//이전 첨부파일의 파일명 추출
//		String originalFilePath = mrequest.getParameter("ofile");
//		String renameFilePath = mrequest.getParameter("rfile");
		
		//새로 업로드된 원본 파일이름 추출
//		String originalFileName = mrequest.getFilesystemName("upfile");
		
		// 모델 서비스 메소드로 전달하고 결과받기
		int result = new QnaService().updateQna(qna);
		
		if (result > 0) {
			// 서블릿에서 서블릿을 실행함
			response.sendRedirect("/comi/qdetail?qnum=" + qna.getQaNum() + "&page=" + currentPage);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", qna.getQaNum() + "번 게시 원글 수정 실패.");

			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
