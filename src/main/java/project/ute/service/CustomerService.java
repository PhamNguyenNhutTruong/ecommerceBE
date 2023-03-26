package project.ute.service;

import java.util.List;
import java.util.Optional;

import project.ute.dto.MessageDto;
import project.ute.model.Customer;

public interface CustomerService {
	public Optional<Customer> checkCustomerAccount(String email);
	public MessageDto addNewCutomer(Customer customer);
	public String randomCustomerId();
	public List<Customer> getCustomerById(String id);
}
