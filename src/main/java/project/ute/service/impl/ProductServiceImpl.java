	package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.MessageDto;
import project.ute.dto.ProductDto;
import project.ute.mapper.ProductMapper;
import project.ute.model.Category;
import project.ute.model.Product;
import project.ute.model.User;
import project.ute.respository.ProductRepository;
import project.ute.service.CategoryService;
import project.ute.service.ProductService;
import project.ute.service.UsersService;
import project.ute.util.ConstantUtils;
import project.ute.util.ImageUtils;
import project.ute.util.RandomNumber;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UsersService usersService;
	
	public  Product findProductById(String id) {
		return productRepository.findProductById(id);
	}
	
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

	@Override
	public MessageDto addNewProduct(String detail, MultipartFile image, String name, long price, long priceSale, boolean status,
			String categoryId, String createBy) {
		try {
			Category category = categoryService.getCategoryById(categoryId);
			List<User> users = usersService.getUsersById(createBy);
			
			if(category != null && users.size() > 0) {
				Product product = new Product();
				product.setId(this.randomProductId());
				product.setDetail(detail);
				product.setMainImage(ImageUtils.compressImage(image.getBytes()));
				product.setName(name);
				product.setPrice(price);
				product.setPriceSale(priceSale);
				product.setStatus(status);
				product.setCategory(category);
				product.setUser(users.get(0));
				
				productRepository.save(product);
				return new MessageDto("Add new product", "Add new product successfull", ConstantUtils.SUCCESS, null, null, HttpStatus.OK);
			}
			return new MessageDto("Add new product", "Add new product failed", ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {
			return new MessageDto("Add new product", "Add new product failed", ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String randomProductId() {
		String productId =  null;
		do {
			productId = RandomNumber.randomId("PR");
		} while(this.findProductById(productId) != null);
		return productId;
	}

	@Override
	public byte[] loadMainProductImage(String name) {
		Optional<Product> proOptional = productRepository.findByName(name);
		return ImageUtils.decompressImage(proOptional.get().getMainImage());
	}

	@Override
	public ProductDto getDetailProductById(String id) {
		Product product = this.findProductById(id);
		
		if(product != null) {
			return ProductMapper.toDetailProductDto(product);
		}
		return null;
	}
}
