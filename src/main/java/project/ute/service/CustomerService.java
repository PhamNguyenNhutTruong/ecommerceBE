package project.ute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.CustomerDto;
import project.ute.dto.MessageDto;
import project.ute.model.Customer;
import project.ute.model.User;

public interface CustomerService {
	public Optional<Customer> checkCustomerAccount(String email);
	public MessageDto addNewCutomer(Customer customer);
	public String randomCustomerId();
	public Customer getCustomerById(String id);
	public Customer getCustomerByEmail(String email);
	public CustomerDto getCustomerInfomation(String id);
	public MessageDto updateCustomerInformation(String customerId, MultipartFile avatar, String displayName, String address, String phonenumber);
	public byte[] loadAvatar(String email);
}
