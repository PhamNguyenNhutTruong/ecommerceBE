package project.ute.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.PaymentMethod;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
//	@Query("SELECT p FROM PaymentMethod p")
//	public List<PaymentMethod> getAllPaymentMethod();
	
	@Query("SELECT p FROM PaymentMethod p where p.name=?1")
	public PaymentMethod getPaymentMethodByName(String name);
}
