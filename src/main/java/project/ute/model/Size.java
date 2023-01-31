package project.ute.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="Size")
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private Long price;

	//bi-directional many-to-one association to LineItem
	@OneToMany(mappedBy="size")
	private List<LineItem> lineItems;

	public Size() {
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

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public List<LineItem> getLineItems() {
		return this.lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public LineItem addLineItem(LineItem lineItem) {
		getLineItems().add(lineItem);
		lineItem.setSize(this);

		return lineItem;
	}

	public LineItem removeLineItem(LineItem lineItem) {
		getLineItems().remove(lineItem);
		lineItem.setSize(null);

		return lineItem;
	}

}