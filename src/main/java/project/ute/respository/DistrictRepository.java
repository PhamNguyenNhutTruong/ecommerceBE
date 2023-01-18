package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, String>{

}
