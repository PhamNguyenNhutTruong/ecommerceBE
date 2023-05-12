package project.ute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.ute.dto.PaymentMethodDto;
import project.ute.model.PaymentMethod;
import project.ute.service.PaymentMethodService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentMethodController {
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@RequestMapping(value = "/payment-method/get-all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> loadAllPaymentMethod() {
		List<PaymentMethodDto> paymentMethodDtos = paymentMethodService.getAllPayPaymentMethod();
		return ResponseEntity.status(HttpStatus.OK).body(paymentMethodDtos);
	}
}
