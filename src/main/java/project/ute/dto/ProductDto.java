package project.ute.dto;

public class ProductDto {
	private String id;
	private String categoryId;
	private String productName;
	private long oldPrice;
	private long newPrice;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(String id, String categoryId, String productName, long oldPrice, long newPrice) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.productName = productName;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(long oldPrice) {
		this.oldPrice = oldPrice;
	}
	public long getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(long newPrice) {
		this.newPrice = newPrice;
	}
	
}
