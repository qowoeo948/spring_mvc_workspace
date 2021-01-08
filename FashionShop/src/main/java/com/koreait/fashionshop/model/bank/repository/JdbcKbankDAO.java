package com.koreait.fashionshop.model.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.WithdrawFailException;
import com.koreait.fashionshop.model.common.Bell;

@Repository
public class JdbcKbankDAO implements KbankDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//DI��? ������ �ִ� ��ü�� ���� �������� ����, �ܺη� ���� ���Թ���
	//			��ü�� ���յ��� ��ȭ��Ű�� ����..
	//			�׷��ٸ� ���յ� ������ ���ֹ����� ����� �ִ°�?	 O  -->  AOP
	//@Autowired
	//private Bell bell;				-->�̰͸��� ���ֹ�����
	
	
	@Override		//���
	public void withdraw(int money) throws WithdrawFailException{
		//bell.sound();  	-->�̰͸��� ���ֹ�����
		int result = jdbcTemplate.update("update kbank set total=total-?",money);
		
		if(result==0) {
			throw new WithdrawFailException("Sorry Withdraw Fail.");
		}
	}

}
