package com.blog.vo;

public class PageSettingVO {

	private int countPerPage;
	public static final int PAGINATION = 10;
	private int page;
	private String search;
	
	public PageSettingVO() {
		this(1, 5);
	}
	
	public PageSettingVO(int page, int countPerPage) {
		this.page = page;
		this.countPerPage = countPerPage;
	}
	
	public int getCountPerPage() {
		return countPerPage;
	}
	
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void compareTotalPage(int totalPage) {
		if(page > totalPage) {
			page = totalPage;
		}
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	@Override
	public String toString() {
		return "PageSettingVO [countPerPage=" + countPerPage + ", page=" + page + ", search=" + search + "]";
	}
}
