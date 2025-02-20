package com.arijit.restaurant.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public List<MenuItem> getMenu() {
        return menuService.getAllMenuItems();
    }

    @PostMapping
    public MenuItem addMenu(@RequestBody MenuRequest menu) {
        return menuService.addMenuItem(RequestMapper.mapToMenuItem(menu));
    }
}
