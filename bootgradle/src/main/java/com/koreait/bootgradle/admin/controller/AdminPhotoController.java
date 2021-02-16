package com.koreait.bootgradle.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.bootgradle.model.common.UploadManager;
import com.koreait.bootgradle.model.domain.Photo;
import com.koreait.bootgradle.model.service.photo.PhotoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminPhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	//파일업로드 폼 요청
	@GetMapping("/admin/photo/registform")
	public String getForm() {
		
		return "admin/photo/upload"; //thymeleaf page
	}

	//등록요청 처리
	@PostMapping("/admin/photo")
	public String regist(Photo photo) {
		//log.debug("넘겨받은 제목은 "+photo.getTitle());
		System.out.println("넘겨받은 제목은 "+photo.getTitle());
		System.out.println("넘겨받은 파일명은 "+photo.getMyFile().getOriginalFilename());

		//파일 저장 로직~
		photoService.regist(photo);
		
		return null;
	}
}
