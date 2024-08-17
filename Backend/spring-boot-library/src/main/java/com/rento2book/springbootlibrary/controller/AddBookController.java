package com.rento2book.springbootlibrary.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rento2book.springbootlibrary.dao.AddBookRepository;
import com.rento2book.springbootlibrary.entity.AddBook;

@RestController
@RequestMapping("/api/addbooks")
public class AddBookController {
	
	@Autowired
	AddBookRepository repo;
	
//	@Autowired
//	BookService bookservice;
	
	
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
	
//	@PutMapping("/updatebook/{id}")
//	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody AddBook product) {
//		
//		
//		return ResponseEntity.ok().body(productService.updateProduct(id, product));
//		
//		
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProductByCategory(@PathVariable Long id){
		
		repo.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	

}
