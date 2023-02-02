package project.ute.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.model.Image;
import project.ute.respository.ImageRepository;
import project.ute.service.ImageService;
import project.ute.util.ImageUtils;

@Service
public class ImageServiceImpl implements ImageService{
	@Autowired
	ImageRepository imageRepository;

	@Override
	public Image uploadImage(MultipartFile file) throws IOException {
		Image image = new Image();
		image.setName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setFilePath(ImageUtils.compressImage(file.getBytes()));
		return imageRepository.save(image);
	}

	@Override
	public byte[] downloadImage(String fileName) {
		Optional<Image> imOptional = imageRepository.findByName(fileName);
		return ImageUtils.decompressImage(imOptional.get().getFilePath());
	}

}
