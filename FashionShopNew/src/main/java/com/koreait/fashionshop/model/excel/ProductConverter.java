package com.koreait.fashionshop.model.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.SubCategory;

/*엑셀을 읽어들여, 자바의 POJO형태로 변환하는 용도*/
@Component		//scan의 대상이 됨
public class ProductConverter {
	
	//누군가 이 메서드를 호출하는 자는 , 자신이 보유한 스트림 주소를 넘기면됨.
	public List convertFromFile(String path) {
		List<Product> productList = new ArrayList<Product>();
		FileInputStream fis = null;
		
		//엑셀 파일 제어 객체 생성
		try {
			fis = new FileInputStream(path);
			HSSFWorkbook book = new HSSFWorkbook(fis);
			
			//파일을 접근했으니, 쉬트에 접근하자
			HSSFSheet sheet = book.getSheetAt(0);	//첫번째접근
			
			//이중 반복문의 횟수를 구하기
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columCount;
			for(int i=1;i<rowCount;i++) {
				Product product = new Product();	//텅빈 vo준비하기
				//컬럼수많큼 반복문 처리
				HSSFRow row = sheet.getRow(i);	//열 하나 얻기
				
				for(int a=0;a<row.getPhysicalNumberOfCells();a++) {
					HSSFCell cell = row.getCell(a);	//셀 하나에 접근
					if(a==0) {	//subcategory_id
						SubCategory subCategory = new SubCategory();
						subCategory.setSubcategory_id((int)cell.getNumericCellValue());
						product.setSubCategory(subCategory);
					}else if(a==1) {	//product_name
						product.setProduct_name(cell.getStringCellValue());
					}else if(a==2) {	//price
						product.setPrice((int)cell.getNumericCellValue());
					}else if(a==3) {	//brand
						product.setBrand(cell.getStringCellValue());
					}else if(a==6) {
						product.setDetail(cell.getStringCellValue());
					}
				}
				//완성된 상품을 List에 담자
				productList.add(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return productList;
	}
}
