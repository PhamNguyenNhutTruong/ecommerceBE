package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.dto.OrderDto;
import project.ute.mapper.OrderMapper;
import project.ute.model.Order;
import project.ute.respository.OrderRepository;
import project.ute.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<OrderDto> getAllOrderByCustomerId(String customerId) {
		List<Order> orders = orderRepository.getAllOrderByCustomerId(customerId);
		List<OrderDto> orderDtos = new ArrayList<>();
		
		for(Order order : orders) {
			orderDtos.add(OrderMapper.toOrderDto(order));
		}
		return orderDtos;
	}

}
