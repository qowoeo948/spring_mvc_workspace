package com.koreait.bootgradle.model.photo.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.bootgradle.exception.PhotoUpdateException;
import com.koreait.bootgradle.model.domain.Photo;

@Repository
public class MybatisPhotoDAO implements PhotoDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Photo photo) throws PhotoUpdateException{
			int result = sqlSessionTemplate.insert("Photo.insert",photo);
			if(result==0) {
				throw new PhotoUpdateException("파일 insert 실패");
				
			}
	}

}
