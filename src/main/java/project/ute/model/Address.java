package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Address")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="provice_code")
	private String proviceCode;
	
	@Column(name="district_code")
	private String districtCode;
	
	@Column(name="ward_code")
	private String wardCode;	
	
	@Column(name="address_text")
	private String addressText;
	
	public Address() {

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProviceCode() {
		return proviceCode;
	}
	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getAddressText() {
		return addressText;
	}
	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}
	
}
