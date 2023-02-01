package project.ute.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.ute.model.Image;
import project.ute.respository.FileDataRepository;
import project.ute.respository.ImageRepository;
import project.ute.util.*;
import project.ute.model.FileData;

@Service
public class StorageService {
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	FileDataRepository fileDataRepository;
	
//	private final String FOLDER_PATH="/Users/javatechie/Desktop/MyFIles/";
//	private final String FOLDER_PATH ="C:/Users/Votro15_130822/Desktop/MyFile/";
//	private final String FOLDER_PATH = "E:/SpingBootTutorial/ecommerceBE/src/main/java/project/ute/img";
	private final String FOLDER_PATH = System.getProperty("user.dir") + "/images/";
	
	public String uploadImage(MultipartFile file) throws IOException {
		makeDirectoryIfNotExist(FOLDER_PATH);
		System.out.println("uploadImage========" + file.getOriginalFilename() + "===" + file.getContentType() + "===");
//		Image img = new Image.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes())).build();
		Image img = new Image(file.getOriginalFilename(), file.getContentType(), ImageUtils.compressImage(file.getBytes()));
		
        Image imageData = imageRepository.save(img);
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }
	
	public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        System.out.println("downloadImage========" + dbImageData.get().getName());
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
//		Image dbImageData = imageRepository.findByName(fileName);
//        System.out.println("downloadImage========" + dbImageData.getName());
//        byte[] images = ImageUtils.decompressImage(dbImageData.getImageData());
//        return images;
    }
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		makeDirectoryIfNotExist(FOLDER_PATH);
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        
//        FileData file = new FileData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .filePath(filePath).build();
//        System.out.println("uploadImageToFileSystem========" + file.getOriginalFilename() + "===" + file.getContentType() + "===");
        FileData fData = new FileData(file.getOriginalFilename(), file.getContentType(), filePath);
        FileData fileData=fileDataRepository.save(fData);

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }
	
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
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