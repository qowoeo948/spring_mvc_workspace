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

//愿�由ъ옄 紐⑤뱶�뿉�꽌�쓽 �긽�뭹�뿉 ���븳 �슂泥� 泥섎━
@Controller
public class AdminProductController implements ServletContextAware{
	private static final Logger logger=LoggerFactory.getLogger(AdminProductController.class);
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductService productService;		//낱개의 상품등록 시
	
	@Autowired
	private DumpService dumpService;	//대량 등록 시
	
	@Autowired
	private FileManager fileManager;
	
	//�슦由ш� �솢 ServletContext瑜� �뜥�빞�븯�뒗媛�?   getRealPath() �궗�슜�븯�젮怨�!!!
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		//�씠 ���씠諛띿쓣 �넃移섏�留먭퀬, �떎�젣 臾쇰━�쟻 寃쎈줈瑜� FileManager �뿉 ���엯�빐�넃�옄!!!
		fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		logger.debug(fileManager.getSaveBasicDir());
		
	}
	
	//�긽�쐞移댄뀒怨좊━ 媛��졇�삤湲� (愿�由ъ옄�슜)
	@RequestMapping(value="/product/registform", method=RequestMethod.GET)
	public ModelAndView getTopList(HttpServletRequest request) {
		//3�떒怨�: 濡쒖쭅 媛앹껜�뿉 �씪�떆�궓�떎
		List topList = topCategoryService.selectAll();
		
		//4�떒怨�: ���옣 
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("admin/product/regist_form");
		
		return mav;
	}
	
	
	//�븯�쐞移댄뀒怨좊━ 媛��졇�삤湲�
	//�뒪�봽留곸뿉�꽌�뒗 java媛앹껜�� Json媛� 蹂��솚(converting)�쓣 �옄�룞�쑝濡� 泥섎━�빐二쇰뒗 �씪�씠釉뚮윭由щ�� 吏��썝�븳�떎
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
	
	//�뿊���뿉 �쓽�븳 �긽�뭹�벑濡� �슂泥� 泥섎━ 
	@RequestMapping(value="/product/excel/regist", method=RequestMethod.POST)
	@ResponseBody // 鍮꾨룞湲� �씠誘�濡� 
	public MessageData registByExcel(HttpServletRequest request, MultipartFile dump) {
		String path = fileManager.getSaveBasicDir()+File.separator+dump.getOriginalFilename(); //���옣�븷 �뙆�씪紐�
		fileManager.saveFile(path, dump);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("�뿊���벑濡� �꽦怨�");
		
		//엑셀 읽어서 데이터베이스에 넣기
		
		dumpService.regist(path);
		
		return messageData;
	}
	
	//�긽�뭹紐⑸줉
	@RequestMapping(value="/product/list", method=RequestMethod.GET )
	public ModelAndView getProductList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		List productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	
	//�긽�뭹�벑濡� �뤌 
	@RequestMapping(value="/product/registform")
	public String registForm() {
		
		return "admin/product/regist_form";
	}
	
	
	//�긽�뭹 �긽�꽭 
	
	//�긽�뭹 �벑濡� 
	@RequestMapping(value="/product/regist", method=RequestMethod.POST)
	@ResponseBody
	public MessageData registProduct(HttpServletRequest request, Product product, String[] test) {
		logger.debug("�븯�쐞移댄뀒怨좊━ "+product.getSubCategory().getSubcategory_id());
		logger.debug("�긽�뭹紐� "+product.getProduct_name());
		logger.debug("媛�寃� "+product.getPrice());
		logger.debug("釉뚮옖�뱶 "+product.getBrand());
		logger.debug("�긽�꽭�궡�슜 "+product.getDetail());
		
		for(Psize psize : product.getPsize()) {
			logger.debug(psize.getFit());
		}
		
		productService.regist(fileManager, product); //�긽�뭹�벑濡� �꽌鍮꾩뒪�뿉寃� �슂泥�
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("�긽�뭹 �벑濡� �꽦怨듭엯�땲�떎.");
		
		return messageData;
	}


	
	
	//�긽�뭹 �닔�젙
	
	//�긽�뭹 �궘�젣

	
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


















