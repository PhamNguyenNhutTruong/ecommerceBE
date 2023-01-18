package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Vouncher;

@Repository
public interface VouncheRepository extends JpaRepository<Vouncher, String>{

}
