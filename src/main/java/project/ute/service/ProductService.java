package project.ute.service;

import java.util.List;
import java.util.Optional;

import project.ute.dto.ProductDto;

public interface ProductService {
	public List<ProductDto> getProductByCategoryId(String id, Optional<Integer> pageNo);
}
