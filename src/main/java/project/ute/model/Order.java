package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="vouncher_id")
	private String voucherId;
	
	@Column(name="ship_id")
	private String shipId;
	
	@Column(name="step_id")
	private String stepId;
	
	@Column(name="method_id")
	private String methodId;
	
	@Column(name="total")
	private long total;
	
	@Column(name="order_at")
	private String orderAt;
	
	@Column(name="order_by")
	private String orderBy;
	
	public Order() {

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getOrderAt() {
		return orderAt;
	}
	public void setOrderAt(String orderAt) {
		this.orderAt = orderAt;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
