package com.koreait.mvclegacy.model.notice;

import java.util.List;

import com.koreait.mvclegacy.model.domain.Notice;

//Enterprise 개발에서는 규모가 크기 때문에 각 영역마다 (MVC) 객체들이 세분화되어 있다.
//즉 나누어져 있다. 이때 분리된 객체간의 관계는  느슨할 수록 좋다. 즉 객체간 결합도를 낮추는 것이 좋다!! 이 방법을
//구현한 기술이 바로 DI(의존성이 강한 걸 외부에서 주입받자) 즉 의존성이 강한 객체를 보유하지 말고, 외부에서 주입받되, 주입받는 객체는
//해당 자료형을 상위자료형으로 받자는 것이다.
public interface NoticeDAO {

	public List selectAll();
	public Notice select(int notice_id);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int notice_id);
}
