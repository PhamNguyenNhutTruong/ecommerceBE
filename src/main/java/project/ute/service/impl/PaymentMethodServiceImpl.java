package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.dto.PaymentMethodDto;
import project.ute.mapper.PaymentMethodMapper;
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

	@Override
	public List<PaymentMethodDto> getAllPayPaymentMethod() {
		List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
		List<PaymentMethodDto> paymentMethodDtos = new ArrayList<>();
		
		for (PaymentMethod paymentMethod : paymentMethods) {
			paymentMethodDtos.add(PaymentMethodMapper.toPaymentMethodDto(paymentMethod));
		}
		return paymentMethodDtos;
	}

}
