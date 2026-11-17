
package com.carrental.controller;

import com.carrental.dto.AdminStatsDto;
import com.carrental.dto.BookACarDto;
import com.carrental.dto.CarDto;
import com.carrental.dto.MostBookedCarDto;
import com.carrental.dto.UserDto;
import com.carrental.service.AdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/car")
    public ResponseEntity<?> postCar(
            @jakarta.validation.Valid @RequestBody CarDto carDto) {

        boolean success = adminService.postCar(carDto);

        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        try {
            adminService.deleteCar(id);
            return ResponseEntity.ok("Car deleted successfully");

        } catch (java.util.NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        CarDto carDto = adminService.getCarById(id);

        if (carDto != null) {
            return ResponseEntity.ok(carDto);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(
            @PathVariable Long id,
            @jakarta.validation.Valid @RequestBody CarDto carDto) {

        boolean success = adminService.updateCar(id, carDto);

        if (success) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/car/bookings")
    public ResponseEntity<List<BookACarDto>> getBookings() {
        return ResponseEntity.ok(adminService.getBookings());
    }

    @PutMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(
            @PathVariable Long bookingId,
            @PathVariable String status) {

        if (!status.equalsIgnoreCase("approve")
                && !status.equalsIgnoreCase("reject")) {
            return ResponseEntity.badRequest()
                    .body("Status must be approve or reject");
        }

        try {
            boolean success = adminService.changeBookingStatus(
                    bookingId, status, null);

            if (success) {
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/car/booking/{bookingId}/reject")
    public ResponseEntity<?> rejectBooking(
            @PathVariable Long bookingId,
            @RequestBody String reason) {

        try {
            boolean success = adminService.changeBookingStatus(
                    bookingId, "Reject", reason);

            if (success) {
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/user/{userId}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long userId) {
        boolean success = adminService.blockUser(userId);

        if (success) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsDto> getStats() {
        return ResponseEntity.ok(adminService.getStats());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            adminService.deleteBooking(id);
            return ResponseEntity.ok("Booking deleted successfully");

        } catch (java.util.NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/analytics/most-booked")
    public ResponseEntity<List<MostBookedCarDto>> getMostBookedCars() {
        return ResponseEntity.ok(adminService.getMostBookedCars());
    }
}
