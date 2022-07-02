package com.library.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.application.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public List<Book> findByRating(int rating);
	
	public List<Book> findByAuthor(String author);

}
