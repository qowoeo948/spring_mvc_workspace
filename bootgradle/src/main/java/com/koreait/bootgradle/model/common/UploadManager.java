package com.koreait.bootgradle.model.common;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.bootgradle.exception.FileUploadException;

import lombok.extern.slf4j.Slf4j;

//���� ������ �����ϴ� ���� ��ü

@Component
@Slf4j
public class UploadManager {
	
	public void save(MultipartFile multi) throws FileUploadException{
		//������
		String dir = this.getClass().getResource("/static/data").getPath();
		String filename=multi.getOriginalFilename();
		
		log.debug("������ ��δ�{}�Դϴ� ",dir+"/"+filename);
		
		try {
			multi.transferTo(new File(dir+"/"+filename));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadException("���� �������",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadException("���� �������",e);
		}
	}

}
