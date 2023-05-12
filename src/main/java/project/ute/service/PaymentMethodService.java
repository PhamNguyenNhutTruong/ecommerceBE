package project.ute.service;

import java.util.List;

import project.ute.dto.PaymentMethodDto;
import project.ute.model.PaymentMethod;

public interface PaymentMethodService {
	public List<PaymentMethodDto> getAllPayPaymentMethod();
	public PaymentMethod getPaymentMethodByName(String name);
}
