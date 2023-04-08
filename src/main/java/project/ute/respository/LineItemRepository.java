package project.ute.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, String>{
	@Query("SELECT l FROM LineItem l where l.order.id=?1")
	public List<LineItem> getAllLineItemByOrderId(String orderId);
}
