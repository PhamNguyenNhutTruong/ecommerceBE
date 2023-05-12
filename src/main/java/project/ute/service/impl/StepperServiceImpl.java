package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.dto.StepperDto;
import project.ute.mapper.StepperMapper;
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

	@Override
	public List<StepperDto> getAllStepper() {
		List<Stepper> steppers = stepperRepository.findAll();
		List<StepperDto> stepperDtos = new ArrayList<>();
		
		for(Stepper stepper : steppers) {
			stepperDtos.add(StepperMapper.toStepperDto(stepper));
		}
		return stepperDtos;
	}

}
