package project.ute.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.MessageDto;
import project.ute.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService productService;
	
	
	@RequestMapping(value = "/product/get-product-by-category-id", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllProductByCategoryId(@RequestParam("id") String id, @RequestParam("pageNo")Optional<Integer> pageNo) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCategoryId(id, pageNo));
	}
	
	@RequestMapping(value = "/product/get-detail-product-by-id/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getDetailProductById(@PathVariable("id") String id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getDetailProductById(id));
	}
	
	
	@RequestMapping(value = "/product/add-new-product", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addNewProduct(@RequestParam("detail") String detail, @RequestParam("image") MultipartFile image, @RequestParam("name") String name, @RequestParam("price") long price, @RequestParam("priceSale") long priceSale, @RequestParam("status") boolean status, @RequestParam("categoryId") String categoryId, @RequestParam("createBy") String createBy) {
		MessageDto messageDto = productService.addNewProduct(detail, image, name, price, priceSale, status, categoryId, createBy);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
	
	@RequestMapping(value = "/load-image/product/{fileName}", method = RequestMethod.GET, produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> downloadMainProductImage(@PathVariable("fileName") String fileName)  {
		try {
			byte[] image = productService.loadMainProductImage(fileName);
			return ResponseEntity.status(HttpStatus.OK).body(image);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Load image failed");
		}
	}
}