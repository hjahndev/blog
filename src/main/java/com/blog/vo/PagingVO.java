package com.blog.vo;

public class PagingVO {

	private PageSettingVO setting;
	private int totalCount;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public PagingVO(PageSettingVO setting, int totalCount) {
		this.setting = setting;
		this.totalCount = totalCount;
		
		totalPage = (int) Math.ceil((double)totalCount / setting.getCountPerPage());
		startPage = ((setting.getPage()-1)/10) / setting.getCountPerPage() + 1;
		
		endPage = startPage + setting.PAGINATION - 1;
				
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		setting.compareTotalPage(totalPage);
	}

	public PageSettingVO getSetting() {
		return setting;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean needFirstPageBtn() {
		return startPage > 1; 
	}
	
	public boolean needLastPageBtn() {
		return endPage < totalPage; 
	}
	
	@Override
	public String toString() {
		return "PagingVO [setting=" + setting + ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}
