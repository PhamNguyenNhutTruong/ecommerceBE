package project.ute.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the footer database table.
 * 
 */
@Entity
@Table(name="Footer")
public class Footer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String link;

	public Footer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}