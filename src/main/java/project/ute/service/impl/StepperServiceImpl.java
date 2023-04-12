package project.ute.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.model.Stepper;
import project.ute.respository.StepperRepository;
import project.ute.service.StepperService;

@Service
public class StepperServiceImpl implements StepperService{
	@Autowired
	StepperRepository stepperRepository;

	@Override
	public Stepper getStepperByName(String name) {
		return stepperRepository.getStepperByName(name);
	}

}
