package com.example.tickets.controller;

import com.example.tickets.dto.LocationDto;
import com.example.tickets.model.Location;
import com.example.tickets.service.impl.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/admin/createLocation")
    public ResponseEntity<Location> addLocation(@RequestBody LocationDto locationDto) {
        Location createdLocation = locationService.createLocation(locationDto);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/deleteLocation")
    public ResponseEntity<String> deleteELocation(@RequestParam UUID locationId) {
        locationService.deleteLocation(locationId);
        return ResponseEntity.ok("Location deleted successfully");
    }

    @PutMapping("/admin/updateLocation")
    public ResponseEntity<Location> updateLocation(@RequestParam UUID locationId,
                                                   @RequestBody LocationDto locationDto) {

        Location location = locationService.updateLocation(locationId, locationDto);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/admin/getLocation")
    public ResponseEntity<Location> getLocation(@RequestParam UUID locationId) {
        Location location = locationService.getLocation(locationId);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/admin/getAllLocations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locationList = locationService.getAllLocations();
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteAllLocations")
    public ResponseEntity<String> deleteAllLocations() {
        locationService.deleteAllLocations();
        return ResponseEntity.ok("All locations deleted successfully");
    }

}
