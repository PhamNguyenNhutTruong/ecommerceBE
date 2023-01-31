package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="CustomerAddress")
public class CustomerAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
//	@Column(name="address_id")
//	private String addressId;
//
//	@Column(name="customer_id")
//	private String customerId;

	@Column(name="main_address")
	private String mainAddress;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public CustomerAddress() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
//	public String getAddressId() {
//		return addressId;
//	}
//
//	public void setAddressId(String addressId) {
//		this.addressId = addressId;
//	}
//
//	public String getCustomerId() {
//		return this.customerId;
//	}
//
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}

	public String getMainAddress() {
		return this.mainAddress;
	}

	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
