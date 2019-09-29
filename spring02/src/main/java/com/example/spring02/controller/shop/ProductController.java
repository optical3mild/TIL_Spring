package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller // controller bean 으로 등록
@RequestMapping("/shop/product/*") // 공통된 url패턴
public class ProductController {
	
	@Inject // 서비스 객체 주입
	ProductService productService;
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		// 뷰의 경로
		mav.setViewName("/shop/product_list");
		// 뷰에 전달할 데이터 저장.
		mav.addObject("list", productService.listProduct());
		// product_list.jsp로 데이터와 함께 포워딩.
		return mav;
	}
	
	// detail/상품번호
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav) {
		// 뷰의 이름
		mav.setViewName("/shop/product_detail");
		// 뷰에 전달할 데이터 저장.
		mav.addObject("dto", productService.detailProduct(product_id));
		// product_detail.jsp로 포워딩됨.
		return mav;
	}
	
	@RequestMapping("write.do")
	public String write() {
		// views/shop/product_write.jsp로 이동
		return "shop/product_write";
	}
	
	//** form의 속성 : method="post" enctype="multipart/form-data"
	//		> enctype="multipart/form-data" 
	//			- 파일을 잘게 쪼개어 부분부분 전송. 
	//			- 컨트롤러의 메소드가 실행될 때는 이미 파일이 임시디렉토리에 저장되어 있다.
	@RequestMapping("insert.do")
	// @ModelAttribute : 생략가능
	public String insert(@ModelAttribute ProductDTO dto) {
		// 파일이름이 공백일 경우 에러발생 --> 공백대신 -을 넣음.
		String filename = "-";
		// 첨부파일이 있으면
		if(!dto.getFile1().isEmpty()) {
			// 첨부파일의 이름을 가져옴.
			filename=dto.getFile1().getOriginalFilename();
			try {
				//업로드할 디렉토리 : views의 images의 개발 디렉토리로 설정.
				//String path = "E:\\workshop\\EGov\\TIL_Spring\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 
				//업로드할 디렉토리 : views의 images의 배포 디렉토리로 설정.
				String path = "E:\\workshop\\EGov\\TIL_Spring\\.metadata\\.plugins\\org.eclipse.wst.server.core\\"
						+ "tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
				// 위에서 지정한 디렉토리가 없으면 생성
				new File(path).mkdirs();
				// 임시 디렉토리에 저장되어 있는 파일을 원하는 디렉토리로 옮김.
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);
		productService.insertProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	// /edit/32 --> 상품코드(32)가 PathVariable에 저장됨. {}로 변수로 설정.
	@RequestMapping("edit/{product_id}")
	// @PathVariable("product_id") : {}안의 변수이름과 동일하게 ("")안을 설정. 매개변수 이름과 동일하다면 ()생략가능
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		mav.setViewName("shop/product_edit");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
		// 파일이름이 공백일 경우 에러발생 --> 공백대신 -을 넣음.
		String filename = "-";
		if(!dto.getFile1().isEmpty()) { // 새로운 첨부파일이 있으면
			// 첨부파일의 이름을 가져옴.
			filename = dto.getFile1().getOriginalFilename();
			try {
				//업로드할 디렉토리 : views의 images의 개발 디렉토리로 설정.
				//String path = "E:\\workshop\\EGov\\TIL_Spring\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 
				//업로드할 디렉토리 : views의 images의 배포 디렉토리로 설정.
				String path = "E:\\workshop\\EGov\\TIL_Spring\\.metadata\\.plugins\\org.eclipse.wst.server.core\\"
						+ "tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\"; 
				// 위에서 지정한 디렉토리가 없으면 생성
				new File(path).mkdirs();
				// 임시 디렉토리에 저장되어 있는 파일을 원하는 디렉토리로 옮김.
				System.out.println(path+filename);
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		} else { // 새로운 첨부파일 없을 때 : 기존에 첨부한 파일 정보를 가져옴.
			// 화면에서 가져온 dto정보를 가져옴
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id());
			// 복사한 dto정보에서 첨부파일 정보를 읽어 dto에 다시 저장.  
			dto.setPicture_url(dto2.getPicture_url());
		}
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";
	}

	@RequestMapping("delete.do")
	// @RequestParam 생략
	//public String delete(@RequestParam int product_id) {
	public String delete(int product_id) {
		// 첨부파일 삭제
		String filename = productService.fileInfo(product_id);
		// null값을 방지(null point exception방지), file이 등록되지 않았을 경우 -으로 등록된것을 확인
		if(filename != null && !filename.equals("-")) {
			//상품이미지 디렉토리 : views의 images의 개발 디렉토리로 설정.
			//String path = "E:\\workshop\\EGov\\TIL_Spring\\spring02\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 
			//상품이미지 디렉토리 : views의 images의 배포 디렉토리로 설정.
			String path = "E:\\workshop\\EGov\\TIL_Spring\\.metadata\\.plugins\\org.eclipse.wst.server.core\\"
					+ "tmp0\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\"; 
			// 파일 참조
			File f =  new File(path+filename);
			if(f.exists()) { // 파일이 존재하면
				f.delete(); // 파일삭제
			}
		}
		// 레코드 삭제
		productService.deleteProduct(product_id);
		// 화면이동
		return "redirect:/shop/product/list.do";
	}
}
