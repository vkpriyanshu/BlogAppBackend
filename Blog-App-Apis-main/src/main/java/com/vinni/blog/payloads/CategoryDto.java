package com.vinni.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 3,message = "Title must be of min. 3 characters !!")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10,message = "Description must be of min. 10 characters !!")
	private String categoryDescription;
}
