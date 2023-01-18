package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Website;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, String>{

}
