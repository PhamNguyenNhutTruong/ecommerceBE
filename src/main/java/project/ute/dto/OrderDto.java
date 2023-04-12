package project.ute.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderDto {
	private String id;
	
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss MM/dd/yyyy")
	private Timestamp date;
	private String orderBy;
	private long total;
	private String status;
	private String method;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<LineItemDto> lineItems;
	
	public OrderDto() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public List<LineItemDto> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemDto> lineItems) {
		this.lineItems = lineItems;
	}
}
