package com.library.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.application.entity.Book;
import com.library.application.repository.BookRepository;

@Service
public class BookService implements BookServiceInterface{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findBooksByRating(int rating) {
		List<Book> bookRecordsByRating = bookRepository.findByRating(rating);
		return bookRecordsByRating;
	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		List<Book> bookRecordsByAuthor = bookRepository.findByAuthor(author);
		return bookRecordsByAuthor;
	}

}
