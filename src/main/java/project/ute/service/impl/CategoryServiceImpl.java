package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.dto.CategoryDto;
import project.ute.mapper.CategoryMapper;
import project.ute.model.Category;
import project.ute.respository.CategoryRepository;
import project.ute.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<CategoryDto> getAllCategoryStillInBusiness() {
		List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
		List<Category> categories = new ArrayList<Category>();
		
		categories = categoryRepository.getAllCategoryStillInBusiness();
		for(Category category : categories) {
			categoryDtos.add(CategoryMapper.toCategoryDto(category));
		}
		
		return categoryDtos;
	}
}
