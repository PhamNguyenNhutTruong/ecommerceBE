package project.ute.dto;

import org.springframework.http.HttpStatus;

public class TokenDto {
	private String accessToken;
	private String refreshToken;
	
	public TokenDto() {
		super();
	}

	public TokenDto(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
