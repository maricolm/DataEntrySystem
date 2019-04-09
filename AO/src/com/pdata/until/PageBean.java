package com.pdata.until;

import java.util.List;

public class PageBean<T> {
	private int pageSize;//	pageSize 每页大小
	private int currentPage;//	currentPage 当前页数
	private int totalPage;//	totalPage 总页数
	private int startIndex;//	startIndex 每页开始的记录数
	private int totalRecords;//	totalRecords 总的条数
	private List<T> beanList;//	beanList 封装的记录
	private int startPage;//	startPage 开始的页码
	//1 2 3 4 5 6 7 8 9 
	//endPage结束页码
	private int endPage;

	//本项目需要，临时添加
	private String condition;
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public PageBean(int currentPage,int totalRecords,int pageSize){
		this.currentPage=currentPage;
		this.totalRecords=totalRecords;
		this.pageSize=pageSize;
		//计算每页开始的索引数
		//0,10
		//10,10
		//20,10
		startIndex=(currentPage-1)*pageSize;
		//总页数
//		totalPage=totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
		totalPage=(int) Math.ceil(totalRecords*1.0/pageSize);
		
		//显示页码
		if(totalPage<=9){
			startPage=1;
			endPage=totalPage;
		}else{
			startPage=currentPage-4;
			endPage=currentPage+4;
			//判断开始页码减4后是否小于1
			if(startPage<1){
				startPage=1;
				endPage=9;
			}
			if(endPage>totalPage){
				startPage=totalPage-8;
				endPage=totalPage;
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
