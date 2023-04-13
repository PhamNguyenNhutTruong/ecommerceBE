package project.ute.mapper;

import project.ute.dto.PaymentMethodDto;
import project.ute.model.PaymentMethod;

public class PaymentMethodMapper {
	public static PaymentMethodDto toPaymentMethodDto(PaymentMethod paymentMethod) {
		PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
		paymentMethodDto.setId(paymentMethod.getId());
		paymentMethodDto.setAvailable(paymentMethod.getIsAvailable());
		paymentMethodDto.setName(paymentMethod.getName());
		
		return paymentMethodDto;
	}
}
