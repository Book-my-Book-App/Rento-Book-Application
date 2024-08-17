package com.rento2book.springbootlibrary.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.rento2book.springbootlibrary.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	//because of pageable we can add "?title=guru&page=0&size=5" in url
	
	Page<Book> findByTitleContaining(@RequestParam("title") String title , Pageable pageable);
	Page<Book> findByCategory(@RequestParam("category") String category , Pageable pageable);

}

