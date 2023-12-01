package adver.controller;

import static common.PhotoTemplate.*;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import adver.model.service.AdverService;
import adver.model.vo.Adver;
import notice.model.service.NoticeService;
import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class InsertAdverServlet
 */
@WebServlet("/inadver")
public class InsertAdverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertAdverServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int photoNum = seqPhotoNum("ADPHOTO");
		String inputFileName = null;
		int photoResult = -1;
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("에러! : enctype='multipart/form-data'");
		}

		int maxSize = 1024 * 1024 * 10;
		String savePath = savePathChange(request.getSession().getServletContext().getRealPath("/resources/adverfile"),
				Integer.toString(photoNum));
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		Photo photo = new Photo();
		Adver adver = new Adver();
		adver.setAdTitle(mrequest.getParameter("title"));
		adver.setMeNum(Integer.parseInt(mrequest.getParameter("writer")));
		adver.setAdUrl(mrequest.getParameter("url"));
		adver.setAdStaDate(Date.valueOf(mrequest.getParameter("startDate")));
		adver.setAdEndDate(Date.valueOf(mrequest.getParameter("endDate")));
		inputFileName = mrequest.getFilesystemName("file1");
		if(inputFileName != null){
			photo.setPhoto1(inputFileName);
			inputFileName = mrequest.getFilesystemName("file2");
			photo.setPhoto2(inputFileName);
			inputFileName = mrequest.getFilesystemName("file3");
			photo.setPhoto3(inputFileName);
			inputFileName = mrequest.getFilesystemName("file4");
			photo.setPhoto4(inputFileName);
			inputFileName = mrequest.getFilesystemName("file5");
			photo.setPhoto5(inputFileName);
			photo.setPhotonum(photoNum);
			adver.setAdPhNum(photoNum);
			photoResult = new PhotoService().insertPhoto(photo, "adphoto");
		}else{
			if(deletePhotoDir(savePath)){
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			photoResult = 1;
			photoNum = -1;
		}


		int noticeResult = new AdverService().insertAdver(adver, photoNum);

		if (noticeResult > 0 && photoResult > 0) {
			response.sendRedirect("/comi/adsall");
		} else if (noticeResult > 0 && photoResult == 0) {
			// 사진 저장오류
			System.out.println("사진저장 실패 혹은 사진 없음");
			response.sendRedirect("/comi/adsall");
		} else {
			// 오류!
			System.out.println("저장 실패");
			System.out.println("noticeResult : " + noticeResult);
			System.out.println("photoResult : " + photoResult);
			response.sendRedirect("/comi/adsall");
		}

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
