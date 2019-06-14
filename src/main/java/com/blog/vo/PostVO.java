package com.blog.vo;

import java.util.Date;

public class PostVO {

	private Long pno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	private String writerNickname;
	
	public Long getPno() {
		return pno;
	}
	public void setPno(Long pno) {
		this.pno = pno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getWriterNickname() {
		return writerNickname;
	}
	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}

	@Override
	public String toString() {
		return "PostVO [pno=" + pno + ", title=" + title + "]";
	}
}
