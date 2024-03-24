package org.mizdooni.business.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mizdooni.business.datatype.RatingCollection;
import org.mizdooni.business.datatype.datetime.TimeStamp;
import org.mizdooni.business.datatype.id.RestaurantID;
import org.mizdooni.business.datatype.id.ReviewID;
import org.mizdooni.business.datatype.id.user.CustomerID;
import org.mizdooni.business.datatype.string.Comment;

@Getter
@Setter
public class Review {
    @NonNull
    private final ReviewID id = new ReviewID();

    @NonNull
    private CustomerID customerID;

    @NonNull
    private RestaurantID restaurantID;

    @NonNull
    private Comment comment;

    @NonNull
    private RatingCollection ratingCollection;

    @NonNull
    private TimeStamp timeStamp;

    public Review(@NonNull CustomerID customerID,
                  @NonNull RestaurantID restaurantID,
                  @NonNull Comment comment,
                  @NonNull RatingCollection ratingCollection,
                  @NonNull TimeStamp timeStamp) {
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.comment = comment;
        this.ratingCollection = ratingCollection;
        this.timeStamp = timeStamp;
    }
}
