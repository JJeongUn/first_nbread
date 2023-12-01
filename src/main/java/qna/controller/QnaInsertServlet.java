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

import member.model.service.MemberService;
import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaInsertServlet
 */
@WebServlet("/qinsert")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//mulipart 방식으로 전송 확인 or 에러 처리
	
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨.");
			view.forward(request, response);
		}
		
		//업로드 할 파일의 용량 제한 설정 : 10메가바이트로 제한
		int maxSize = 1024 * 1024 * 10;
		
		//업로드되는 파일의 저장 폴더를 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/qna_upfiles");
	
		//request 를 MultipartRequest 로 변환
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		//데이터베이스 qna 테이블에 기록할 값 추출
		Qna qna = new Qna();
	
		Member member = new Member();
		
		qna.setQaTitle(mrequest.getParameter("title"));
		qna.setMeNum(Integer.parseInt(mrequest.getParameter("writer")));	//보류
		qna.setQaCon(mrequest.getParameter("content"));
		
		member.setMeAka(mrequest.getParameter("writer"));
	
		
//		//6. 업로드된 원본 파일 이름 추출
//		String originlFileName = mrequest.getFilesystemName("upfile");
//		//보류
//		
//		//7. 폴더에 저장된 파일의 이름바꾸기 처리
//		if(originlFileName != null) {
//			
//			String renameFileName = FileNameChange.change(originlFileName, savePath, "yyyyMMddHHmmss");
//		
		
		// 모델 서비스 메소드로 전달하고 결과받기
		
		int result = new QnaService().insertQna(qna);
		
		//받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			System.out.println("들어옴");
			// 서블릿에서 서블릿을 실행 
			view = request.getRequestDispatcher("/qlist");
			request.setAttribute("page", 1);
			request.setAttribute("member", member);
			//request.setAttribute("qna",qna);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "새 qna 등록 실패.");
			
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
