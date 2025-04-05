package com.event.eventManagement.repository;

import com.event.eventManagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_Id(Long userId);

    boolean existsByUser_IdAndVenue_Id(Long userId, Long venueId);

    @Query("SELECT b FROM Booking b JOIN FETCH b.user JOIN FETCH b.venue WHERE b.id = :id")
    Booking findByIdWithUser(@Param("id") Long id);
}
