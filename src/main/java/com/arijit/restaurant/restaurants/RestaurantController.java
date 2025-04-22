package com.arijit.restaurant.restaurants;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequest request) {
        logger.info("Received request to create restaurant: {}", request);
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be empty");
        }
        Restaurant restaurant = restaurantService.createRestaurant(request);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Restaurant> getMyRestaurant() {
        logger.info("Received request to get user's restaurant");
        Restaurant restaurant = restaurantService.getMyRestaurant();
        return restaurant != null
                ? ResponseEntity.ok(restaurant)
                : ResponseEntity.noContent().build();
    }


    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        logger.info("Received request to get all restaurants");
        return restaurantService.getAllRestaurants();
    }
}