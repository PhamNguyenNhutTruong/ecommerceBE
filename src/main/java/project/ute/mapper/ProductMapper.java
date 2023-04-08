package project.ute.mapper;

import java.util.ArrayList;
import java.util.List;

import project.ute.dto.ImageDto;
import project.ute.dto.ProductDto;
import project.ute.model.Image;
import project.ute.model.Product;
import project.ute.util.ConstantUtils;

public class ProductMapper {
	public static ProductDto toProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setName(product.getName());
		productDto.setNewPrice(product.getPriceSale());
		productDto.setOldPrice(product.getPrice());
		productDto.setMainImage(ConstantUtils.URL__LOAD_IMAGE_FROM_PRODUCT_TABLE + product.getName());
		productDto.setDetail(product.getDetail());
		productDto.setCreatedBy(product.getUser().getId());
		return productDto;
	}
	
	public static ProductDto toDetailProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setName(product.getName());
		productDto.setNewPrice(product.getPriceSale());
		productDto.setOldPrice(product.getPrice());
		productDto.setMainImage(ConstantUtils.URL__LOAD_IMAGE_FROM_PRODUCT_TABLE + product.getName());
		productDto.setDetail(product.getDetail());
		productDto.setCreatedBy(product.getUser().getId());
		productDto.setImages(toListImageDto(product.getImages()));
		return productDto;
	}
	
	public static List<ImageDto> toListImageDto(List<Image> images) {
		List<ImageDto> imageDtos = new ArrayList<>();
		for(Image image : images) {
			imageDtos.add(ImageMapper.toImageDto(image));
		}
		return imageDtos;
	} 
}
