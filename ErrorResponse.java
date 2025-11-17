package com.practice.DTO;

import java.time.LocalDate;

public class ErrorResponse {
     private String msg;
     private LocalDate time;
     private String description;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LocalDate getTime() {
		return time;
	}
	public void setTime(LocalDate time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ErrorResponse(String msg, LocalDate time, String description) {
		super();
		this.msg = msg;
		this.time = time;
		this.description = description;
	}
     
}
