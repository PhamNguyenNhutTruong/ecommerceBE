package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

}
