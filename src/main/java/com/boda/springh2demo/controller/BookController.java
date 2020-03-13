package com.boda.springh2demo.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.boda.springh2demo.dto.BookDto;
import com.boda.springh2demo.model.Book;
import com.boda.springh2demo.service.BookService;
import com.boda.springh2demo.util.ObjectMapperUtils;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<Book>> getAll() {
		return ResponseEntity.ok().body(bookService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(bookService.getById(id));
	}

	@PostMapping
	public ResponseEntity<Book> create(@Valid @RequestBody BookDto dto) {
		Book book = bookService.create(ObjectMapperUtils.map(dto, Book.class));
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
		Book book = bookService.update(id, ObjectMapperUtils.map(dto, Book.class));
		return ResponseEntity.ok().body(book);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/author/{author}")
	public ResponseEntity<List<Book>> getByAuthor(@PathVariable String author){
		return ResponseEntity.ok().body(bookService.getByAuthor(author));
	}
}
