package com.arijit.restaurant.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
        UUID menuId = menuRepository.save(menuItem);
        menuItem.setItemId(menuId);
        return menuItem;
    }

    public List<MenuItem> getMenuDetails(List<UUID> items) {
        return menuRepository.findByIds(items);
    }
}
