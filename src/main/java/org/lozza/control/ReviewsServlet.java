package org.lozza.control;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lozza.business.entry.Restaurant;
import org.lozza.business.entry.user.Client;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.ReviewService;
import org.lozza.business.services.UserService;

import java.io.IOException;

@WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ReviewsServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String clientUsername = request.getParameter("client_username");
            String restaurantName = request.getParameter("restaurant_name");
            String foodRate = request.getParameter("food_rate");
            String serviceRate = request.getParameter("service_rate");
            String ambianceRate = request.getParameter("ambiance_rate");
            String overallRate = request.getParameter("overall_rate");
            String comment = request.getParameter("comment");

            logger.debug("client username " + clientUsername);
            logger.debug("restaurant name " + restaurantName);
            logger.debug("food rate " + foodRate);
            logger.debug("service rate " + serviceRate);
            logger.debug("ambiance rate " + ambianceRate);
            logger.debug("overall rate " + overallRate);

            Client user = UserService.getClientByForce(clientUsername);
            Restaurant restaurant = RestaurantService.getRestaurantByForce(restaurantName);
            ReviewService.addOrUpdateReview(
                    user,
                    restaurant,
                    Float.parseFloat(foodRate),
                    Float.parseFloat(serviceRate),
                    Float.parseFloat(ambianceRate),
                    Float.parseFloat(overallRate),
                    comment
            );

            response.sendRedirect(request.getContextPath() + "/restaurants/" + restaurant.id());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }

    }
}
