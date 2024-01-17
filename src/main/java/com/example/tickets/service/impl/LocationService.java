package com.example.tickets.service.impl;

import com.example.tickets.dto.LocationDto;
import com.example.tickets.model.Location;
import com.example.tickets.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setLocationId(locationDto.getLocationId());
        location.setCity(locationDto.getCity());
        location.setAddress(locationDto.getAddress());
        location.setIsOutdoor(locationDto.getIsOutdoor());

        locationRepository.save(location);

        return location;
    }

    public void deleteLocation(UUID locationId) {
        if (locationRepository.findById(locationId).isPresent()) {
            locationRepository.delete(locationRepository.findById(locationId).get());
        }
    }


    public Location updateLocation(UUID locationId, LocationDto locationDto) {
        if (locationRepository.findById(locationId).isPresent()) {
            Location location = locationRepository.findById(locationId).get();
            location.setCity(locationDto.getCity());
            location.setAddress(locationDto.getAddress());
            location.setIsOutdoor(locationDto.getIsOutdoor());
            locationRepository.save(location);
            return location;
        }
        return null;
    }

    public Location getLocation(UUID locationId) {
        if (locationRepository.findById(locationId).isPresent()) {
            return locationRepository.findById(locationId).get();
        }
        return null;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }
}
