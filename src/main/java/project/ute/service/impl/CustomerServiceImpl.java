package project.ute.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import project.ute.dto.MessageDto;
import project.ute.model.Customer;
import project.ute.respository.CustomerRepository;
import project.ute.service.CustomerService;
import project.ute.util.ConstantUtils;
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
		return new MessageDto("Add memeber", "Add new customer successfull", ConstantUtils.SUCCESS, HttpStatus.OK);
	}

	@Override
	public String randomCustomerId() {
		String customerId =  null;
		do {
			customerId = RandomNumber.randomId("CU");
		} while(this.getCustomerById(customerId).size() > 0);
		return customerId;
	}

	@Override
	public List<Customer> getCustomerById(String id) {
		return customerRepository.getCustomerById(id);
	}
	
}
