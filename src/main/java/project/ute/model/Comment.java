package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name="Comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="create_at")
	private Timestamp createAt;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="create_by")
	private Customer customer;

	//bi-directional many-to-one association to Rating
	@ManyToOne
	private Rating rating;

	public Comment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

}