package project.ute.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.model.Image;
import project.ute.respository.ImageRepository;

@Service
public class StorageService {
	@Autowired
	ImageRepository imageRepository;
	
	private final String FOLDER_PATH = System.getProperty("user.dir") + "/images/";
	
//	public String uploadImage(MultipartFile file) throws IOException {
//		makeDirectoryIfNotExist(FOLDER_PATH);
//		Image img = new Image(file.getOriginalFilename(), file.getContentType(), ImageUtils.compressImage(file.getBytes()));
//		
//        Image imageData = imageRepository.save(img);
//        if (imageData != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
//        }
//        return null;
//    }
//	
//	public byte[] downloadImage(String fileName) {
//        Optional<Image> dbImageData = imageRepository.findByName(fileName);
//        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
//        return images;
//    }
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		makeDirectoryIfNotExist(FOLDER_PATH);
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        
        Image fData = new Image(file.getOriginalFilename(), file.getContentType(), filePath);
        Image fileData=imageRepository.save(fData);

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> fileData = imageRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
	
	private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}