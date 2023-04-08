package project.ute.mapper;

import project.ute.dto.SlideDto;
import project.ute.model.Slide;
import project.ute.util.ConstantUtils;

public class SlideMapper {
	public static SlideDto toSlideDto(Slide slide) {
		SlideDto slideDto = new SlideDto();
		slideDto.setId(slide.getId());
		slideDto.setDetail(slide.getDetail());
		slideDto.setLink(ConstantUtils.URL__LOAD_IMAGE_FROM_SLIDE_TABLE + slide.getName());
		
		return slideDto;
	}
}
