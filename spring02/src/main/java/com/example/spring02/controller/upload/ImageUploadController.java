package com.example.spring02.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {
	private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
	
	@RequestMapping("imageUpload.do")
	// MultipartFile upload : 파일이 저장되는 매개변수. 매개변수 명은 upload로 사용하여야 한다(ckeditor에서 고정)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) throws Exception {
		
		// response : 서버가 클라이언트에 응답. 인코딩 필요.
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//업로드한 파일 이름
		String fileName = upload.getOriginalFilename();
		//업로드한 파일을 byte배열로 변환
		byte[] bytes = upload.getBytes();

		//tomcat - 개발디렉토리/배포디렉토리
		//이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
		String uploadPath="E:\\workshop\\EGov\\TIL_Spring\\.metadata\\.plugins\\"
				+ "org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\"
				+ "WEB-INF\\views\\images\\"; // 배포디렉토리에 이미지 폴더 경로 추가.
		
		// 파일 다운로드 : 서버에서 클라이언트에 전송 (서버 --> 클라) = InputStream필요.
		// 파일 업로드 : 클라이언트가 서버에 작성(= 파일 기록, 클라-->서버) = OutputStream필요.
		OutputStream out = new FileOutputStream(new File(uploadPath + fileName)); // java.io.OutputStream;
		
		// 서버로 업로드
		out.write(bytes);
		
		// 업로드된것을 알림 = 클라이언트에 결과 표시. parameter 이름은 "CKEditorFuncNum"로 사용(ckeditor에서 고정)
		String callback = request.getParameter("CKEditorFuncNum");
		
		// 서버 --> 클라이언트 텍스트전송 : 업로드 결과를 서버가 클라이언트에 전송(자바스크립트 실행)
		// PrintWriter : writer를 상속. writer는 output stream.
		PrintWriter printWriter = response.getWriter();
		
		// 이미지 경로
		String fileUrl = request.getContextPath() + "/images/" + fileName;
		
		// 자바스크립트를 하드코딩하여 전송, 실행
		printWriter.println( "<script>window.parent.CKEDITOR.tools.callFunction("+callback+",'"+fileUrl
				+"','이미지가 업로드 되었습니다.')"+"</script>");
		printWriter.flush();
	}
}
