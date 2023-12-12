package com.example.tickets.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID locationId;

    private String city;

    private String address;

    private Boolean isOutdoor;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Event> eventList;

}
