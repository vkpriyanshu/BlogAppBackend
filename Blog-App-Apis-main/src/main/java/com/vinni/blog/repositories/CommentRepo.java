package com.vinni.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinni.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
