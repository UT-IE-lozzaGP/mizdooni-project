package org.lozza.business.services;

import org.lozza.business.ds.Restaurant;
import org.lozza.business.ds.Review;
import org.lozza.business.ds.collections.ReviewCollection;
import org.lozza.business.ds.user.Client;
import org.lozza.business.services.exceptions.ReviewServiceException;

import java.time.LocalDateTime;

public class ReviewService {
    private static final ReviewCollection reviews = new ReviewCollection();

    public static void addReview(
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

        reviews.add(new Review(
                client,
                restaurant,
                foodRate,
                serviceRate,
                ambianceRate,
                overallRate,
                comment,
                LocalDateTime.now()
        ));
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
