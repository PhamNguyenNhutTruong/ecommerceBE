package project.ute.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import project.ute.dto.LineItemDto;
import project.ute.dto.MessageDto;
import project.ute.dto.OrderDto;
import project.ute.model.LineItem;
import project.ute.model.Order;
import project.ute.service.CheckoutService;
import project.ute.service.CustomerService;
import project.ute.service.LineItemService;
import project.ute.service.OrderService;
import project.ute.service.PaymentMethodService;
import project.ute.service.ProductService;
import project.ute.service.StepperService;
import project.ute.util.ConstantUtils;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	@Autowired
	OrderService orderService;
	
	@Autowired
	LineItemService lineItemService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Autowired
	StepperService stepperService;
	
	@Autowired
	ProductService productService;

	@Override
	public MessageDto checkout(OrderDto orderDto) {
		try {
			Order order = new Order();
			order.setId(orderDto.getId()); 
			order.setOrderAt(orderDto.getDate());
			order.setTotal(orderDto.getTotal());
			order.setCustomer(customerService.getCustomerById(orderDto.getOrderBy()));
			order.setPaymentMethod(paymentMethodService.getPaymentMethodByName(orderDto.getMethod()));
			order.setStepper(stepperService.getStepperByName(orderDto.getStatus()));
			orderService.addNewOrder(order);
			
			List<LineItemDto> lineItemDtos = orderDto.getLineItems();
			for(LineItemDto lineItemDto : lineItemDtos) {
				LineItem lineItem = new LineItem();
				lineItem.setId(lineItemDto.getId());
				lineItem.setOrder(order);
				lineItem.setProduct(productService.findProductById(lineItemDto.getProductId()));
				lineItem.setAmount(lineItemDto.getAmount());
				lineItem.setQuantity(lineItemDto.getQuantity());
				lineItemService.addNewLineItem(lineItem);
//				System.out.println("===== ProductId : " + lineItemDto.getProductId());
			}
//			System.out.println("===== Order id: " + orderDto.getId());
//			System.out.println("===== Customer id: " + orderDto.getOrderBy());
//			System.out.println("===== PaymentMethod : " + orderDto.getMethod());
//			System.out.println("===== Stepper : " + orderDto.getStatus());
			return new MessageDto("Checkout", "Checkout successfull", ConstantUtils.SUCCESS, null, null, HttpStatus.OK); 
		} catch (Exception e) {
			return new MessageDto("Checkout", e.getMessage(), ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}

}
