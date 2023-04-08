package project.ute.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Customer;
import project.ute.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	@Query("SELECT c FROM Customer c WHERE c.email= ?1")
	public Optional<Customer> checkCustomerAccount(String email);
	
	@Query("SELECT c FROM Customer c WHERE c.id=?1")
	public Customer getCustomerById(String id);
	
	@Query("SELECT c FROM Customer c WHERE c.email=?1")
	public Customer getCustomerByEmail(String email);
}
