package com.boda.springh2demo.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boda.springh2demo.exception.RecordNotFoundException;
import com.boda.springh2demo.model.Book;
import com.boda.springh2demo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAll() {
		// This step is only for to use Java 8 functionality otherwise doesn't require
		List<Book> books = new ArrayList<Book>();
		bookRepository.findAll().forEach(b -> books.add(b));
		return books;
	}

	public Book getById(Long id) {
		// This step is only for to use Java 8 functionality otherwise doesn't require
		List<Book> books = bookRepository.findAll();
		return books.stream().filter(b -> b.getId().equals(id)).findAny()
				.orElseThrow(() -> new RecordNotFoundException("Book not found"));

		// Alternative
		// return bookRepository
		// .findById(id)
		// .orElseThrow(() -> new RecordNotFoundException("Book not found"));
	}

	public Book create(Book book) {
		book.setCreatedAt(Instant.now());
		return bookRepository.save(book);
	}

	public Book update(Long id, Book book) {
		Book bookUpdate = bookRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Book not found"));
		bookUpdate.setTitle(book.getTitle());
		bookUpdate.setAuthor(book.getAuthor());
		bookUpdate.setUpdatedAt(Instant.now());

		return bookRepository.save(bookUpdate);
	}

	public void delete(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Book not found"));
		bookRepository.delete(book);
	}
	
	public List<Book> getByAuthor(String author){
		return bookRepository.findByAuthorOrderByCreatedAtDesc(author);
	}
}
