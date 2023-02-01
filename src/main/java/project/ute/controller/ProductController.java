package project.ute.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.ute.service.ProductService;
import project.ute.service.StorageService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	StorageService storageService;
	
	@RequestMapping(value = "/product/get-product-by-category-id", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllCategoryStillInBusiness(@RequestParam("id") String id, @RequestParam("pageNo")Optional<Integer> pageNo) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByCategoryId(id, pageNo));
	}
	
//	@PostMapping
//	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
//		String uploadImage = storageService.uploadImage(file);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(uploadImage);
//	}
//
//	@GetMapping("/img/{fileName}")
//	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
//		byte[] imageData= storageService.downloadImage(fileName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//	}


	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = storageService.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData= storageService.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
}