package member.controller;

import static common.PhotoTemplate.*;

import java.util.Enumeration;
import java.util.UUID;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.service.MemberService;
import member.model.vo.Member;
import party.model.service.PartyService;
import photo.model.*;
import photo.model.service.PhotoService;

/**
 * Servlet implementation class UpdatePhotoservlet
 */
@WebServlet("/upphoto")
public class UpdatePhotoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePhotoservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프로필 사진 업로드 처리용 컨트롤러
		
		//1. 전송온 값에 한글이 있다면 인코딩 처리함
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
	        // 로그인한 회원이 없을 경우 처리
	        // 로그인 페이지로 리다이렉트
	        response.sendRedirect("/comi/login.jsp");
	        return;
	    }
		
		if (!ServletFileUpload.isMultipartContent(request)) {
	        System.out.println("에러! : enctype='multipart/form-data'");
	        return;
	    }
		
		// 2. 파일 업로드 설정
	    int maxSize = 1024 * 1024 * 10; // 최대 업로드 파일 크기 (10MB)

		if(loginMember.getMePhotoAdd() != null){
			updatePhoto(request.getSession().getServletContext().getRealPath("/resources/profile_photo_upfiles"),
    			String.valueOf(loginMember.getMeNum()));
		}

	    String savePath = savePathChange(request.getSession().getServletContext().getRealPath("/resources/profile_photo_upfiles"),
    		 String.valueOf(loginMember.getMeNum()));
	    //String savePath = request.getSession().getServletContext().getRealPath("/resources/profile_photo_upfiles");
	    
	    // 3. 파일 업로드 처리
	    MultipartRequest multi = new MultipartRequest(
	            request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
	    );
	    
	    Enumeration files = multi.getFileNames();
	    String profilePhotoFileName = null;

	    while (files.hasMoreElements()) {
	        String name = (String) files.nextElement();
	        profilePhotoFileName = multi.getFilesystemName(name);
	    }

	    // 4. 프로필 사진 업로드 결과 처리
	    if (profilePhotoFileName != null) {
	        // 파일 업로드 성공

	        // 여기에서 프로필 사진 파일명(profilePhotoFileName)을 DB에 저장하거나 다른 작업을 수행할 수 있습니다.
	        // 이 작업은 PhotoService나 MemberService를 사용하여 구현할 수 있습니다.

	        // 예시: PhotoService를 사용하여 DB에 프로필 사진 정보를 업데이트
	        PhotoService photoService = new PhotoService();
	        int result = photoService.updateProfilePhoto(loginMember, profilePhotoFileName);

	        if (result > 0) {
	            // 프로필 사진 업데이트 성공
	            //loginMember.setProfilePhoto(profilePhotoFileName); // 회원 객체에 업데이트된 프로필 사진 파일명 설정
	            response.sendRedirect("/comi/main.jsp");
	        } else {
	            // 업데이트 실패
	            RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
	            request.setAttribute("message", loginMember.getMeId() + "님 사진 수정 실패!");
	            view.forward(request, response);
	        }
	    } else {
	        // 파일 업로드 실패
	        RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
	        request.setAttribute("message", "프로필 사진 업로드 실패!");
	        view.forward(request, response);
	    }
	}
	    
//		Member member = new Member();
//		int meNum = updateProfilePhoto("menum");
//		
//		if (!ServletFileUpload.isMultipartContent(request)) {
//			
//			System.out.println("에러! : enctype='multipart/form-data'");
//		}
//
//		int maxSize = 1024 * 1024 * 10;
//		String savePath = savePathChange(request.getSession().getServletContext().getRealPath("/resources/profile_photo_upfiles"),
//				Integer.toString(meNum));
//		
//		//3. 모델 서비스 메소드로 값 또는 객체 전달 실행하고 결과받기
//		int result = new PhotoService().updateProfilePhoto(loginMember, savePath);
//		
//		//4. 받은 결과로 내보낼 뷰 선택 처리
//		if(result > 0) {
//			response.sendRedirect("/comi/main.jsp");
//		} else {			
//			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
//		
//			request.setAttribute("message", loginMember.getMeId() + "님 사진 수정 실패!");
//		
//			view.forward(request, response);
//		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
