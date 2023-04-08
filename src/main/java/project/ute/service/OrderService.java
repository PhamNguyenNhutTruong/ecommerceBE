package project.ute.service;

import java.util.List;

import project.ute.dto.OrderDto;

public interface OrderService {
	public List<OrderDto> getAllOrderByCustomerId(String customerId);
}
