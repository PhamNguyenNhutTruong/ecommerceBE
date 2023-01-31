package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import project.ute.dto.ProductDto;
import project.ute.mapper.ProductMapper;
import project.ute.model.Product;
import project.ute.respository.ProductRepository;
import project.ute.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	public List<ProductDto> getProductByCategoryId(String id, Optional<Integer> pageNo) {
		PageRequest pageable = PageRequest.of(pageNo.orElse(0), 8);
		List<Product> products = new ArrayList<>();
		List<ProductDto> productDtos = new ArrayList<>();
		
		Page<Product> prPage= (Page<Product>) productRepository.getProductByCategoryId(id, pageable);
		products = prPage.getContent();
		for(Product product : products) {
			productDtos.add(ProductMapper.toProductDto(product));
		}
		return productDtos;
	}
}
