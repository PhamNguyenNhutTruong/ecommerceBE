package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}
