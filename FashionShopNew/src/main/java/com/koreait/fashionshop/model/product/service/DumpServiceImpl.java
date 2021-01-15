package com.koreait.fashionshop.model.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.model.common.FileManager;
import com.koreait.fashionshop.model.domain.Color;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.excel.ProductConverter;
import com.koreait.fashionshop.model.product.repository.ColorDAO;
import com.koreait.fashionshop.model.product.repository.ProductDAO;
import com.koreait.fashionshop.model.product.repository.PsizeDAO;

@Service
public class DumpServiceImpl implements DumpService{
	private static final Logger logger=LoggerFactory.getLogger(DumpService.class);
	
	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ColorDAO colorDAO;
	
	@Autowired
	private PsizeDAO psizeDAO;
	
	@Override
	public void regist(String path) {
		//엑셀을 읽어서 데이터로 넣기
		List<Product> productList = productConverter.convertFromFile(path);
		logger.debug("엑셀파일을 분석하여 나온 결과 리스트 "+productList.size());
		
		for(int i=0;i<productList.size();i++) {
			Product product = productList.get(i);
			productDAO.insert(product);
			//따라서 이 라인부터는 product vo에 product_id가 채워져있다.
			//인서트 하자마자 이 시점부터는 product vo에 이미 pk값이 채워져 있는 상태이다..
			
			//색상넣기!!(하나의 상품에 딸려있는 여러개의 색상을 넣자, 그리기 위해서는 product_id가 필요함)
			for(Color color :product.getColorList()) {
				color.setProduct_id(product.getProduct_id());	
				//이 시점부터는 컬러가 fk를 보유했으므로, 색상테이블에데이터 넣어보자
				//데이터 넣어보자!!
				colorDAO.insert(color);
			}
			
			//사이즈 넣기
			for(Psize psize:product.getPsizeList()) {
				psize.setProduct_id(product.getProduct_id());
				psizeDAO.insert(psize);
			}
			
			//이미 들어간 파일명을 product_id +확장자 조합으로 교체 ex)35.jpg
			product.setFilename(product.getProduct_id()+"."+FileManager.getExtend(product.getFilename()));
			productDAO.update(product);
		}
	}
	
}
