package com.event.eventManagement.service.seviceImpl;
import com.event.eventManagement.entity.Event;
import com.event.eventManagement.entity.User;
import com.event.eventManagement.repository.UserRepository;
import com.event.eventManagement.service.EventService;
import com.event.eventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventService eventService;

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public User createUser(User u) {
        if (userRepository.existsByEmail(u.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    @Transactional
    public Set<Event> updateEventByUserId(Long eventId) {
        User u = getLoggedInUser();  // Get logged-in user
        Event e = eventService.getEventById(eventId);  // Fetch event by ID
        u.getEvents().add(e);  // Add event to user
        userRepository.save(u);  // Save updated user
        return u.getEvents();  // Return updated events list
    }

    @Override
    public Set<Event> getAllUserEvents() {
        return getLoggedInUser().getEvents();
    }

    @Override
    @Transactional
    public void deleteEventByUserById(Long eventId) {
        User u = getLoggedInUser();
        Set<Event> events = u.getEvents().stream()
                .filter(e -> !e.getId().equals(eventId)) // Use .equals() for Long comparison
                .collect(Collectors.toSet());
        u.setEvents(events);
        userRepository.save(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
