package project.ute.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.MessageDto;
import project.ute.dto.SlideDto;
import project.ute.service.SlideService;
import project.ute.util.ConstantUtils;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SlideController {
	@Autowired
	SlideService slideService;
	
	@RequestMapping(value = "/slide/add-new-slide", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addNewSlide(@RequestParam("detail")String detail, @RequestParam("images")MultipartFile[] images) throws IOException {	
		MessageDto messageDto = new MessageDto("Add new slide", "Add new slide failed", ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			messageDto = slideService.addNewSilde(detail, images);
			return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
		} catch (Exception e) {
			messageDto.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/slide/get-all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getAllSlide() {
		List<SlideDto> slideDtos = slideService.getAllSlide();
		return ResponseEntity.status(HttpStatus.OK).body(slideDtos);
	}
	
	@RequestMapping(value = "/load-image/slide/{fileName}", method = RequestMethod.GET, produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> downloadMainProductImage(@PathVariable("fileName") String fileName)  {
		try {
			byte[] image = slideService.loadSlideImage(fileName);
			return ResponseEntity.status(HttpStatus.OK).body(image);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Load image failed");
		}
	}
}
