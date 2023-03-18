package project.ute.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.ute.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	@Query("SELECT u FROM User u WHERE u.id=?1")
	public List<User> getAllUsersById(String id);
	
	Optional<User> getByEmail(String email);
}
