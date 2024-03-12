package org.lozza.business.entry;

import org.lozza.business.entry.user.Client;

import java.time.LocalDateTime;
import java.util.Objects;

public record Review(
        Client client,
        Restaurant restaurant,
        float foodRate,
        float serviceRate,
        float ambianceRate,
        float overallRate,
        String comment,
        LocalDateTime dateTime
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Float.compare(foodRate, review.foodRate) == 0 && Float.compare(serviceRate, review.serviceRate) == 0 && Float.compare(ambianceRate, review.ambianceRate) == 0 && Float.compare(overallRate, review.overallRate) == 0 && Objects.equals(client, review.client) && Objects.equals(restaurant, review.restaurant) && Objects.equals(comment, review.comment) && Objects.equals(dateTime, review.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, restaurant, foodRate, serviceRate, ambianceRate, overallRate, comment, dateTime);
    }
}
