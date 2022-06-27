package com.library.application.service;

import java.util.List;

import com.library.application.entity.Book;

public interface BookServiceInterface {
	
	List<Book> findBooksByRating(int rating);

}
