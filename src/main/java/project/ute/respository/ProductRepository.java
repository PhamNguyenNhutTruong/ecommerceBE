package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
