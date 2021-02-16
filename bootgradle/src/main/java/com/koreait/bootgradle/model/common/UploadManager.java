package com.koreait.bootgradle.model.common;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.bootgradle.exception.FileUploadException;

import lombok.extern.slf4j.Slf4j;

//파일 저장을 전담하는 로직 객체

@Component
@Slf4j
public class UploadManager {
	
	public void save(MultipartFile multi) throws FileUploadException{
		//저장경로
		String dir = this.getClass().getResource("/static/data").getPath();
		String filename=multi.getOriginalFilename();
		
		log.debug("저장할 경로는{}입니다 ",dir+"/"+filename);
		
		try {
			multi.transferTo(new File(dir+"/"+filename));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadException("파일 저장실패",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadException("파일 저장실패",e);
		}
	}

}
