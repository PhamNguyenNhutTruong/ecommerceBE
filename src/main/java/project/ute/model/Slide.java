package project.ute.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Slide")
public class Slide implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(unique = true)
	private String name;

	private String detail;

	private byte[] link;

	public Slide() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public byte[] getLink() {
		return this.link;
	}

	public void setLink(byte[] link) {
		this.link = link;
	}

}