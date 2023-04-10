package project.ute.mapper;

import project.ute.dto.CustomerDto;
import project.ute.model.Customer;
import project.ute.util.ConstantUtils;

public class CustomerMapper {
	public static CustomerDto toGetCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.getId());
		customerDto.setAvatar(customer.getPicture() == null ? null : ConstantUtils.URL__LOAD_IMAGE_FROM_CUSTOMER_TABLE + customer.getId());
		customerDto.setDisplayName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setFamilyName(customer.getFamilyName());
		customerDto.setGivenName(customer.getGivenName());
		customerDto.setPhonenumber(customer.getPhonenumber());
		
		return customerDto;
	}
	
	public static CustomerDto toPutCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.getId());
		customerDto.setAvatar(ConstantUtils.URL__LOAD_IMAGE_FROM_CUSTOMER_TABLE + customer.getId());
		customerDto.setDisplayName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhonenumber(customer.getPhonenumber());
		
		return customerDto;
	}
}
