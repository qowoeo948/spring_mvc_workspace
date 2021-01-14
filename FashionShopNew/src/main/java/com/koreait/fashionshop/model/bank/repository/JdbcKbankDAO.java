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
	
	//DI 란? 의존성 있는 객체는 직접 생성하지 말고,외부로부터 주입받자 
	//         객체간 결합도를 약화시키기 위함..
	//         그렇다면 결합도 마저도 없애버리는 방법이 있는가? O AOP
	
	//출금
	public void withdraw(int money) throws WithdrawFailException{		
		int result = jdbcTemplate.update("update kbank set total=total-?", money);
		if(result==0) {
			throw new WithdrawFailException("Sorry Withdraw fail");
		}
	}
	
}
