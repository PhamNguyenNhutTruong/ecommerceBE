package project.ute.mapper;

import project.ute.dto.CategoryDto;
import project.ute.model.Category;

public class CategoryMapper {
	public static CategoryDto toCategoryDto (Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		
		return categoryDto;
	}
}
