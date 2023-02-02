package project.ute.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	 Optional<Image> findByName(String fileName);
	 
	 @Query("SELECT i FROM Image i WHERE i.product.id =?1")
	 List<Image> getAllImageByProductId(String id);
}
