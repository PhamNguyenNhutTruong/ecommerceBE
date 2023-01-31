package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;
	
//	True: Còn kinh doanh/ False: Ngừng kinh doanh
	private boolean status;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category")
	private List<Product> products;

	public Category() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	Dùng để giới hạn trường được gởi đi theo dữ liệu của API. Trường dữ liệu nào được gắ anotation này sẽ không được gởi theo PAI
//	@JsonIgnore
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
//	Dùng để giới hạn trường được gởi đi theo dữ liệu của API. Trường dữ liệu nào được gắ anotation này sẽ không được gởi theo PAI
//	@JsonIgnore
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}