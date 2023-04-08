package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.ute.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = "/category/get-all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getAllCategoryStillInBusiness(@RequestHeader MultiValueMap<String, String> headers) {

//		headers.forEach((key, value) -> {
//			System.out.println(key + " " + value);
//		});
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategoryStillInBusiness());
	}
}
