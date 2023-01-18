package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String>{

}
