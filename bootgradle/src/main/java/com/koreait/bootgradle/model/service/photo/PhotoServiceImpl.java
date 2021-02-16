package com.koreait.bootgradle.model.service.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.bootgradle.exception.FileUploadException;
import com.koreait.bootgradle.exception.PhotoUpdateException;
import com.koreait.bootgradle.model.common.UploadManager;
import com.koreait.bootgradle.model.domain.Photo;
import com.koreait.bootgradle.model.photo.repository.PhotoDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoDAO photoDAO;
	
	@Autowired
	private UploadManager uploadManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Photo photo) throws PhotoUpdateException,FileUploadException{
		log.debug("regist()호출");
		
		//여기서 트랜잭션!!	(exception명시하면 spring은 알아서 트랜잭션 처리를 해준다)
		uploadManager.save(photo.getMyFile());
		photo.setFilename(photo.getMyFile().getOriginalFilename());
		photoDAO.insert(photo);
		
	}

}
