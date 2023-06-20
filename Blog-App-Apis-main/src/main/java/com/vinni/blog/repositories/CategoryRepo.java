package com.vinni.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinni.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
