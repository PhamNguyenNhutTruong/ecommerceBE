package project.ute.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ProductDto {
	private String id;
	private String categoryId;
	private String name;
	private long oldPrice;
	private long newPrice;
	private String mainImage;
	private String detail;
	private String createdBy;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ImageDto> images;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(String id, String categoryId, String name, long oldPrice, long newPrice, String mainImage, String detail, String createdBy) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.mainImage = mainImage;
		this.detail = detail;
		this.createdBy = createdBy;
	}
	
	
	public ProductDto(String id, String categoryId, String name, long oldPrice, long newPrice, String mainImage,
			String detail, String createdBy, List<ImageDto> images) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.mainImage = mainImage;
		this.detail = detail;
		this.createdBy = createdBy;
		this.images = images;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<ImageDto> getImages() {
		return images;
	}

	public void setImages(List<ImageDto> images) {
		this.images = images;
	}
}
