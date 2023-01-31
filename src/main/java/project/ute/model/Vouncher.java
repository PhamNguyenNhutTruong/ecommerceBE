package project.ute.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="Vouncher")
public class Vouncher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="close_date")
	private Timestamp closeDate;

	private Long discount;

	private String name;

	@Column(name="open_date")
	private Timestamp openDate;

	private Integer quantity;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="vouncher")
	private List<Order> orders;

	public Vouncher() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}

	public Long getDiscount() {
		return this.discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setVouncher(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setVouncher(null);

		return order;
	}

}