package com.koreait.bootgradle.model.service.photo;

import org.springframework.stereotype.Service;

import com.koreait.bootgradle.model.domain.Photo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PhotoServiceImpl implements PhotoService{

	@Override
	public void regist(Photo photo) {
		log.debug("regist()»£√‚");
	}

}
