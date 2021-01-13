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

//관리자 모드에서의 상품에 대한 요청 처리
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
	
	//우리가 왜 servletcontext를 써야하는가? getRealPath() 사용하려고!!
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//이 타이밍을 놓치지 말고, 실제 물리적 경로를 FileManager에 대입해놓자.
		fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		logger.debug(fileManager.getSaveBasicDir());
		//logger.debug(fileManager.getSaveAddonDir());
	}
	
	
	//상위카테고리 가져오기		(관리자용)
	@RequestMapping(value="/admin/product/registform",method=RequestMethod.GET)
	public ModelAndView getTopList(HttpServletRequest request) {
		//3단계 업무: 로직객체에 일시킨다.
		List topList = topCategoryService.selectAll();
		
		//4단계: 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList",topList);
		mav.setViewName("admin/product/regist_form");
		return mav;
	}
	
	//하위카테고리 가져오기 
	//스프링에서는 java객체와 Json간 변환(converting)을 자동으로 처리해주는 라이브러리를 지원한다.
	@RequestMapping(value="/admin/product/sublist", method=RequestMethod.GET)
	@ResponseBody
	public List getSubList(int topcategory_id,HttpServletRequest request) {
		List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
		return  subList;
	}
	
	
	/*
	   @RequestMapping(value="/admin/product/sublist", method=RequestMethod.GET, produces="application/json;charset=utf8")
	   @ResponseBody //jsp와 같은 뷰페이지가 아닌, 단순 데이터만 전송시..
	   public String getSubList(int topcategory_id) {
	      logger.debug("topcategory_id : "+topcategory_id);
	      List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
	      
	      //리스트를 json으로 변형하여 보내줘야함..
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
	
	//엑셀 대량 등록폼
	@GetMapping("/admin/product/excel/registform")
	public String getExcelForm(HttpServletRequest request) {
		
		return "admin/product/excel_form";
	}
	
	//엑셀에 의한 상품등록 요청 처리
	@RequestMapping(value="admin/product/excel/regist",method=RequestMethod.POST,produces="text/html;charset=utf8")
	@ResponseBody		//비동기 이므로
	public String registByExcel(MultipartFile dump,HttpServletRequest request) {
		String path = fileManager.getSaveBasicDir()+File.separator+dump.getOriginalFilename();	//저장할 파일명
		fileManager.saveFile(path, dump);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"상품등록성공\"");
		sb.append("}");
		return sb.toString();
	}
	 

	//상품목록
	@RequestMapping(value="/admin/product/list", method=RequestMethod.GET )
	public ModelAndView getProductList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		List productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	
	//상품 등록 폼
	@RequestMapping(value="/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}
	
	//상품상세
	
	//상품등록
	@RequestMapping(value="/admin/product/regist", method=RequestMethod.POST, produces="text/html;charset=utf8")
	@ResponseBody		//페이지 응답이 아니라 text응답이다~
	public String registProduct(Product product,String[] test,HttpServletRequest request) {
		logger.debug("하위카테고리 "+product.getSubCategory().getSubcategory_id());
		logger.debug("상품명 "+product.getProduct_name());
		logger.debug("가격 "+product.getPrice());
		logger.debug("브랜드 "+product.getBrand());
		logger.debug("상세내용 "+product.getDetail());
		
		for(Psize psize : product.getPsize()) {
			logger.debug(psize.getFit());
		}
	
		//logger.debug("insert하기 전 상품의 product_id "+product.getProduct_id());
		
		productService.regist(fileManager,product); 	//상품 디비에 등록, 서비스에게 요청

		//logger.debug("방금 insert된 상품의 product_id "+product.getProduct_id());	//여기서 product.getProduct_id 는 대표이미지 파일명이 될거야 이제
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"상품등록성공\"");
		sb.append("}");
		
		return sb.toString();
		
	}


	
	//상품 수정
	
	//상품 삭제
	
	
	
	
	
	//**********************************************************************************************//
	//쇼핑몰 프론트 요청 처리 
	//**********************************************************************************************//
	
	//상품목록 요청 처리
	@RequestMapping(value="/shop/product/list",method=RequestMethod.GET)
	public ModelAndView getShopProductList(int subcategory_id,HttpServletRequest request) {		//하위카테고리의 id가 날라와야됨
		//상품목록
		List productList = productService.selectById(subcategory_id);	

		ModelAndView mav = new ModelAndView();
		mav.addObject("productList",productList);
		mav.setViewName("shop/product/list");
		return mav;
	}
	
	//상품 상세보기 요청
	@RequestMapping(value="/shop/product/detail",method=RequestMethod.GET)
	public ModelAndView getShopProductDetail(int product_id,HttpServletRequest request) {
		
		//상품 1건 가져오기
		Product product = productService.select(product_id);
		
		ModelAndView mav = new ModelAndView("shop/product/detail");
		mav.addObject("product",product);
		
		return mav;
	}
	

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
