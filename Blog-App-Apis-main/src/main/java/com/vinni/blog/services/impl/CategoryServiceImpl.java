package com.vinni.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinni.blog.entities.Category;
import com.vinni.blog.exceptions.ResourceNotFoundException;
import com.vinni.blog.payloads.CategoryDto;
import com.vinni.blog.repositories.CategoryRepo;
import com.vinni.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category cat = this.dtoToCategory(category);
		Category addedCategory = this.categoryRepo.save(cat);
		return this.categoryToDto(addedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		cat.setCategoryTitle(category.getCategoryTitle());
		cat.setCategoryDescription(category.getCategoryDescription());
		Category updatedCategory = this.categoryRepo.save(cat);
		return this.categoryToDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		return this.categoryToDto(cat);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> cats = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = cats.stream().map(cat->this.categoryToDto(cat)).collect(Collectors.toList());
		return catDtos;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		this.categoryRepo.delete(cat);

	}
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
