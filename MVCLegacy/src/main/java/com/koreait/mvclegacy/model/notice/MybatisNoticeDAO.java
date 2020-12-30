package com.koreait.mvclegacy.model.notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;

@Repository
public class MybatisNoticeDAO implements NoticeDAO{
	//�������� �����ϴ� DB����� �̿��ϱ�������, Ʈ����� ������ SqlSession�� ���񽺷κ���
	//���Թ޾�����, ���̻� Ʈ����� ������ ���񽺿��� ���Թ��� �ʿ����..�������� �˾Ƽ� Ʈ�������
	//ó�����ش�..
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	
	public List selectAll() {
		List list = null;
		list=sessionTemplate.selectList("Notice.selectAll");
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice = null;
		notice = sessionTemplate.selectOne("Notice.select", notice_id);
		return notice;
	}
	
	public void insert(Notice notice){
		sessionTemplate.insert("Notice.insert", notice);
	}
	public void update(Notice notice) throws DMLException{
		int result = sessionTemplate.update("Notice.update", notice);
		//result=0;//������..����
		if(result==0) {//��������
			throw new DMLException("�����۾��� �����Ͽ����ϴ�");
		}
	}
	public void delete(int notice_id) {
		sessionTemplate.delete("Notice.delete", notice_id);
	}
}
