package project.ute.mapper;

import project.ute.dto.ImageDto;
import project.ute.model.Image;

public class ImageMapper {
	private final static String url = "http://localhost:8080/api/image/download/";
	public static ImageDto toImageDto(Image image) {
		ImageDto imageDto = new ImageDto();
		imageDto.setName(image.getName());
		imageDto.setImg(url + image.getName());
		
		return imageDto;
	}
}
