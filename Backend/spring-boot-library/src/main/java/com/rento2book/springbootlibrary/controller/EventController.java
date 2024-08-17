package com.rento2book.springbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rento2book.springbootlibrary.entity.Event;
import com.rento2book.springbootlibrary.service.EventService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("address") String address,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        if (name == null || date == null || address == null || description == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            byte[] imageBytes = (image != null && !image.isEmpty()) ? image.getBytes() : null;
            Event event = new Event(name, date, address, description, imageBytes);
            Event savedEvent = eventService.saveEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("address") String address,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        Event event = eventService.getEventById(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            event.setName(name);
            event.setDate(date);
            event.setAddress(address);
            event.setDescription(description);

            if (image != null && !image.isEmpty()) {
                event.setImage(image.getBytes());
            }

            Event updatedEvent = eventService.saveEvent(event);
            return ResponseEntity.ok(updatedEvent);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long	 id) {
        if (eventService.getEventById(id) != null) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
