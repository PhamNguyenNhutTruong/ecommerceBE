package project.ute.dto;

public class AccountDto {
	private String email;
	private String password;
	private int role;
	private boolean isGoogleLogin;
	public AccountDto(String email, String password, int role, boolean isGoogleLogin) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.isGoogleLogin = isGoogleLogin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public boolean isGoogleLogin() {
		return isGoogleLogin;
	}
	public void setGoogleLogin(boolean isGoogleLogin) {
		this.isGoogleLogin = isGoogleLogin;
	}
}
