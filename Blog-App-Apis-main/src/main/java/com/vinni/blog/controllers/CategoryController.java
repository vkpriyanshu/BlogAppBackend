package com.vinni.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinni.blog.payloads.ApiResponse;
import com.vinni.blog.payloads.CategoryDto;
import com.vinni.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
//	POST-create Category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto CategoryDto) {
		CategoryDto createCategoryDto = this.categoryService.createCategory(CategoryDto);
		return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
	}
	
//	PUT-update Category
	@PutMapping("/{CategoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto CategoryDto,@PathVariable("CategoryId") Integer cid) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(CategoryDto, cid);
		return ResponseEntity.ok(updatedCategory);
	}
	
//	DELETE-delete Category
	@DeleteMapping("/{CategoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("CategoryId") Integer cid) {
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
	}
	
//	GET-Category get
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategorys() {
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
	
//	GET-Category get
	@GetMapping("/{CategoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("CategoryId") Integer cid) {
		return ResponseEntity.ok(this.categoryService.getCategoryById(cid));
	}

}
