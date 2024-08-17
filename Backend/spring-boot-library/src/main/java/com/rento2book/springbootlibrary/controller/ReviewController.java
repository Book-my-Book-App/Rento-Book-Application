package com.rento2book.springbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rento2book.springbootlibrary.dao.ReviewRepository;
import com.rento2book.springbootlibrary.entity.Checkout;
import com.rento2book.springbootlibrary.entity.Review;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	
	@Autowired
	ReviewRepository repo;
	
	@PostMapping("add")
    public ResponseEntity<?> addBook(@RequestBody Review book) {
		Review savedBook = repo.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

}
