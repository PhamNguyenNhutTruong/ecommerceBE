package project.ute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.MessageDto;
import project.ute.dto.SlideDto;
import project.ute.model.Slide;

public interface SlideService {
	public MessageDto addNewSilde(String detail, MultipartFile[] files);
	public Slide findSlideById(String id);
	public String randomId();
	public byte[] loadSlideImage(String name);
	public Optional<Slide > findSlideByName(String name);
	public List<SlideDto> getAllSlide();
}
