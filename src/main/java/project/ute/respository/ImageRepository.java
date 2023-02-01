package project.ute.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
	Optional<Image> findByName(String fileName);
//	Image findByName(String fileName);
}