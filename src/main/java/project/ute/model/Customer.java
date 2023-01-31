package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String email;

	@Column(name="family_name")
	private String familyName;

	@Column(name="given_name")
	private String givenName;

	@Column(name="is_google_login")
	private Boolean isGoogleLogin;

	private String name;

	private String password;

	private String phonenumber;

	private String picture;

	@Column(name="verified_email")
	private String verifiedEmail;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="customer")
	private List<Comment> comments;

	//bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy="customer")
	private List<CustomerAddress> customerAddresses;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	//bi-directional many-to-many association to Product
	@ManyToMany(mappedBy="customers")
	private List<Product> products;

	public Customer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public Boolean getIsGoogleLogin() {
		return this.isGoogleLogin;
	}

	public void setIsGoogleLogin(Boolean isGoogleLogin) {
		this.isGoogleLogin = isGoogleLogin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVerifiedEmail() {
		return this.verifiedEmail;
	}

	public void setVerifiedEmail(String verifiedEmail) {
		this.verifiedEmail = verifiedEmail;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setCustomer(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setCustomer(null);

		return comment;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setCustomer(this);

		return customerAddress;
	}

	public CustomerAddress removeCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setCustomer(null);

		return customerAddress;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}