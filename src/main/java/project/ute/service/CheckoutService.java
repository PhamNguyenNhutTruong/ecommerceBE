package project.ute.service;

import project.ute.dto.MessageDto;
import project.ute.dto.OrderDto;

public interface CheckoutService {
	public  MessageDto checkout(OrderDto orderDto);
}
