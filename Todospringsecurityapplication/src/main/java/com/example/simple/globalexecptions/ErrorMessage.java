package com.example.simple.globalexecptions;

import java.time.LocalDateTime;

public class ErrorMessage {

	private String message;
	private String status;
	private LocalDateTime errorTime;
	public ErrorMessage(String message, String status, LocalDateTime errorTime) {
		super();
		this.message = message;
		this.status = status;
		this.errorTime = errorTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}
	@Override
	public String toString() {
		return "ErrorMessage [message=" + message + ", status=" + status + ", errorTime=" + errorTime + "]";
	}
	
	
}
