package com.arijit.restaurant.menu;

import com.arijit.restaurant.tables.records.MenuRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.arijit.restaurant.Tables.MENU;

@Repository
@RequiredArgsConstructor
public class MenuRepository {
    private final DSLContext dslContext;
    private final Clock clock;

    public List<MenuItem> findAll() {
        return dslContext.selectFrom(MENU)
                .where(MENU.STATUS.eq("AVAILABLE"))
                .stream()
                .map(this::mapToMenuItems)
                .toList();
    }

    public UUID save(MenuItem menuItem) {
        return dslContext.insertInto(MENU).set(MENU.ID, UUID.randomUUID())
                .set(MENU.STATUS, "AVAILABLE")
                .set(MENU.PRICE, menuItem.getItemPrice())
                .set(MENU.DESCRIPTION, menuItem.getItemDescription())
                .set(MENU.ITEM_NAME, menuItem.getItemName())
                .set(MENU.CREATED_AT, LocalDateTime.now(clock))
                .set(MENU.UPDATED_AT,  LocalDateTime.now(clock))
                .returning(MENU.ID)
                .fetchOne()
                .getValue(MENU.ID);
    }

    private MenuItem mapToMenuItems(MenuRecord menuRecord) {
        return MenuItem.builder().itemId(
                menuRecord.getId()).itemName(
                menuRecord.getItemName()).itemDescription(
                menuRecord.getDescription()).itemPrice(
                menuRecord.getPrice()).build();
    }
}
