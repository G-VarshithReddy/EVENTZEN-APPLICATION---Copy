package com.event.eventManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String venue;
    private String location;

    @Column(name = "ticket_count")
    private int ticketCount;

    @Column(name = "entry_fee")
    private Double fee;

    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Date eventDate;  // Using a single date field instead of startDate and endDate

    private String imageUrl;

    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();
}
