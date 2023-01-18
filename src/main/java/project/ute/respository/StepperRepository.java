package project.ute.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.ute.model.Stepper;

@Repository
public interface StepperRepository extends JpaRepository<Stepper, String>{

}
