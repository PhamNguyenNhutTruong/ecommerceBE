package project.ute.service;

import java.util.List;

import project.ute.dto.OrderDto;
import project.ute.model.Order;

public interface OrderService {
	public List<OrderDto> getAllOrderByCustomerId(String customerId);
	public void addNewOrder(Order order);
}
