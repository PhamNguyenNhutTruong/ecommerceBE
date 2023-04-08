package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.MessageDto;
import project.ute.dto.SlideDto;
import project.ute.mapper.SlideMapper;
import project.ute.model.Slide;
import project.ute.respository.SlideRepository;
import project.ute.service.SlideService;
import project.ute.util.ConstantUtils;
import project.ute.util.ImageUtils;
import project.ute.util.RandomNumber;

@Service
public class SlideServiceImpl implements SlideService{
	@Autowired
	SlideRepository slideRepository;

	@Override
	public MessageDto addNewSilde(String detail, MultipartFile[] files) {
		try {
			for(MultipartFile file : files) {
				Slide slide = new Slide();
				slide.setId(this.randomId());
				slide.setName(file.getOriginalFilename());
				slide.setDetail(detail);
				slide.setLink(ImageUtils.compressImage(file.getBytes()));
				
				slideRepository.save(slide);
			}
			return new MessageDto("Add new slide", "Add new slide successfull", ConstantUtils.SUCCESS, null, null, HttpStatus.OK);
		} catch (Exception e) {
			return new MessageDto("Add new slide", e.getMessage() , ConstantUtils.ERROR, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Slide findSlideById(String id) {
		return slideRepository.findSlideById(id);
	}

	@Override
	public String randomId() {
		String slideId =  null;
		do {
			slideId = RandomNumber.randomId("SL");
		} while(this.findSlideById(slideId) != null);
		return slideId;
	}

	@Override
	public byte[] loadSlideImage(String name) {
		Optional<Slide> optionalSlide= this.findSlideByName(name);
		return ImageUtils.decompressImage(optionalSlide.get().getLink());
	}

	@Override
	public Optional<Slide> findSlideByName(String name) {
		return slideRepository.findSlideByName(name);
	}

	@Override
	public List<SlideDto> getAllSlide() {
		List<Slide> slides = slideRepository.findAll();
		List<SlideDto> slideDtos = new ArrayList<>();
		for(Slide slide : slides) {
			slideDtos.add(SlideMapper.toSlideDto(slide));
		}
		return slideDtos;
	}

}
