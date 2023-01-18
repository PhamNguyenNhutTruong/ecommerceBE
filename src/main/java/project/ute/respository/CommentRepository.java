package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>{

}
