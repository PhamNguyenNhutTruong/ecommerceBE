package project.ute.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.ute.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
	@Query("SELECT o FROM Order o where o.customer.id=?1")
	public List<Order> getAllOrderByCustomerId(String customerId);
}
