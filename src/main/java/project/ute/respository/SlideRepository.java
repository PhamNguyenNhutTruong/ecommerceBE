package project.ute.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Slide;

@Repository
public interface SlideRepository extends JpaRepository<Slide, String>{
	@Query("SELECT s FROM Slide s WHERE s.id=?1")
	public Slide findSlideById(String id);
	
	@Query("SELECT s FROM Slide s WHERE s.name=?1")
	public Optional<Slide > findSlideByName(String name);
}
