package project.ute.service;

import java.util.List;

import project.ute.dto.StepperDto;
import project.ute.model.Stepper;

public interface StepperService {
	public List<StepperDto> getAllStepper(); 
	public Stepper getStepperByName(String name);
}
