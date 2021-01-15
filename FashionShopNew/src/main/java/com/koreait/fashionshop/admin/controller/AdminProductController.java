package com.koreait.fashionshop.admin.controller;

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
import com.koreait.fashionshop.model.product.service.DumpService;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.SubCategoryService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

//������ ��忡���� ��ǰ�� ���� ��û ó��
@Controller
public class AdminProductController implements ServletContextAware{
	private static final Logger logger=LoggerFactory.getLogger(AdminProductController.class);
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductService productService; //������ ��ǰ��Ͻ�
	
	@Autowired
	private DumpService dumpService; //�뷮 ��Ͻ� 
	
	@Autowired
	private FileManager fileManager;
	
	
	//�츮�� �� ServletContext�� ����ϴ°�?   getRealPath() ����Ϸ���!!!
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//�� Ÿ�̹��� ��ġ������, ���� ������ ��θ� FileManager �� �����س���!!!
		//fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		//fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		fileManager.setSaveBasicDir(fileManager.getSaveBasicDir());
		fileManager.setSaveAddonDir(fileManager.getSaveAddonDir());
		
		logger.debug(fileManager.getSaveBasicDir());
		
	}
	
	//����ī�װ� �������� (�����ڿ�)
	@RequestMapping(value="/product/registform", method=RequestMethod.GET)
	public ModelAndView getTopList(HttpServletRequest request) {
		//3�ܰ�: ���� ��ü�� �Ͻ�Ų��
		List topList = topCategoryService.selectAll();
		
		//4�ܰ�: ���� 
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("admin/product/regist_form");
		
		return mav;
	}
	
	
	//����ī�װ� ��������
	//������������ java��ü�� Json�� ��ȯ(converting)�� �ڵ����� ó�����ִ� ���̺귯���� �����Ѵ�
	@RequestMapping(value="/product/sublist", method=RequestMethod.GET)
	@ResponseBody
	public List getSubList(HttpServletRequest request, int topcategory_id) {
		List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
		return subList;
	}
	
	@GetMapping("/product/excel/registform")
	public String getExcelForm(HttpServletRequest request) {
		return "admin/product/excel_form";
	}
	
	//������ ���� ��ǰ��� ��û ó�� 
	@RequestMapping(value="/product/excel/regist", method=RequestMethod.POST)
	@ResponseBody // �񵿱� �̹Ƿ� 
	public MessageData registByExcel(HttpServletRequest request, MultipartFile dump) {
		String path = fileManager.getSaveBasicDir()+File.separator+dump.getOriginalFilename(); //������ ���ϸ�
		fileManager.saveFile(path, dump);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("������� ����");
		
		//���� �о �����ͺ��̽��� �ֱ�!! 
		dumpService.regist(path);

		return messageData;
	}
	
	//��ǰ���
	@RequestMapping(value="/product/list", method=RequestMethod.GET )
	public ModelAndView getProductList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		List productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	
	//��ǰ��� �� 
	@RequestMapping(value="/product/registform")
	public String registForm() {
		
		return "admin/product/regist_form";
	}
	
	
	//��ǰ �� 
	
	//��ǰ ��� 
	@RequestMapping(value="/product/regist", method=RequestMethod.POST)
	@ResponseBody
	public MessageData registProduct(HttpServletRequest request, Product product, String[] test) {
		logger.debug("����ī�װ� "+product.getSubCategory().getSubcategory_id());
		logger.debug("��ǰ�� "+product.getProduct_name());
		logger.debug("���� "+product.getPrice());
		logger.debug("�귣�� "+product.getBrand());
		logger.debug("�󼼳��� "+product.getDetail());
		
		for(Psize psize : product.getPsize()) {
			logger.debug(psize.getFit());
		}
		
		productService.regist(fileManager, product); //��ǰ��� ���񽺿��� ��û
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��ǰ ��� �����Դϴ�.");
		
		return messageData;
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
