package com.example.jponline_backend.controller;

import com.example.jponline_backend.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jponline_backend.services.EventServices;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200") // Permite acesso ao frontend Angular
public class EventController {

    @Autowired
    private EventServices eventServices;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventServices.getAllEvents();
    }

    @GetMapping("/{date}")
    public List<Event> getEventsByDate(@PathVariable String date) {
        return eventServices.getEventsByDate(LocalDate.parse(date));
    }


}
