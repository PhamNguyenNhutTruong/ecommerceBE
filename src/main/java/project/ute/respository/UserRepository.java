package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
