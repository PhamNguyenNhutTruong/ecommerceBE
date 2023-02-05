package project.ute.service;

import java.util.List;

import project.ute.dto.CategoryDto;
import project.ute.model.Category;


public interface CategoryService {
	public List<CategoryDto> getAllCategoryStillInBusiness();
}
