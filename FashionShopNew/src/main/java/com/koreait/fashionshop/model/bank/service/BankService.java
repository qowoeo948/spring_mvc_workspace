package com.koreait.fashionshop.model.bank.service;

import org.springframework.stereotype.Service;

public interface BankService {
	//송금!! (출금 + 입금)
	public void send(int money);
}
