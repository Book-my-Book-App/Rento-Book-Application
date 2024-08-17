package com.rento2book.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rento2book.springbootlibrary.entity.Book;
import com.rento2book.springbootlibrary.entity.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long>{

}
