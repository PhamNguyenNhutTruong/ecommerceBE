package project.ute.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.persistence.Transient;

public class AccountDto implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	@Transient
	public List<GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    String role = null;
	    
	    if (this.getRole() == 1) {
	    	role = "ROLE_USER";
	    } else if (this.getRole() == 0) {
	    	role = "ROLE_ADMIN";
	    }
	    authorities.add(new SimpleGrantedAuthority(role));
	    return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
