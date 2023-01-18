package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="given_name")
	private String givenName;
	
	@Column(name="family_name")
	private String familyName;
	
	@Column(name="verified_email")
	private boolean verifiedEmail;
	
	@Column(name="picture")
	private String picture;
	
	@Column(name="is_google_login")
	private boolean isGoogleLogin;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phonenumber")
	private String phonenumber;
	
	public Customer() {

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public boolean isVerifiedEmail() {
		return verifiedEmail;
	}
	public void setVerifiedEmail(boolean verifiedEmail) {
		this.verifiedEmail = verifiedEmail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public boolean isGoogleLogin() {
		return isGoogleLogin;
	}
	public void setGoogleLogin(boolean isGoogleLogin) {
		this.isGoogleLogin = isGoogleLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
