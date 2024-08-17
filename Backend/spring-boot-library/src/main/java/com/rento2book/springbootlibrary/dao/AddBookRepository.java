package com.rento2book.springbootlibrary.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.rento2book.springbootlibrary.entity.AddBook;
import com.rento2book.springbootlibrary.entity.Book;


public interface AddBookRepository extends JpaRepository<AddBook, Long>{
	

}
