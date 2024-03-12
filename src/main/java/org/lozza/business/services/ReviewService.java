package org.lozza.business.services;

import org.lozza.business.entry.Restaurant;
import org.lozza.business.entry.Review;
import org.lozza.business.collections.ReviewCollection;
import org.lozza.business.entry.user.Client;
import org.lozza.business.services.exceptions.ReviewServiceException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ReviewService {
    private static final ReviewCollection reviews = new ReviewCollection();

    public static void addOrUpdateReview(
            Client client,
            Restaurant restaurant,
            float foodRate,
            float serviceRate,
            float ambianceRate,
            float overallRate,
            String comment
    ) throws ReviewServiceException {
        checkClientIsValid(client);
        checkRestaurantIsValid(restaurant);
        checkRate(foodRate);
        checkRate(serviceRate);
        checkRate(ambianceRate);
        checkRate(overallRate);

        Review newReview = new Review(
                client,
                restaurant,
                foodRate,
                serviceRate,
                ambianceRate,
                overallRate,
                comment,
                LocalDateTime.now()
        );

        getIndexByClientAndRestaurant(client, restaurant)
                .ifPresentOrElse(
                        i -> reviews.set(i, newReview),
                        () -> reviews.add(newReview));
    }

    public static List<Review> getAllReviewsByRestaurant(Restaurant restaurant) {
        return reviews.stream()
                .filter(review -> review.restaurant().equals(restaurant))
                .toList();
    }

    public static float getAverageOverallRate(Restaurant restaurant) {
        return (float) reviews.stream()
                .filter(review -> review.restaurant().equals(restaurant))
                .mapToDouble(Review::overallRate)
                .average()
                .orElse(0D);
    }
    public static float getAverageServiceRate(Restaurant restaurant) {
        return (float) reviews.stream()
                .filter(review -> review.restaurant().equals(restaurant))
                .mapToDouble(Review::serviceRate)
                .average()
                .orElse(0D);
    }

    public static float getAverageFoodRate(Restaurant restaurant) {
        return (float) reviews.stream()
                .filter(review -> review.restaurant().equals(restaurant))
                .mapToDouble(Review::foodRate)
                .average()
                .orElse(0D);
    }

    public static float getAverageAmbianceRate(Restaurant restaurant) {
        return (float) reviews.stream()
                .filter(review -> review.restaurant().equals(restaurant))
                .mapToDouble(Review::ambianceRate)
                .average()
                .orElse(0D);
    }

    protected static OptionalInt getIndexByClientAndRestaurant(Client client, Restaurant restaurant) {
        return IntStream.range(0, reviews.size())
                .filter(i ->
                        reviews.get(i).client().equals(client) && reviews.get(i).restaurant().equals(restaurant)
                ).findFirst();
    }

    protected static void checkClientIsValid(Client client) throws ReviewServiceException {
        if (client == null)
            throw new ReviewServiceException(ReviewServiceException.Type.InvalidClient);
    }

    protected static void checkRestaurantIsValid(Restaurant restaurant) throws ReviewServiceException {
        if (restaurant == null)
            throw new ReviewServiceException(ReviewServiceException.Type.InvalidRestaurant);
    }

    protected static void checkRate(float rate) throws ReviewServiceException {
        if (rate < 0 || rate > 5)
            throw new ReviewServiceException(ReviewServiceException.Type.InvalidRate);
    }
    public static void reset() {
        reviews.clear();
    }
}
