package project.ute.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, String>{
	@Query("SELECT s FROM Size s WHERE s.name=?1 AND s.price=?2")
	public List<Size> getSizeByName(String name, long price);
}
