package com.example.spring02.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring02.service.board.BoardService;
import com.example.spring02.util.MediaUtils;
import com.example.spring02.util.UploadFileUtils;

//컨트롤러 bean 설정
@Controller
public class AjaxUploadController {
	//로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	@Inject
	BoardService boardService;
	
	//업로드 디렉토리 : servlet-context.xml에 설정되어 있음
	//설정파일에서 값을 읽어와 변수에 대입하므로, 결과적으로 String uploadPath="e:/upload";와 동일.
	@Resource(name="uploadPath")
	String uploadPath;
	
	//파일첨부 페이지로 이동
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
	public String uploadAjax() {
		return "/upload/uploadAjax";
	}

	// - @ResponseBody : 서버 --> 클라이언트(뷰가 아닌 데이터 리턴)
	// - @RequestBody : 클라이언트 --> 서버(json 형식으로 전달)
	@ResponseBody // json형식으로 리턴
	//동일 url이지만 요청방식에 따라 구분되어 선택됨  --> 중복 url가능
	// produces : 한글폰트 인식을 위해 utf-8로 설정
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	// 업로드한 파일은 MultipartFile변수에 저장됨
	// ResponseEntity : 메시지 + http상태 코드
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		// 업로드한 파일 정보와 http상태 코드를 함께 리턴
		return new ResponseEntity<String>(
			// util패키지의 UploadFileUtils 클래스(정적 클래스)
			// uploadFile(파일경로,파일이름,파일내용) : getBytes() = 바이트 배열 = 파일내용
			UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK
		);
		// 결과적으로 리턴값은
		// new ResponseEntity<String>("파일이름", HttpStatus.OK)가 됨
	}
	
	//이미지 표시기능
	@ResponseBody // view가 아닌 data리턴
	@RequestMapping("/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		// 서버의 파일을 다운로드 하기위한 스트림
		InputStream in = null; //java.io
		ResponseEntity<byte[]> entity = null;
		try {
			//확장자 검사
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			//헤더 구성 객체
			HttpHeaders headers = new HttpHeaders();
			//InputStream 생성
			in = new FileInputStream(uploadPath + fileName);
			if(mType != null) { //이미지 파일이면
				headers.setContentType(mType);
			} else { // 이미지가 아니면
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				
				//다운로드용 컨텐트 타입. APPLICATION_OCTET_STREAM : 범용적인 컨텐츠 타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				
				// 첨부파일 링크를 추가 + 한글이 깨지지 않도록 설정
				// 큰 따옴표 내부에 " \" "
				// 바이트 배열을 스트링으로
				// is-8859-1 서유럽언어
				// new String(fileName.getBytes("utf-8"), "iso-8859-1")
				headers.add("Content-Disposition", "attachment; fileName=\"" 
									+ new String(fileName.getBytes("utf-8"), "iso-8859-1") +"\"");
				//headers.add("Content-Disposition", "attachment; fileName='"+fileName+"'");
			}
			//바이트 배열, 헤더 (파일내용, header, 상태코드)
			entity =  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null) in.close(); //스트림 닫기
		}
		return entity;
	}

	// 이미지파일 --> 썸네일 삭제, 원본이미지 삭제
	// 이미지파일이 아닌경우 --> 원본파일 삭제
	@ResponseBody // 뷰가 아닌 데이터를 리턴
	@RequestMapping(value="/upload/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName){
		logger.info("fileName: " + fileName);
		// 확장자 검사 - 확장자 문자열 추출
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); // .다음부터 문자열 절삭
		// static class에서 해당 값을 가져옴 - 해당하는 값이 없는 경우 null이 저장되어 있음.
		MediaType mType = MediaUtils.getMediaType(formatName);
		if(mType != null) { // 이미지 파일이면 원본이미지 삭제
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			// File.separatorChar : 유닉스 / 윈도우즈 \
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		// 원본파일 삭제(이미지이면 썸네일 삭제), replace("oldchar", "newchar")
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		//레코드 삭제
		boardService.deleteFile(fileName);
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
}
