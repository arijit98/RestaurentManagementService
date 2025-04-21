package com.arijit.restaurant.menu;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;


    @GetMapping("/restaurants/{restaurantId}")
    public List<MenuItem> getMenu(@PathVariable UUID restaurantId) {
        logger.info("Received request to get menu item restaurant: {}", restaurantId);
        return menuService.getMenuItemsByRestaurantId(restaurantId);
    }

    @PostMapping("/restaurants/{restaurantId}")
    @PreAuthorize("hasRole('ADMIN')")
    public MenuItem addMenu(@RequestBody MenuRequest menu, @PathVariable UUID restaurantId) {
        logger.info("Received request to add menu item: {}", menu);
        return menuService.addMenuItem(RequestMapper.mapToMenuItem(menu, restaurantId));
    }


    @GetMapping("/{restaurantId}/{tableId}")
    public String showMenu(@PathVariable Long restaurantId, @PathVariable Integer tableId, Model model) {
        // Add restaurant and table IDs to the model
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("tableId", tableId);

//        // Add restaurant-specific menu items to the model
//        model.addAttribute("menuItems", menuService.getMenuItemsForRestaurant(restaurantId));
//        model.addAttribute("restaurantName", menuService.getRestaurantName(restaurantId));

        // Return the menu view name
        return "menu";
    }
}
