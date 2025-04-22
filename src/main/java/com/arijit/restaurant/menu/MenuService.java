package com.arijit.restaurant.menu;

import com.arijit.restaurant.Public;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuItem> getMenuItemsByRestaurantId(UUID restaurantId) {
        return menuRepository.findAllAvailableMenu(restaurantId);
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
        UUID menuId = menuRepository.save(menuItem);
        menuItem.setItemId(menuId);
        return menuItem;
    }

    public List<MenuItem> getMenuItemsByIds(Set<UUID> ids) {
        return menuRepository.findAllByIds(ids);
    }

//    public List<MenuItem> getMenuItemsForRestaurant(Long restaurantId) {
//        // Fetch menu items for a specific restaurant
//        return menuRepository.findByRestaurantId(restaurantId);
//    }
//
//    public String getRestaurantName(Long restaurantId) {
//        return restaurantRepository.findById(restaurantId)
//                .map(Restaurant::getName)
//                .orElse("Restaurant");
//    }
}
