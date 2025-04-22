package com.arijit.restaurant.restaurants;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.arijit.restaurant.Tables.RESTAURANT;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {
    private final DSLContext dslContext;
    private final Clock clock;

    public Restaurant save(Restaurant restaurant) {
        return Objects.requireNonNull(dslContext.insertInto(RESTAURANT)
                        .set(RESTAURANT.ID, restaurant.getId())
                        .set(RESTAURANT.NAME, restaurant.getName())
                        .set(RESTAURANT.ADDRESS, restaurant.getAddress())
                        .set(RESTAURANT.CONTACT_NUMBER, restaurant.getContactNumber())
                        .set(RESTAURANT.INDIVIDUAL_ID, restaurant.getIndividualId())
                        .set(RESTAURANT.LOGO_URL, restaurant.getLogoUrl())
                        .set(RESTAURANT.CREATED_AT, LocalDateTime.now(clock))
                        .set(RESTAURANT.UPDATED_AT, LocalDateTime.now(clock))
                        .returning()
                        .fetchOne())
                .into(Restaurant.class);
    }

    // single restaurant per user for now
    public Restaurant findByIndividualId(UUID individualId) {
        return dslContext.selectFrom(RESTAURANT)
                .where(RESTAURANT.INDIVIDUAL_ID.eq(individualId))
                .fetchOneInto(Restaurant.class);
    }

    public List<Restaurant> findAll() {
        return dslContext.selectFrom(RESTAURANT)
                .fetch()
                .into(Restaurant.class);
    }
}