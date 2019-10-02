package com.example.spring02.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl implements PdfService {

	@Inject
	CartService cartService;
	
	@Override
	public String createPdf() {
		String result = "";
		try {
			// pdf 문서 객체 생성 : pdf 내용을 채우는 객체
			Document document = new Document();
			//pdf 생성 객체 : outputStream의 경로가 c인경우 관리자 권한이 필요하므로,
			//				하위 디렉토리를 만들거나 다른 드라이브에 저장한다.
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("e:/sample.pdf"));
			//pdf 문서열기
			document.open();
			//한글이 지원되는 폰트 지정
			// : 한글은 기본적으로 폰트가 깨지므로 폰트 폴더에 들어가 경로와 폰트 파일이름을 확인하여 설정한다.
			// BaseFont.IDENTITY_H, BaseFont.EMBEDDED : options
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf",
														BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// 폰트 사이즈 지정
			Font font = new Font(baseFont, 12);
			// pdf에 table을 생성 : 4컬럼의 테이블
			PdfPTable table = new PdfPTable(4);
			// 출력할 내용
			Chunk chunk = new Chunk("장바구니", font);
			// 문단
			Paragraph ph = new Paragraph(chunk);
			ph.setAlignment(Element.ALIGN_CENTER); //가운데 정렬
			document.add(ph);
			
			document.add(Chunk.NEWLINE); //줄바꿈 처리
			document.add(Chunk.NEWLINE);
			//document.add(Chunk.NEXTPAGE); //다음페이지로
			//document.newPage(); //페이지 나누기
			
			// 셀 생성(<td>) : 테이블의 타이틀 행 생성
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font));
			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));

			// 가운데 정렬 : addCell전에 설정필요
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			// 테이블에 셀 추가
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			
			// 임의 사용자의 id를 매개값으로 지정, 장바구니 목록 리턴
			List<CartDTO> items = cartService.listCart("kim");
			
			for (int i=0; i<items.size(); i++) {
				CartDTO dto = items.get(i); // i번째 레코드를 dto에 저장
				
				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(), font));
				cellProductName.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cellProductName); // 테이블에 셀 추가

				// PdfPCell에는 String만 매개값이 될 수 있음 : Integer.toString() or + "" 사용하여 자료형 변경.
				// PdfPCell cellPrice = new PdfPCell(Integer.toString(new Phrase(dto.getPrice(), font)));
				PdfPCell cellPrice = new PdfPCell(new Phrase(dto.getPrice()+"", font));
				cellPrice.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cellPrice);
				
				PdfPCell cellAmount = new PdfPCell(new Phrase(dto.getAmount()+"", font));
				cellAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cellAmount);
				
				PdfPCell cellMoney = new PdfPCell(new Phrase(dto.getMoney()+"", font));
				cellMoney.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cellMoney);
			}
			
			// document에 테이블 추가
			document.add(table);
			// pdf 파일이 생성됨
			document.close();
			
			result = "pdf파일이 생성되었습니다.";
		} catch (Exception e) {
			result = "pdf파일 생성실패.";
			e.printStackTrace();
		}
		return result;
	}

}
