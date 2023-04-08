package project.ute.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CustomerDto {
	private String customerId;
	private String avatar;
	private String displayName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String givenName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String familyName;
	private String phonenumber;
	
	public CustomerDto() {
		super();
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
