package com.library.application;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.library.application.controller.BookController;
import com.library.application.entity.Book;
import com.library.application.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private BookRepository bookRepo;
	
	@InjectMocks
	private BookController bookController;
	
	Book book = new Book(1, "Alchemist", "Self Motivation book", 3);
	Book book2 = new Book(2, "Five Point Someone", "About 3 idiots", 3);
	
	@BeforeEach
	public void setUp()
	{
		System.out.println("Start Test...");
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	@Test
	public void getAllRecordsSuccess() throws Exception
	{
		List<Book> records = new ArrayList<>();
		records.add(book);
		records.add(book2);
		
		Mockito.when(bookRepo.findAll()).thenReturn(records);
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/book").contentType(MediaType.APPLICATION_JSON);
			
		mockMvc.perform(reqBuilder).andExpect(status().isOk());
	}
	 

}
