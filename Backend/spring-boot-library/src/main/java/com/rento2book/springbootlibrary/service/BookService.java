//package com.rento2book.springbootlibrary.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.rento2book.springbootlibrary.dao.AddBookRepository;
//import com.rento2book.springbootlibrary.entity.AddBook;
//
//@Service
//@Transactional
//public class BookService {
//
//public AddBook updateBook(Long id, AddBook p) {
//	
//	@Autowired
//	AddBookRepository repo;
//		
//	AddBook prod = productRepository.findById(id).orElseThrow();
//		
//		prod.setName(p.getName());
//		prod.setCategory(p.getCategory());
//		prod.setPrice(p.getPrice());
//		
//		return productRepository.save(prod);
//		
//	} 
//}
