package com.koreait.fashionshop.model.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.koreait.fashionshop.model.domain.Color;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.domain.SubCategory;

/*
 * 엑셀을 읽여들여, 자바의 POJO 형태로 변환하는 용도
 * */
@Component  //scan의 대상이 됨
public class ProductConverter {
	
	//누군가 이 메서드를 호출하는 자는, 자신이 보유한 스트림 주소를 넘기면 됨..
	public List convertFromFile(String path) {
		List<Product> productList = new ArrayList<Product>();
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(path);
			
			//엑셀파일 제어 객체 생성 
			XSSFWorkbook book=new XSSFWorkbook(fis);
			
			//파일을 접근했으니, 쉬트에 접근해보자
			XSSFSheet sheet=book.getSheetAt(0); //첫번째 쉬트에 접근
			
			//이중 반복문의 횟수를 구하기 
			int rowCount=sheet.getPhysicalNumberOfRows();
			
			for(int i=1;i<rowCount;i++) {
				Product product = new Product(); //텅빈 VO 준비하기..(채워넣기 위함)
				//컬럼수만큼 반복문 처리
				XSSFRow row = sheet.getRow(i); //열 하나 얻기
				
				for(int a=0;a<row.getPhysicalNumberOfCells();a++) {
					XSSFCell cell = row.getCell(a); //셀하나에 접근
					
					if(a==0) {//subcategory_id
						SubCategory subCategory = new SubCategory();
						subCategory.setSubcategory_id((int)cell.getNumericCellValue());
						product.setSubCategory(subCategory);
					}else if(a==1) {//product_name
						product.setProduct_name(cell.getStringCellValue());
					}else if(a==2) {
						product.setPrice((int)cell.getNumericCellValue());
					}else if(a==3) {
						product.setBrand(cell.getStringCellValue());
					}else if(a==4){//색상 
						String[] colors=cell.getStringCellValue().trim().split(","); //점을 기준으로 나누면, 스트링 배열이반환!!
						List colorList = new ArrayList();
						for(String color :colors) {
							Color obj = new Color();
							obj.setPicker(color);  //하나의 색상 vo에 색상정보를 대입
							colorList.add(obj);
						}
						product.setColorList(colorList);
					}else if(a==5) {
						String[] psize=cell.getStringCellValue().trim().split(","); //점을 기준으로 나누면, 스트링 배열이반환!!
						List psizeList = new ArrayList();
						for(String size :psize) {
							Psize obj = new Psize();
							obj.setFit(size);  //하나의 색상 vo에 색상정보를 대입
							psizeList.add(obj);
						}
						product.setPsizeList(psizeList);
					}else if(a==6) {
						product.setDetail(cell.getStringCellValue());
					}else if(a==7) {
						product.setFilename(cell.getStringCellValue());	//엑셀에 들어있는 원본파일명
					}
				}
				//완성된 상품을 리스트에 담자 
				productList.add(product);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return productList;
	}
}
