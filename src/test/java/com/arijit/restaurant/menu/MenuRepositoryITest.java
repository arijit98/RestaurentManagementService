package com.arijit.restaurant.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SqlGroup({
        @Sql(scripts = "classpath:sql/menu_insertions.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class MenuRepositoryITest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void findAll_retrievesAllMenus() {
        List<MenuItem> menus = menuRepository.findAllAvailableMenu(UUID.fromString("123e4567-e89b-12d3-a456-426614174099"));

        // Then
        assertNotNull(menus);
        assertSoftly(s->{
            s.assertThat(menus).satisfiesExactly(
                    menu -> assertEquals("Mock Menu 3", menu.getItemName()));
        });

    }

    @Test
    public void save_savesAndRetrievesMenu() {
        // Given
        MenuItem menu =  MenuItem.builder().itemName("Test Menu").build();

        // When
        UUID savedMenuId = menuRepository.save(menu);

        // Then
        assertNotNull(savedMenuId);
    }

}
