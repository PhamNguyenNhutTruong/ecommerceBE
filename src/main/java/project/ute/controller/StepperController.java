package project.ute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.ute.dto.StepperDto;
import project.ute.service.StepperService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class StepperController {
	@Autowired
	StepperService stepperService;
	
	@RequestMapping(value = "/stepper/show-all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> showAllStepper() {
		List<StepperDto> stepperDtos = stepperService.getAllStepper();
		return ResponseEntity.status(HttpStatus.OK).body(stepperDtos);
	}
}
