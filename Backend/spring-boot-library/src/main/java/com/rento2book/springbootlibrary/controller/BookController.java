package com.rento2book.springbootlibrary.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rento2book.springbootlibrary.dao.AddBookRepository;
import com.rento2book.springbootlibrary.dao.BookRepository;
import com.rento2book.springbootlibrary.entity.AddBook;
//import com.rento2book.springbootlibrary.entity.AddBook;
import com.rento2book.springbootlibrary.entity.Book;
//import com.rento2book.springbootlibrary.service.BookService;

@RestController
//@RequestMapping("/api/books")
public class BookController {
	
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AddBookRepository repo;
	
//	@Autowired
//	BookService bookservice;
	
	@GetMapping("/booksall")
    public List<Book> getAllNotes() {
        return bookRepository.findAll();
    }
	
	@GetMapping("/booksal")
    public Optional<Book> getABook() {
        return bookRepository.findById(Long.valueOf(2));
    }
	
	@PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
	
	@PostMapping("/addbook")
    public ResponseEntity<?> addBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            @RequestParam("copies") int copies,
            @RequestParam("copiesAvailable") int copiesAvailable,
            @RequestParam("category") String category,
            @RequestParam(value = "img", required = false) MultipartFile img) throws IOException {
        
        AddBook book = new AddBook();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setCopies(copies);
        book.setCopiesAvailable(copiesAvailable);
        book.setCategory(category);
        
        if (img != null && !img.isEmpty()) {
            book.setImg(img.getBytes());
        }
        
        AddBook savedBook = repo.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

}
