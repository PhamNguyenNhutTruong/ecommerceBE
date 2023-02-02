package project.ute.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.ImageDto;
import project.ute.mapper.ImageMapper;
import project.ute.model.Image;
import project.ute.respository.ImageRepository;
import project.ute.service.ImageService;
import project.ute.util.ImageUtils;

@Service
public class ImageServiceImpl implements ImageService{
	@Autowired
	ImageRepository imageRepository;

	@Override
	public void uploadImage(MultipartFile file) throws IOException {
		Image image = new Image();
		image.setName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setFilePath(ImageUtils.compressImage(file.getBytes()));
		imageRepository.save(image);
	}

	@Override
	public byte[] downloadImage(String fileName) {
		Optional<Image> imOptional = imageRepository.findByName(fileName);
		return ImageUtils.decompressImage(imOptional.get().getFilePath());
	}

	@Override
	public List<ImageDto> getAllImageByProductId(String id) {
		List<Image> images = new ArrayList<Image>();
		List<ImageDto> imageDtos = new ArrayList<ImageDto>();
		images = imageRepository.getAllImageByProductId(id);
		
		for (Image image : images) {
			imageDtos.add(ImageMapper.toImageDto(image));
		}
		return imageDtos;
	}

}
