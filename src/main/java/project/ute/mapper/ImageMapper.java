package project.ute.mapper;

import project.ute.dto.ImageDto;
import project.ute.model.Image;
import project.ute.util.ConstantUtils;

public class ImageMapper {
	public static ImageDto toImageDto(Image image) {
		ImageDto imageDto = new ImageDto();
		imageDto.setName(image.getName());
		imageDto.setfilePath(ConstantUtils.URL_LOAD_IMAGE_FROM_IMAGE_TABLE + image.getName());
		
		return imageDto;
	}
}
