package project.ute.mapper;

import project.ute.dto.StepperDto;
import project.ute.model.Stepper;

public class StepperMapper {
	public static StepperDto toStepperDto(Stepper stepper) {
		StepperDto stepperDto = new StepperDto();
		stepperDto.setId(stepper.getId());
		stepperDto.setName(stepper.getName());
		
		return stepperDto;
	}
}
