package com.koreait.fashionshop.controller.product;

import java.io.File;
import java.io.IOException;
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
	
	
	//����ī�װ� ��������		(�����ڿ�)
	@RequestMapping(value="/admin/product/registform",method=RequestMethod.GET)
	public ModelAndView getTopList(HttpServletRequest request) {
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
	public List getSubList(int topcategory_id,HttpServletRequest request) {
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
	
	//���� �뷮 �����
	@GetMapping("/admin/product/excel/registform")
	public String getExcelForm(HttpServletRequest request) {
		
		return "admin/product/excel_form";
	}
	
	//������ ���� ��ǰ��� ��û ó��
	@RequestMapping(value="admin/product/excel/regist",method=RequestMethod.POST,produces="text/html;charset=utf8")
	@ResponseBody		//�񵿱� �̹Ƿ�
	public String registByExcel(MultipartFile dump,HttpServletRequest request) {
		String path = fileManager.getSaveBasicDir()+File.separator+dump.getOriginalFilename();	//������ ���ϸ�
		fileManager.saveFile(path, dump);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"��ǰ��ϼ���\"");
		sb.append("}");
		return sb.toString();
	}
	 

	//��ǰ���
	@RequestMapping(value="/admin/product/list", method=RequestMethod.GET )
	public ModelAndView getProductList(HttpServletRequest request) {
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
	public String registProduct(Product product,String[] test,HttpServletRequest request) {
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
	
	
	
	
	
	//**********************************************************************************************//
	//���θ� ����Ʈ ��û ó�� 
	//**********************************************************************************************//
	
	//��ǰ��� ��û ó��
	@RequestMapping(value="/shop/product/list",method=RequestMethod.GET)
	public ModelAndView getShopProductList(int subcategory_id,HttpServletRequest request) {		//����ī�װ��� id�� ����;ߵ�
		//��ǰ���
		List productList = productService.selectById(subcategory_id);	

		ModelAndView mav = new ModelAndView();
		mav.addObject("productList",productList);
		mav.setViewName("shop/product/list");
		return mav;
	}
	
	//��ǰ �󼼺��� ��û
	@RequestMapping(value="/shop/product/detail",method=RequestMethod.GET)
	public ModelAndView getShopProductDetail(int product_id,HttpServletRequest request) {
		
		//��ǰ 1�� ��������
		Product product = productService.select(product_id);
		
		ModelAndView mav = new ModelAndView("shop/product/detail");
		mav.addObject("product",product);
		
		return mav;
	}
	

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
