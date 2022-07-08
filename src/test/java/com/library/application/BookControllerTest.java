package com.library.application;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.library.application.entity.Book;
import com.library.application.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	BookRepository bookRepo;
	
	Book book = new Book(1, "Alchemist", "Self Motivation book", 3, "Paulo Coelho");
	
	@Test
	@DisplayName("GET /book/test")
	public void checkTestAPI() throws Exception
	{
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/book/test");	
		mockMvc.perform(reqBuilder).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("GET /book/all")
	//@Disabled
	public void testGetAllBooksAPI() throws Exception
	{
		List<Book> records = new ArrayList<>();
		records.add(book);
		Mockito.when(bookRepo.findAll()).thenReturn(records);

		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/book/all").contentType(MediaType.APPLICATION_JSON);	
		mockMvc.perform(reqBuilder).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("GET /book/id")
	public void testGetBookByIdAPI() throws Exception
	{	
		Book book2 = new Book(2, "Ponniyin Selvan", "Ancient Tamil Novel", 4, "Kalki");
		Mockito.when(bookRepo.findById(2)).thenReturn(Optional.of(book2));
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/book/id/2").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(reqBuilder).andExpect(status().isOk())
		// Validate the returned fields
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.name", is("Ponniyin Selvan")))
        .andExpect(jsonPath("$.summary", is("Ancient Tamil Novel")))
        .andExpect(jsonPath("$.author", is("Kalki")))  //Case sensitive fails here if author name is "kalki"
        .andExpect(jsonPath("$.rating", is(4)));
	}

}
