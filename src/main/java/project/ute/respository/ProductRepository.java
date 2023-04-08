package project.ute.respository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT p FROM Product p WHERE p.id=?1")
	public Product findProductById(String id);
	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1 and p.status = true")
	public Page<Product> getProductByCategoryId(String id, PageRequest pageable);
	
	@Query("SELECT p FROM Product p WHERE p.name = ?1")
	Optional<Product> findByName(String name);
}
