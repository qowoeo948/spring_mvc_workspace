package com.koreait.mvclegacy.model.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

@Service
public class NoticeService {
	//주입시키려 하는 자료형이 2개 이상일 경우, 개발자는 무엇을 사용할지 원하는 객체를 명시해야 한다.
	
	@Autowired
	@Qualifier("jdbcNoticeDAO")	//자동으로 맨앞에 소문자인거를 간주함, (구분해준다)
	//@Qualifier("mybatisNoticeDAO")	//자동으로 맨앞에 소문자인거를 간주함, (구분해준다)  ->저기만 바꿔주면 무슨기술을 쓰더라도 유연해진다
	private NoticeDAO noticeDAO;	//DI로 주입받기 위해서 상위객체를 보유한
	
	//CRUD method 
	public List selectAll() {
		List list = noticeDAO.selectAll();
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = noticeDAO.select(notice_id);
		return notice;
	}
	
	public void insert(Notice notice) throws DMLException{
		noticeDAO.insert(notice);
	}
	public void update(Notice notice) throws DMLException{
		noticeDAO.update(notice);
	}
	public void delete(int notice_id) throws DMLException{
		noticeDAO.delete(notice_id);
	}	
	
}