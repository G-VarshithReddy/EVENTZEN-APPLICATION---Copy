package com.event.eventManagement.service;

import com.event.eventManagement.entity.Booking;
import com.event.eventManagement.entity.User;
import com.event.eventManagement.entity.Venue;
import com.event.eventManagement.repository.BookingRepository;
import com.event.eventManagement.repository.UserRepository;
import com.event.eventManagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VenueRepository venueRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findByIdWithUser(id);
    }

    public Booking createBooking(Long venueId, Long userId, Booking bookingDetails) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (bookingRepository.existsByUser_IdAndVenue_Id(userId, venueId)) {
            throw new RuntimeException("User with ID " + userId + " has already booked this venue.");
        }

        bookingDetails.setVenue(venue);
        bookingDetails.setUser(user);
        bookingDetails.setTotalPrice(venue.getPricePerHour());

        return bookingRepository.save(bookingDetails);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
