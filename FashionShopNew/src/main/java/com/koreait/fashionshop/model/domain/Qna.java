package com.koreait.fashionshop.model.domain;

import lombok.Data;

@Data
public class Qna {
	private int qna_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
}
