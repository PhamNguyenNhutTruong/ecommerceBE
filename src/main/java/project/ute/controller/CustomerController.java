package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.CustomerDto;
import project.ute.dto.MessageDto;
import project.ute.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/profile/get/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllCategoryStillInBusiness(@PathVariable("id") String id) {
		CustomerDto customerDto = customerService.getCustomerInfomation(id);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@RequestMapping(value = "/profile/update", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateCustomerInformation(@RequestParam("customerId") String customerId, @RequestParam("avatar") MultipartFile avatar,
			@RequestParam("displayName") String displayName,
			@RequestParam("address") String address, @RequestParam("phonenumber") String phonenumber) {
		MessageDto messageDto = customerService.updateCustomerInformation(customerId, avatar, displayName, address, phonenumber);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
	
	@RequestMapping(value = "/profile/load-image/{email}", method = RequestMethod.GET, produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> downloadImage(@PathVariable("email") String email)  {
		try {
			byte[] image = customerService.loadAvatar(email);
			return ResponseEntity.status(HttpStatus.OK).body(image);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Load image failed");
		}
	}
}
