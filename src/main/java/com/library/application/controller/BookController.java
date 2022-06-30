package com.library.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.application.entity.Book;
import com.library.application.repository.BookRepository;
import com.library.application.service.BookServiceInterface;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookServiceInterface bookServiceInterface;
	
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBookRecords()
	{
		List<Book> bookRecords = bookRepository.findAll();
		return new ResponseEntity<>(bookRecords, HttpStatus.OK);
	}
	
	@GetMapping("id/{bookId}")
	public ResponseEntity<Book> getBookbyId(@PathVariable("bookId") int bookId)
	{
		Book bookRecord = bookRepository.findById(bookId).get();
		return new ResponseEntity<>(bookRecord, HttpStatus.OK);
	}
	
	@GetMapping("rating/{rating}")
	public ResponseEntity<List<Book>> getBookbyRating(@PathVariable("rating") int rating)
	{
		List<Book> bookRecords = bookServiceInterface.findBooksByRating(rating);
		return new ResponseEntity<>(bookRecords, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Book> createBookRecord(@RequestBody Book bookRecord)
	{
		Book newBookRecord = bookRepository.save(bookRecord);
		return new ResponseEntity<>(newBookRecord, HttpStatus.CREATED); 
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Book> updateBookRecord(@RequestBody @Validated Book bookRecord)
	{
		Optional<Book> optionalBook = bookRepository.findById(bookRecord.getId());
		Book book = optionalBook.get();
		book.setId(bookRecord.getId());
		book.setName(bookRecord.getName());
		book.setSummary(bookRecord.getSummary());
		book.setRating(bookRecord.getRating());
		book.setAuthor(bookRecord.getAuthor());
		
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	/** Add @DeleteMapping **/
	
	/** Add @PatchMapping **/
	
	@GetMapping("/test")
	public ResponseEntity<String> testApi()
	{
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

}
