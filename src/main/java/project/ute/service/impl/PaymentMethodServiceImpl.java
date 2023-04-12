package project.ute.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.model.PaymentMethod;
import project.ute.respository.PaymentMethodRepository;
import project.ute.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{
	@Autowired
	PaymentMethodRepository paymentMethodRepository;

	@Override
	public PaymentMethod getPaymentMethodByName(String name) {
		return paymentMethodRepository.getPaymentMethodByName(name);
	}

}
