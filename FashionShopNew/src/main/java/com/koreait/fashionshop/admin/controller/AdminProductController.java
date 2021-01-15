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

//관리자 모드에서의 상품에 대한 요청 처리
@Controller
public class AdminProductController implements ServletContextAware{
	private static final Logger logger=LoggerFactory.getLogger(AdminProductController.class);
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductService productService; //낱개의 상품등록시
	
	@Autowired
	private DumpService dumpService; //대량 등록시 
	
	@Autowired
	private FileManager fileManager;
	
	
	//우리가 왜 ServletContext를 써야하는가?   getRealPath() 사용하려고!!!
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//이 타이밍을 놓치지말고, 실제 물리적 경로를 FileManager 에 대입해놓자!!!
		//fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		//fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		fileManager.setSaveBasicDir(fileManager.getSaveBasicDir());
		fileManager.setSaveAddonDir(fileManager.getSaveAddonDir());
		
		logger.debug(fileManager.getSaveBasicDir());
		
	}
	
	//상위카테고리 가져오기 (관리자용)
	@RequestMapping(value="/product/registform", method=RequestMethod.GET)
	public ModelAndView getTopList(HttpServletRequest request) {
		//3단계: 로직 객체에 일시킨다
		List topList = topCategoryService.selectAll();
		
		//4단계: 저장 
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("admin/product/regist_form");
		
		return mav;
	}
	
	
	//하위카테고리 가져오기
	//스프링에서는 java객체와 Json간 변환(converting)을 자동으로 처리해주는 라이브러리를 지원한다
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
	
	//엑셀에 의한 상품등록 요청 처리 
	@RequestMapping(value="/product/excel/regist", method=RequestMethod.POST)
	@ResponseBody // 비동기 이므로 
	public MessageData registByExcel(HttpServletRequest request, MultipartFile dump) {
		String path = fileManager.getSaveBasicDir()+File.separator+dump.getOriginalFilename(); //저장할 파일명
		fileManager.saveFile(path, dump);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("엑셀등록 성공");
		
		//엑셀 읽어서 데이터베이스에 넣기!! 
		dumpService.regist(path);

		return messageData;
	}
	
	//상품목록
	@RequestMapping(value="/product/list", method=RequestMethod.GET )
	public ModelAndView getProductList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		List productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	
	//상품등록 폼 
	@RequestMapping(value="/product/registform")
	public String registForm() {
		
		return "admin/product/regist_form";
	}
	
	
	//상품 상세 
	
	//상품 등록 
	@RequestMapping(value="/product/regist", method=RequestMethod.POST)
	@ResponseBody
	public MessageData registProduct(HttpServletRequest request, Product product, String[] test) {
		logger.debug("하위카테고리 "+product.getSubCategory().getSubcategory_id());
		logger.debug("상품명 "+product.getProduct_name());
		logger.debug("가격 "+product.getPrice());
		logger.debug("브랜드 "+product.getBrand());
		logger.debug("상세내용 "+product.getDetail());
		
		for(Psize psize : product.getPsize()) {
			logger.debug(psize.getFit());
		}
		
		productService.regist(fileManager, product); //상품등록 서비스에게 요청
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("상품 등록 성공입니다.");
		
		return messageData;
	}


	
	
	//상품 수정
	
	//상품 삭제

	
	//예외처리 
	//위의 메서드 중에서 하나라도 예외가 발생하면, 아래의 핸들러가 동작
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
