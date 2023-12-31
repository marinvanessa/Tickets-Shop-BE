package com.example.tickets.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;

    private String name;

    private String description;

    private LocalDate date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> ticketList;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
