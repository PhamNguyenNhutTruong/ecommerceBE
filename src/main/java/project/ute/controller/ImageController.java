package project.ute.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import project.ute.service.ImageService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ImageController {
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> uploadImage(@RequestParam("images")MultipartFile[] images) throws IOException {
		try {
			for(MultipartFile image : images) {
				imageService.uploadImage(image);
			}
			return ResponseEntity.status(HttpStatus.OK).body("Upload images successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("Upload images falied");
		}
	}
		
//	Lấy tất cả các hình liên quan đến 1 sản phẩm nào đó
	@RequestMapping(value = "/image/get-all-image-by-product-id/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllImageByProductId(@PathVariable("id") String id)  {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.getAllImageByProductId(id));
	}
	
//	Dùng cho phía FE lấy được hình ảnh từ DB
	@RequestMapping(value = "/image/download/{fileName}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<byte[]> downloadImage(@PathVariable("fileName") String fileName)  {
		byte[] image = imageService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
	}
}
