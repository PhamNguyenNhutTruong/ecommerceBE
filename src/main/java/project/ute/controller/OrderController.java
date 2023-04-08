package project.ute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.ute.dto.OrderDto;
import project.ute.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/order/get-all-by-customer-id/{customerId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllOrderByCustomerId(@PathVariable("customerId") String customerId) {
		List<OrderDto> orderDtos = orderService.getAllOrderByCustomerId(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(orderDtos);
	}
}
