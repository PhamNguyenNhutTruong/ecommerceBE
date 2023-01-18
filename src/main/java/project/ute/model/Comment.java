package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Comment")
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="rating_id")
	private String ratingId;
	
	@Column(name="created_at")
	private String createdAt;
	
	public Comment() {

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
