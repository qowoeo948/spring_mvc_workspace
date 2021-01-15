package com.koreait.fashionshop.model.product.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.exception.UploadFailException;
import com.koreait.fashionshop.model.common.FileManager;
import com.koreait.fashionshop.model.domain.Color;
import com.koreait.fashionshop.model.domain.Image;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.product.repository.ColorDAO;
import com.koreait.fashionshop.model.product.repository.ImageDAO;
import com.koreait.fashionshop.model.product.repository.ProductDAO;
import com.koreait.fashionshop.model.product.repository.PsizeDAO;

@Service
public class ProductServiceImpl implements ProductService{
	private static final Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ImageDAO imageDAO;
	
	@Autowired
	private PsizeDAO psizeDAO;
	
	@Autowired
	private ColorDAO colorDAO;
	
	@Override
	public List selectAll() {
		return productDAO.selectAll();
	}

	@Override
	public List selectById(int subcategory_id) {
		return productDAO.selectById(subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}

	@Override
	public void regist(FileManager fileManager, Product product) throws UploadFailException, ProductRegistException{
		
		String ext=fileManager.getExtend(product.getRepImg().getOriginalFilename());
		product.setFilename(ext); //�솗�옣�옄 寃곗젙
		//db�뿉 �꽔�뒗 �씪�� DAO�뿉寃� �떆�궎怨�
		productDAO.insert(product);
		
		//�뙆�씪 �뾽濡쒕뱶!!�뒗 FileManager�뿉寃� �떆�궓�떎
		//���몴�씠誘몄� �뾽濡쒕뱶 
		String basicImg = product.getProduct_id()+"."+ext;
		fileManager.saveFile(fileManager.getSaveBasicDir()+File.separator+basicImg, product.getRepImg());
		
		//異붽��씠誘몄� �뾽濡쒕뱶 (FileManager�뿉寃� 異붽��씠誘몄� 媛��닔留뚰겮 �뾽濡쒕뱶 �뾽臾대�� �떆�궎硫� �맂�떎!!)
		for(int i=0;i<product.getAddImg().length;i++) {
			
			MultipartFile file = product.getAddImg()[i];
			ext = fileManager.getExtend(file.getOriginalFilename());
			
			//image table�뿉 �꽔湲�!!
			Image image = new Image();
			image.setProduct_id(product.getProduct_id()); //fk
			image.setFilename(ext); //�솗�옣�옄 �꽔湲�
			imageDAO.insert(image);
			
			//硫붾땲�졇�쓽 ���옣 硫붿꽌�뱶 �샇異�
			String addImg = image.getImage_id()+"."+ext;
			fileManager.saveFile(fileManager.getSaveAddonDir()+File.separator+addImg, file);
		}
		
		//湲고� �샃�뀡 以�, �깋�긽 �궗�씠利� �꽔湲� (諛섎났臾몄쑝濡�...)
		
		//�궗�씠利�
		for(Psize psize : product.getPsize()) {
			//logger.debug("�떦�떊�씠 �꽑�깮�븳 �궗�씠利덈뒗 "+psize.getFit());
			psize.setProduct_id(product.getProduct_id());//fk ���엯
			psizeDAO.insert(psize);
		}
		
		//�깋�긽 
		for(Color color : product.getColor()){
			logger.debug("�꽆寃⑤컺�� �깋�긽�� "+color.getPicker());
			color.setProduct_id(product.getProduct_id());
			colorDAO.insert(color);
		}
		
	}

	@Override
	public void update(Product product) {
		
	}

	@Override
	public void delete(int product_id) {
		// TODO Auto-generated method stub
		
	}

}
