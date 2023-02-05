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
@Table(name="Orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="order_at")
	private Timestamp orderAt;

	private Long total;

	//bi-directional many-to-one association to LineItem
	@OneToMany(mappedBy="order")
	private List<LineItem> lineItems;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="order_by")
	private Customer customer;

	//bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name="method_id")
	private PaymentMethod paymentMethod;

	//bi-directional many-to-one association to Shipment
	@ManyToOne
	@JoinColumn(name="ship_id")
	private Shipment shipment;

	//bi-directional many-to-one association to Stepper
	@ManyToOne
	@JoinColumn(name="step_id")
	private Stepper stepper;

	//bi-directional many-to-one association to Vouncher
	@ManyToOne
	private Vouncher vouncher;

	public Order() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getOrderAt() {
		return this.orderAt;
	}

	public void setOrderAt(Timestamp orderAt) {
		this.orderAt = orderAt;
	}

	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<LineItem> getLineItems() {
		return this.lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public LineItem addLineItem(LineItem lineItem) {
		getLineItems().add(lineItem);
		lineItem.setOrder(this);

		return lineItem;
	}

	public LineItem removeLineItem(LineItem lineItem) {
		getLineItems().remove(lineItem);
		lineItem.setOrder(null);

		return lineItem;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Shipment getShipment() {
		return this.shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Stepper getStepper() {
		return this.stepper;
	}

	public void setStepper(Stepper stepper) {
		this.stepper = stepper;
	}

	public Vouncher getVouncher() {
		return this.vouncher;
	}

	public void setVouncher(Vouncher vouncher) {
		this.vouncher = vouncher;
	}

}