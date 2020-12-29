package com.koreait.mvclegacy.model.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

@Service
public class NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	//CRUD method 
	public List selectAll() {
		List list = noticeDAO.selectAll();
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = noticeDAO.select(notice_id);
		return notice;
	}
	
	public void insert(Notice notice) {
		noticeDAO.insert(notice);
	}
	public void update(Notice notice) throws DMLException{
		noticeDAO.update(notice);
	}
	public void delete(int notice_id) {
		noticeDAO.delete(notice_id);
	}	
	
}