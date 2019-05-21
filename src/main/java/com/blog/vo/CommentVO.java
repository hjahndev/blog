package com.blog.vo;

import java.util.Date;

public class CommentVO {
	private Long cno;
	private Long pno;
	private String password;
	private String comment;
	private String writer;
	private Date regDate;
	private Date updateDate;
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	public Long getPno() {
		return pno;
	}
	public void setPno(Long pno) {
		this.pno = pno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [pno=" + pno + ", password=" + password + ", comment=" + comment + ", writer=" + writer + "]";
	}
}
