package com.koreait.fashionshop.client.controller.product;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.exception.UploadFailException;
import com.koreait.fashionshop.model.common.FileManager;
import com.koreait.fashionshop.model.common.MessageData;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.SubCategoryService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

//愿�由ъ옄 紐⑤뱶�뿉�꽌�쓽 �긽�뭹�뿉 ���븳 �슂泥� 泥섎━
@Controller
public class ProductController implements ServletContextAware{
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileManager fileManager;
	
	//�슦由ш� �솢 ServletContext瑜� �뜥�빞�븯�뒗媛�?   getRealPath() �궗�슜�븯�젮怨�!!!
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//�씠 ���씠諛띿쓣 �넃移섏�留먭퀬, �떎�젣 臾쇰━�쟻 寃쎈줈瑜� FileManager �뿉 ���엯�빐�넃�옄!!!
		//fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		//fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		fileManager.setSaveBasicDir(fileManager.getSaveBasicDir());
		fileManager.setSaveAddonDir(fileManager.getSaveAddonDir());
		
		logger.debug(fileManager.getSaveBasicDir());
		
	}
	
	//�긽�뭹 �닔�젙
	
	//�긽�뭹 �궘�젣

	
	
	
	/* *********************************************************************** 
	  �눥�븨紐� �봽濡좏듃 �슂泥� 泥섎━ 
	 ************************************************************************/
	//�긽�뭹紐⑸줉 �슂泥� 泥섎━
	@RequestMapping(value="/product/list", method=RequestMethod.GET)
	public ModelAndView getShopProductList(HttpServletRequest request, int subcategory_id) {//�븯�쐞移댄뀒怨좊━�쓽 id
		List productList = productService.selectById(subcategory_id);//�긽�뭹紐⑸줉
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("productList", productList);
		mav.setViewName("shop/product/list");
		return mav;
	}
		
	//�긽�뭹�긽�꽭 蹂닿린 �슂泥� 
	@RequestMapping(value="/product/detail", method=RequestMethod.GET)
	public ModelAndView getShopProductDetail(HttpServletRequest request, int product_id) {
		Product product = productService.select(product_id);//�긽�뭹 1嫄� 媛��졇�삤湲�
		ModelAndView mav = new ModelAndView("shop/product/detail");
		mav.addObject("product", product);
		
		return mav;
	}
	
	//�삁�쇅泥섎━ 
	//�쐞�쓽 硫붿꽌�뱶 以묒뿉�꽌 �븯�굹�씪�룄 �삁�쇅媛� 諛쒖깮�븯硫�, �븘�옒�쓽 �빖�뱾�윭媛� �룞�옉
	@ExceptionHandler(ProductRegistException.class)
	@ResponseBody
	public String handleException(ProductRegistException e) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("\"msg\":\""+e.getMessage()+"\"");
		sb.append("}");
		return sb.toString();
	}
	
	@ExceptionHandler(UploadFailException.class)
	@ResponseBody
	public String handleException(UploadFailException e) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("\"msg\":\""+e.getMessage()+"\"");
		sb.append("}");
		return sb.toString();
	}
	
	
}

