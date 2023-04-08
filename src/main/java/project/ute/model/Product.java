package project.ute.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="Product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String detail;

	@Column(name="main_image")
	private byte[] mainImage;
	
	 @Column(unique=true)
	private String name;

	private Long price;

	@Column(name="price_sale")
	private Long priceSale;

	private Boolean status;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="product")
	private List<Image> images;

	//bi-directional many-to-one association to LineItem
	@OneToMany(mappedBy="product")
	private List<LineItem> lineItems;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	//bi-directional many-to-many association to Customer
	@ManyToMany
	@JoinTable(
		name="wishlist"
		, joinColumns={
			@JoinColumn(name="product_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="customer_id")
			}
		)
	private List<Customer> customers;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_by")
	private User user;

	public Product() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public byte[] getMainImage() {
		return this.mainImage;
	}

	public void setMainImage(byte[] mainImage) {
		this.mainImage = mainImage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getPriceSale() {
		return this.priceSale;
	}

	public void setPriceSale(Long priceSale) {
		this.priceSale = priceSale;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setProduct(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setProduct(null);

		return image;
	}

	public List<LineItem> getLineItems() {
		return this.lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public LineItem addLineItem(LineItem lineItem) {
		getLineItems().add(lineItem);
		lineItem.setProduct(this);

		return lineItem;
	}

	public LineItem removeLineItem(LineItem lineItem) {
		getLineItems().remove(lineItem);
		lineItem.setProduct(null);

		return lineItem;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}