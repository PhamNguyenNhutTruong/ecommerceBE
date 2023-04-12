package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import project.ute.dto.MessageDto;
import project.ute.dto.OrderDto;
import project.ute.service.CheckoutService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CheckoutController {
	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping("/checkout/save")
	public ResponseEntity<?> checkout(HttpServletRequest request, @RequestBody OrderDto orderDto) {
		MessageDto messageDto = checkoutService.checkout(orderDto);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
}
