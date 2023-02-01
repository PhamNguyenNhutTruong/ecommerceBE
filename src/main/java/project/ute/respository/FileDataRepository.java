package project.ute.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long>{
	 Optional<FileData> findByName(String fileName);
}
