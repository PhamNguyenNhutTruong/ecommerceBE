package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.ute.service.LineItemService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LineItemController {
	@Autowired
	LineItemService lineItemService;
	
	@RequestMapping(value = "/line-item/get-all-by-order-id/{orderId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllCategoryStillInBusiness(@PathVariable("orderId") String orderId) {
		return ResponseEntity.status(HttpStatus.OK).body(lineItemService.getAllLineItemByOrderId(orderId));
	}
}
