package project.ute.dto;

import org.springframework.http.HttpStatus;

public class TokenDto {
	private String title;
	private String token;
	private String status;

	private HttpStatus httpStatus;
	
	
	public TokenDto(String title, String token, String status, HttpStatus httpStatus) {
		super();
		this.title = title;
		this.token = token;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
