package project.ute.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.ute.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService productService;
	
	
	@RequestMapping(value = "/product/get-product-by-category-id", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllCategoryStillInBusiness(@RequestParam("id") String id, @RequestParam("pageNo")Optional<Integer> pageNo) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCategoryId(id, pageNo));
	}
}