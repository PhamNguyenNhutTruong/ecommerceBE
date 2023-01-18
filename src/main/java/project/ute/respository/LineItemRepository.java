package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, String>{

}
