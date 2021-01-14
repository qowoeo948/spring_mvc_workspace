/*
 * paging 처리�? ?��?��?��?��?�� ?��?�� ?��?��?�� ?��?��
 * */
package com.koreait.fashionshop.model.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Pager {
	private List list;
	private int totalRecord; //�? ?��코드 ?�� 
	private int pageSize = 10; //?��?���??�� 보여�? ?��코드 ?�� 
	private int totalPage;
	private int blockSize=10; //블럭?�� 보여�? ?��?���? ?��
	private int currentPage = 1; //?��?�� ?��?���?
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num ;
	
	
	//?��?��?�� �??�� 초기?��
	public void init(HttpServletRequest request, List list) {
		this.list=list; //보관
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		//?��?���?�? ?��?��?�� 경우?��, �? ?��?��?�� ?��?���?�? ??�?
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage - (currentPage-1) % blockSize;
		lastPage = firstPage + (blockSize -1);
		curPos = (currentPage - 1) * pageSize; //?��?���??�� List?��?��?��?�� ?��?�� index
		num = totalRecord - curPos; //?��?���??�� ?��?�� 번호
	}

	public List getList() {
		return list;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}


	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getBlockSize() {
		return blockSize;
	}


	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getFirstPage() {
		return firstPage;
	}


	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}


	public int getLastPage() {
		return lastPage;
	}


	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}


	public int getCurPos() {
		return curPos;
	}


	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}
}
