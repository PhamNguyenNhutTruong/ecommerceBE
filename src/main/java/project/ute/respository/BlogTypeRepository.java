package project.ute.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.BlogType;

@Repository
public interface BlogTypeRepository extends JpaRepository<BlogType, String>{

}
