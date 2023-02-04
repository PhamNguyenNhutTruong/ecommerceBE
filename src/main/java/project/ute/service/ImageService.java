package project.ute.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import project.ute.dto.ImageDto;
import project.ute.model.Image;

public interface ImageService {
	public void uploadImage(MultipartFile file, String productId) throws IOException;
	public byte[] downloadImage(String fileName);
	public List<ImageDto> getAllImageByProductId(String id);
}
