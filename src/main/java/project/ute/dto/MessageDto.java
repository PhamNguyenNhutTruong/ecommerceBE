package project.ute.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MessageDto {
	private String title;
	private String message;
	private String status;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customerId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private TokenDto token;
	
	private HttpStatus httpStatus;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CustomerDto data;
	
	public MessageDto() {
		super();
	}

//	public MessageDto(String title, String message, String status) {
//		super();
//		this.title = title;
//		this.message = message;
//		this.status = status;
//	}
//
//	
	public MessageDto(String title, String message, String status, HttpStatus httpStatus, CustomerDto data) {
		super();
		this.title = title;
		this.message = message;
		this.status = status;
		this.httpStatus = httpStatus;
		this.data = data;
	}
	
	public MessageDto(String title, String message, String status, String email, TokenDto token, HttpStatus httpStatus) {
		super();
		this.title = title;
		this.message = message;
		this.status = status;
		this.email = email;
		this.token = token;
		this.httpStatus = httpStatus;
	}
	
	public MessageDto(String customerId, String title, String message, String status, String email, TokenDto token, HttpStatus httpStatus) {
		super();
		this.customerId = customerId;
		this.title = title;
		this.message = message;
		this.status = status;
		this.email = email;
		this.token = token;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public TokenDto getToken() {
		return token;
	}

	public void setToken(TokenDto token) {
		this.token = token;
	}

	public CustomerDto getData() {
		return data;
	}

	public void setData(CustomerDto data) {
		this.data = data;
	}
	
}
