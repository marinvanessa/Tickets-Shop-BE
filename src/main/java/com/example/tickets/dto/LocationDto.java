package com.example.tickets.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class LocationDto {

    private UUID locationId;

    private String city;

    private String address;

    private Boolean isOutdoor;


}
