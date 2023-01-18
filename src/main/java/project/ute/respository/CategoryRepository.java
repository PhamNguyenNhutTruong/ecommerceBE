package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
