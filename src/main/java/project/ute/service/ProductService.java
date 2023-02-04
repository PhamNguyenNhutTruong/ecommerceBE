package project.ute.service;

import java.util.List;
import java.util.Optional;

import project.ute.dto.ProductDto;
import project.ute.model.Product;

public interface ProductService {
	public Product findProductById(String id);
	public List<ProductDto> getProductByCategoryId(String id, Optional<Integer> pageNo);
}
