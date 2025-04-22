package com.arijit.restaurant.restaurants;


import com.arijit.restaurant.user.User;
import com.arijit.restaurant.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public Restaurant createRestaurant(RestaurantRequest request) {

        // Access security context to get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Find user to get individualId
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        UUID individualId = user.getIndividualId();

        Restaurant restaurant = Restaurant.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .address(request.getAddress())
                .contactNumber(request.getContactNumber())
                .individualId(individualId)
                .logoUrl(request.getLogoUrl())
                .build();

        return restaurantRepository.save(restaurant);
    }

    public Restaurant getMyRestaurant() {
        // Access security context to get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Find user to get individualId
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        UUID individualId = user.getIndividualId();

        return restaurantRepository.findByIndividualId(individualId);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}