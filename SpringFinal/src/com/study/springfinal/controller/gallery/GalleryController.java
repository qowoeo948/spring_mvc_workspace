package com.study.springfinal.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.study.springfinal.common.FileManager;
import com.study.springfinal.domain.Gallery;
import com.study.springfinal.model.dao.GalleryDAO;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	//표시를 할테니, 여기에 넣어주세요
	@Autowired
	private GalleryDAO galleryDAO;

	//등록하기
	//스프링 프레임웍은 업로드 컴포넌트로, apache fileupload를 사용함
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(Gallery gallery, HttpServletRequest request) {
		System.out.println("등록 원해?");
		
//	   System.out.println("title "+gallery.getTitle());
//	   System.out.println("writer "+gallery.getWriter());
//		System.out.println("content "+gallery.getContent());
//		System.out.println("photo "+gallery.getPhoto());
//		
		//물리적 저장
		MultipartFile photo = gallery.getPhoto();
		System.out.println("original is "+photo.getOriginalFilename());
		System.out.println("getName is "+photo.getName());
		System.out.println("size is "+photo.getSize());
		System.out.println("content type is "+photo.getContentType());
		
		//파일명 새로 만들어, 저장하기
		String newName = Long.toString(System.currentTimeMillis());
		String ext = FileManager.getExtend(photo.getOriginalFilename());
		String filename = newName+"."+ext;	//최종 파일명..
		gallery.setFilename(filename); //새로운 파일명 VO에 저장
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath("/data");
		//이클립스 내부 톰켓인 경우, 실제 경로와는 틀린 경로에 저장..
		//개발시엔 그 경로를 확인하려면 출력해보자
		System.out.println(realPath);
		try {
			photo.transferTo(new File(realPath+"/"+filename)); //물리적 저장!
			
			int result = galleryDAO.insert(gallery);
			System.out.println("등록결과는 "+result);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//저장할것이 없다면 스트링만 이렇게 해도된다
		return "redirect:/gallery/list";
	}
	
	//목록 가져오기
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView selectAll() {
		//3단계
		List galleryList = galleryDAO.selectAll();
		
		//4단계: 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("galleryList",galleryList);
		mav.setViewName("gallery/list");
		return mav;
	}
	
	//상세보기 요청 처리
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView select(int gallery_id) {
		System.out.println("gallery_id "+gallery_id);
		Gallery gallery = galleryDAO.select(gallery_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gallery",gallery);
		mav.setViewName("gallery/detail");
		return mav;
	}
	
	//글 수정 요청처리
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(Gallery gallery) {
		//3단계:일시키기
		galleryDAO.update(gallery);
		//4단계: 저장할 것이 없다. 왜? 요청을 끊고 detail을 새로 접속할거니까.
		return "redirect:/gallery/detail?gallery_id="+gallery.getGallery_id();
	}
	
	//글 삭제 요청 처리
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(int gallery_id) {
		galleryDAO.delete(gallery_id);
		//저장할것이 없다. 왜? list를 새롭게 접속할거니까.
		return "redirect:/gallery/list";
	}
	
}
