package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, String>{

}
