package project.ute.mapper;

import project.ute.dto.OrderDto;
import project.ute.model.Order;

public class OrderMapper {
	public static OrderDto toOrderDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setDate(order.getOrderAt());
		orderDto.setTotal(order.getTotal());
		orderDto.setAddress(null);
		orderDto.setStatus(null);
		orderDto.setOrderBy(order.getCustomer().getId());
		orderDto.setMethod(order.getPaymentMethod().getName());
		
		return orderDto;
	}
}
