package com.event.eventManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private int capacity;
    private String amenities;
    private double pricePerHour;
    private String imageUrl;  // Added for venue image
    private String description; // Added for venue description
}
