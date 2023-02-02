package project.ute.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import project.ute.model.Image;

public interface ImageService {
	public Image uploadImage(MultipartFile file) throws IOException;
	public byte[] downloadImage(String fileName);
}
