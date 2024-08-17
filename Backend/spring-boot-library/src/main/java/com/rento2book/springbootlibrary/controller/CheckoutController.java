package com.rento2book.springbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rento2book.springbootlibrary.dao.CheckoutRepository;

import com.rento2book.springbootlibrary.entity.Checkout;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	@Autowired
	CheckoutRepository repo;
	
	@PostMapping("add")
    public ResponseEntity<?> addBook(@RequestBody Checkout book) {
		Checkout savedBook = repo.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

}
