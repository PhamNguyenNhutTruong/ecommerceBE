package project.ute.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="PaymentMethod")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="is_available")
	private Boolean isAvailable;

	private String name;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="paymentMethod")
	private List<Order> orders;

	public PaymentMethod() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setPaymentMethod(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setPaymentMethod(null);

		return order;
	}

}