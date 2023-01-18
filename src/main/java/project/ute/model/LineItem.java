package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="LineItem")
public class LineItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="size_id")
	private String sizeId;
	
	@Column(name="order_id")
	private String orderId;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="amount")
	private long amount;
	
	public LineItem() {

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
}
