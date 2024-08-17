package com.rento2book.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rento2book.springbootlibrary.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
