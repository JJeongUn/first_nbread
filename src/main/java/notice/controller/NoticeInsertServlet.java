package notice.controller;

import static common.PhotoTemplate.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.service.MemberService;
import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;


/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/noticeins")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int photoNum = seqPhotoNum("NOTICEPHOTO");
		// System.out.println("photoNum : " + photoNum);
		String inputFileName = null;
		int photoResult = -1;
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 에러 페이지 급구!
			// view = request.getRequestDispatcher("views/common/error.jsp");
			// request.setAttribute("message", "form의 enctype='multipart/form-data' 속성
			// 누락됨.");
			// view.forward(request, response);
			System.out.println("에러! : enctype='multipart/form-data'");
		}

		int maxSize = 1024 * 1024 * 10;
		String savePath = savePathChange(request.getSession().getServletContext().getRealPath("/resources/noticefile"),
				Integer.toString(photoNum));

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		Photo photo = new Photo();
		Notice notice = new Notice();
		Member member = null;
		notice.setNoTitle(mrequest.getParameter("title"));
		notice.setMeNum(Integer.parseInt(mrequest.getParameter("writer")));
		member = new MemberService().selectMember(notice.getMeNum());
		notice.setNoCon(mrequest.getParameter("content"));
		inputFileName = mrequest.getFilesystemName("inputfile1");
		if (inputFileName != null) {
			photo.setPhoto1(inputFileName);
			inputFileName = mrequest.getFilesystemName("inputfile2");
			photo.setPhoto2(inputFileName);
			inputFileName = mrequest.getFilesystemName("inputfile3");
			photo.setPhoto3(inputFileName);
			inputFileName = mrequest.getFilesystemName("inputfile4");
			photo.setPhoto4(inputFileName);
			inputFileName = mrequest.getFilesystemName("inputfile5");
			photo.setPhoto5(inputFileName);
			photo.setPhotonum(photoNum);
			notice.setNoPhotoNum(photoNum);
			photoResult = new PhotoService().insertPhoto(photo, "noticephoto");
		}else{
			if(deletePhotoDir(savePath)){
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			photoResult = 1;
			photoNum = -1;
		}
		
		int noticeResult = new NoticeService().insertNotice(notice, photoNum);

		if (noticeResult > 0 && photoResult > 0) {
			response.sendRedirect("/comi/noticesall");
		} else if (noticeResult > 0 && photoResult == 0) {
			// 사진 저장오류
			System.out.println("사진저장 실패 혹은 사진 없음");
			response.sendRedirect("/comi/noticesall");
		} else {
			// 오류!
			System.out.println("저장 실패");
			System.out.println("noticeResult : " + noticeResult);
			System.out.println("photoResult : " + photoResult);
			response.sendRedirect("/comi/noticesall");
		}
	}

	private Object noticeService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
