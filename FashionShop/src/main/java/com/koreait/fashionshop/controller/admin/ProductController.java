package com.koreait.fashionshop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.common.FileManager;
import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.SubCategoryService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

//������ ��忡���� ��ǰ�� ���� ��û ó��
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
	
	//�츮�� �� servletcontext�� ����ϴ°�? getRealPath() ����Ϸ���!!
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//�� Ÿ�̹��� ��ġ�� ����, ���� ������ ��θ� FileManager�� �����س���.
		fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		logger.debug(fileManager.getSaveBasicDir());
		//logger.debug(fileManager.getSaveAddonDir());
	}
	
	
	
	//����ī�װ� ��������
	@RequestMapping(value="/admin/product/registform",method=RequestMethod.GET)
	public ModelAndView getTopList() {
		//3�ܰ� ����: ������ü�� �Ͻ�Ų��.
		List topList = topCategoryService.selectAll();
		
		//4�ܰ�: ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList",topList);
		mav.setViewName("admin/product/regist_form");
		return mav;
	}
	
	//����ī�װ� �������� 
	//������������ java��ü�� Json�� ��ȯ(converting)�� �ڵ����� ó�����ִ� ���̺귯���� �����Ѵ�.
	@RequestMapping(value="/admin/product/sublist", method=RequestMethod.GET)
	@ResponseBody
	public List getSubList(int topcategory_id) {
		List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
		return  subList;
	}
	
	
	/*
	   @RequestMapping(value="/admin/product/sublist", method=RequestMethod.GET, produces="application/json;charset=utf8")
	   @ResponseBody //jsp�� ���� ���������� �ƴ�, �ܼ� �����͸� ���۽�..
	   public String getSubList(int topcategory_id) {
	      logger.debug("topcategory_id : "+topcategory_id);
	      List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
	      
	      //����Ʈ�� json���� �����Ͽ� ���������..
	      StringBuilder sb = new StringBuilder();
	      sb.append("{");
	      sb.append("\"subList\":[");
	      for(int i = 0; i < subList.size(); i++) {
	         SubCategory subCategory = subList.get(i);
	         sb.append("{");
	         sb.append("\"subcategory_id\":"+subCategory.getSubcategory_id()+",");
	         sb.append("\"topcategory_id\":"+subCategory.getTopcategory_id()+",");
	         sb.append("\"name\":\""+subCategory.getName()+"\"");
	         if(i<subList.size()-1) {
	            sb.append("},");
	         }else {
	            sb.append("}");
	         }
	      }
	      sb.append("]");
	      sb.append("}");
	      
	      return sb.toString();
	   }
	 * */
	 

	//��ǰ���
	@RequestMapping(value="/admin/product/list", method=RequestMethod.GET )
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		List productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	
	//��ǰ ��� ��
	@RequestMapping(value="/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}
	
	//��ǰ��
	
	//��ǰ���
	@RequestMapping(value="/admin/product/regist", method=RequestMethod.POST, produces="text/html;charset=utf8")
	@ResponseBody		//������ ������ �ƴ϶� text�����̴�~
	public String registProduct(Product product,String[] test) {
		logger.debug("����ī�װ� "+product.getSubCategory().getSubcategory_id());
		logger.debug("��ǰ�� "+product.getProduct_name());
		logger.debug("���� "+product.getPrice());
		logger.debug("�귣�� "+product.getBrand());
		logger.debug("�󼼳��� "+product.getDetail());
		
		for(Psize psize : product.getPsize()) {
			logger.debug(psize.getFit());
		}
	
		//logger.debug("insert�ϱ� �� ��ǰ�� product_id "+product.getProduct_id());
		
		productService.regist(fileManager,product); 	//��ǰ ��� ���, ���񽺿��� ��û

		//logger.debug("��� insert�� ��ǰ�� product_id "+product.getProduct_id());	//���⼭ product.getProduct_id �� ��ǥ�̹��� ���ϸ��� �ɰž� ����
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"��ǰ��ϼ���\"");
		sb.append("}");
		
		return sb.toString();
		
	}


	
	//��ǰ ����
	
	//��ǰ ����
	
	
	//����ó��
	//���� �޼��� �߿��� �ϳ��� ���ܰ� �߻��ϸ�, �Ʒ��� �ڵ鷯�� ����
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
	
}
