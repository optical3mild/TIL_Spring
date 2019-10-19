package com.example.spring03_boot.model.dto;

import java.util.Date;

public class GuestbookDTO {
	private int idx;
	private String name;
	private String email;
	private String content;
	private String passwd;
	private Date post_date;	//java.util.Date
	
	// getter, setter, toString()
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	
	@Override
	public String toString() {
		return "GuestbookDTO [idx=" + idx + ", name=" + name + ", email=" + email + ", content=" + content + ", passwd="
				+ passwd + ", post_date=" + post_date + "]";
	}
	
	
}
