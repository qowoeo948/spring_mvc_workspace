package com.koreait.fashionshop.model.product.service;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.admin.controller.AdminProductController;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.excel.ProductConverter;

@Service
public class DumpServiceImpl implements DumpService{
	private static final Logger logger=LoggerFactory.getLogger(AdminProductController.class);
	
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public void regist(String path) {
		//엑셀을 읽어서 데이터로 넣기
		List<Product> productList = productConverter.convertFromFile(path);
		logger.debug("엑셀파일을 분석하여 나온 결과 리스트 "+productList.size());
		
	}

}
