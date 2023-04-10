package project.ute.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.CustomerDto;
import project.ute.dto.MessageDto;
import project.ute.mapper.CustomerMapper;
import project.ute.model.Customer;
import project.ute.respository.CustomerRepository;
import project.ute.service.CustomerService;
import project.ute.util.ConstantUtils;
import project.ute.util.ImageUtils;
import project.ute.util.RandomNumber;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Optional<Customer> checkCustomerAccount(String email) {
		return customerRepository.checkCustomerAccount(email);
	}

	@Override
	public MessageDto addNewCutomer(Customer customer) {
		customerRepository.save(customer);
		return new MessageDto(null, "Add memeber", "Add new customer successfull", ConstantUtils.SUCCESS, null, null,HttpStatus.OK);
	}

	@Override
	public String randomCustomerId() {
		String customerId =  null;
		do {
			customerId = RandomNumber.randomId("CU");
		} while(this.getCustomerById(customerId) != null);
		return customerId;
	}

	@Override
	public Customer getCustomerById(String id) {
		return customerRepository.getCustomerById(id);
	}

	@Override
	public CustomerDto getCustomerInfomation(String id) {
		return CustomerMapper.toGetCustomerDto(this.getCustomerById(id));
	}

	@Override
	public MessageDto updateCustomerInformation(String id, MultipartFile avatar, String displayName, String address, String phonenumber) {
		try {
			Customer customer = this.getCustomerById(id);
			
			if(customer != null) {
				customer.setPicture(ImageUtils.compressImage(avatar.getBytes()));
				customer.setName(displayName);
				customer.setCustomerAddresses(null);
				customer.setPhonenumber(phonenumber);
				
				customerRepository.save(customer);
				
				CustomerDto customerDto = CustomerMapper.toPutCustomerDto(customer);
				
				return new MessageDto("Update customer infomation", "Update successful", ConstantUtils.SUCCESS , HttpStatus.OK, customerDto);
			}
			return new MessageDto(null, "Update customer infomation", "Update failed", ConstantUtils.ERROR , null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new MessageDto(null, "Update customer infomation", e.getMessage(), ConstantUtils.ERROR , null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.getCustomerByEmail(email);
	}

	@Override
	public byte[] loadAvatar(String id) {
		Customer customer = this.getCustomerById(id);
		return ImageUtils.decompressImage(customer.getPicture());
	}
}
