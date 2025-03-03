package com.arijit.restaurant.menu;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;


    @GetMapping
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<MenuItem> getMenu() {
        logger.info("Received request to get menu item:");
        return menuService.getAllMenuItems();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MenuItem addMenu(@RequestBody MenuRequest menu) {
        logger.info("Received request to add menu item: {}", menu);
        return menuService.addMenuItem(RequestMapper.mapToMenuItem(menu));
    }
}
