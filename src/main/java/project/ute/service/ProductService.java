package project.ute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.MessageDto;
import project.ute.dto.ProductDto;
import project.ute.model.Product;

public interface ProductService {
	public Product findProductById(String id);
	public List<ProductDto> getProductByCategoryId(String id, Optional<Integer> pageNo);
	public MessageDto addNewProduct(String detail, MultipartFile image, String name, long price, long priceSale, boolean status, String categoryId, String createBy);
	public String randomProductId();
	public byte[] loadMainProductImage(String name);
	public ProductDto getDetailProductById(String id);
}
