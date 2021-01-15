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
		//������ �о �����ͷ� �ֱ�
		List<Product> productList = productConverter.convertFromFile(path);
		logger.debug("���������� �м��Ͽ� ���� ��� ����Ʈ "+productList.size());
		
		for(int i=0;i<productList.size();i++) {
			Product product = productList.get(i);
			productDAO.insert(product);
			//���� �� ���κ��ʹ� product vo�� product_id�� ä�����ִ�.
			//�μ�Ʈ ���ڸ��� �� �������ʹ� product vo�� �̹� pk���� ä���� �ִ� �����̴�..
			
			//����ֱ�!!(�ϳ��� ��ǰ�� �����ִ� �������� ������ ����, �׸��� ���ؼ��� product_id�� �ʿ���)
			for(Color color :product.getColorList()) {
				color.setProduct_id(product.getProduct_id());	
				//�� �������ʹ� �÷��� fk�� ���������Ƿ�, �������̺������� �־��
				//������ �־��!!
				colorDAO.insert(color);
			}
			
			//������ �ֱ�
			for(Psize psize:product.getPsizeList()) {
				psize.setProduct_id(product.getProduct_id());
				psizeDAO.insert(psize);
			}
			
			//�̹� �� ���ϸ��� product_id +Ȯ���� �������� ��ü ex)35.jpg
			product.setFilename(product.getProduct_id()+"."+FileManager.getExtend(product.getFilename()));
			productDAO.update(product);
		}
	}
	
}
