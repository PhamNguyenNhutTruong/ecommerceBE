package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Stepper;

@Repository
public interface StepperRepository extends JpaRepository<Stepper, String>{
	@Query("SELECT s FROM Stepper s WHERE s.name=?1")
	public Stepper getStepperByName(String name);
}
