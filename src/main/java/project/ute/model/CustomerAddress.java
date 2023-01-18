package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CustomerAddress")
public class CustomerAddress implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="main_address")
	private String mainAddress;
	
	@Column(name="address_id")
	private String addressId;
	
	@Column(name="customer_id")
	private String customerId;
	
	public CustomerAddress() {

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMainAddress() {
		return mainAddress;
	}
	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
