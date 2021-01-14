package com.koreait.fashionshop.model.common;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.fashionshop.exception.UploadFailException;

import lombok.Data;

@Data
@Component  /*component-scan의 대상 중 하나임..*/
public class FileManager {
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	private String saveBasicDir="/resources/data/basic";
	private String saveAddonDir="/resources/data/addon";
		
	public static String getExtend(String path) {
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		//System.out.println(ext);		
		return ext;
	}
	
	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
	
	//파일 저장하기
	public void saveFile(String realDir , MultipartFile multi) throws UploadFailException{
		try {
			multi.transferTo(new File(realDir));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadFailException("파일 업로드 실패");
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadFailException("파일 업로드 실패");
		}
	}
	
}











