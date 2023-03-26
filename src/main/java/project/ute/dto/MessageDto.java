package project.ute.dto;

import org.springframework.http.HttpStatus;

public class MessageDto {
	private String title;
	private String message;
	private String token;
	private String status;
	private HttpStatus httpStatus;
	
	public MessageDto() {
		super();
	}

	public MessageDto(String title, String message, String status) {
		super();
		this.title = title;
		this.message = message;
		this.status = status;
	}

	
	public MessageDto(String title, String message, String status, HttpStatus httpStatus) {
		super();
		this.title = title;
		this.message = message;
		this.status = status;
		this.httpStatus = httpStatus;
	}
	

	public MessageDto(String title, String message, String token, String status, HttpStatus httpStatus) {
		super();
		this.title = title;
		this.message = message;
		this.token = token;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
