package com.event.eventManagement.service.seviceImpl;

import com.event.eventManagement.entity.Event;
import com.event.eventManagement.repository.EventRepository;
import com.event.eventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> getAllEvent(Pageable page) {
        return eventRepository.findAll(page);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
    }

    @Override
    public Event updateEvent(Event event, Long id) {
        Event e = getEventById(id);

        e.setName(event.getName() != null ? event.getName() : e.getName());
        e.setCategory(event.getCategory() != null ? event.getCategory() : e.getCategory());
        e.setVenue(event.getVenue() != null ? event.getVenue() : e.getVenue());
        e.setLocation(event.getLocation() != null ? event.getLocation() : e.getLocation());
        e.setTicketCount(event.getTicketCount() != 0 ? event.getTicketCount() : e.getTicketCount());
        e.setFee(event.getFee() != null ? event.getFee() : e.getFee());
        e.setEventDate(event.getEventDate() != null ? event.getEventDate() : e.getEventDate());
        e.setImageUrl(event.getImageUrl() != null ? event.getImageUrl() : e.getImageUrl());

        return eventRepository.save(e);
    }

    @Override
    public void deleteEvent(Long id) {
        Event e = getEventById(id);
        eventRepository.delete(e);
    }
}
