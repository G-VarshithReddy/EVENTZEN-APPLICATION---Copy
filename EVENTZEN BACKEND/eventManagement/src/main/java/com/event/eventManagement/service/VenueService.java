package com.event.eventManagement.service;

import com.event.eventManagement.entity.Venue;
import com.event.eventManagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElse(null);
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Long id, Venue venueDetails) {
        Optional<Venue> optionalVenue = venueRepository.findById(id);
        if (optionalVenue.isPresent()) {
            Venue venue = optionalVenue.get();
            venue.setName(venueDetails.getName());
            venue.setLocation(venueDetails.getLocation());
            venue.setCapacity(venueDetails.getCapacity());
            venue.setAmenities(venueDetails.getAmenities());
            venue.setPricePerHour(venueDetails.getPricePerHour());
            return venueRepository.save(venue);
        }
        return null;
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}
