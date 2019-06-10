package com.blog.vo;

public class LinkVO {
	
	private Long prevPno;
	private String prevTitle;
	private Long nextPno;
	private String nextTitle;
	
	public Long getPrevPno() {
		return prevPno;
	}
	public void setPrevPno(Long prevPno) {
		this.prevPno = prevPno;
	}
	public String getPrevTitle() {
		return prevTitle;
	}
	public void setPrevTitle(String prevTitle) {
		this.prevTitle = prevTitle;
	}
	public Long getNextPno() {
		return nextPno;
	}
	public void setNextPno(Long nextPno) {
		this.nextPno = nextPno;
	}
	public String getNextTitle() {
		return nextTitle;
	}
	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}
	@Override
	public String toString() {
		return "LinkVO [prevPno=" + prevPno + ", prevTitle=" + prevTitle + ", nextPno=" + nextPno + ", nextTitle="
				+ nextTitle + "]";
	}
}
