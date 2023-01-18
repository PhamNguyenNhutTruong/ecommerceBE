package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String>{

}
