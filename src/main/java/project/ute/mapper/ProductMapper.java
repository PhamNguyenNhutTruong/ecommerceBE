package project.ute.mapper;

import project.ute.dto.ProductDto;
import project.ute.model.Product;

public class ProductMapper {
	public static ProductDto toProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setProductName(product.getName());
		productDto.setNewPrice(product.getPriceSale());
		productDto.setOldPrice(product.getPrice());
		return productDto;
	}
}
