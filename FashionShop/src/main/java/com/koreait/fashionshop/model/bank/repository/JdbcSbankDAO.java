package com.koreait.fashionshop.model.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.DepositFailException;
import com.koreait.fashionshop.model.common.Bell;

@Repository
public class JdbcSbankDAO implements SbankDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override		//�Ա�
	public void deposit(int money) throws DepositFailException{
		int result = jdbcTemplate.update("insert into sbank(sbank_id,total) values(seq_sbank.nextval,?)",money);
		result=0;	//�Ϻη� Ʈ����� ���з� ó���غ��� ����.. �����
		if(result==0) {
			throw new DepositFailException("Sorry Deposit Fail.");
		}
	}

}