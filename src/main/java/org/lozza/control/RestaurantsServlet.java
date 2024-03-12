package org.lozza.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lozza.business.entry.Restaurant;
import org.lozza.business.entry.user.Manager;
import org.lozza.business.entry.user.User;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.ReviewService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;


@WebServlet("/restaurants")
public class RestaurantsServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RestaurantsServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user instanceof Manager) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        request.getSession().setAttribute("restaurants", RestaurantService.getAllRestaurants());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/restaurants.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Restaurant> restaurants;
        try {
            restaurants = ((List<?>) request.getSession().getAttribute("restaurants"))
                    .stream()
                    .map(o -> (Restaurant) o)
                    .toList();
        } catch (ClassCastException e) {
            restaurants = new ArrayList<>();
        }

        String action = request.getParameter("action");
        String search = request.getParameter("search");

        logger.debug("action: " + action);
        logger.debug("search: " + search);

        List<Restaurant> newRestaurants = switch (action) {
            case "search_by_type" ->
                    restaurants.stream()
                            .filter(restaurant -> restaurant.type().equals(search))
                            .toList();
            case "search_by_city" ->
                    restaurants.stream()
                            .filter(restaurant -> restaurant.address().city().equals(search))
                            .toList();
            case "search_by_name" -> {
                Pattern pattern = Pattern.compile(search);
                yield restaurants.stream()
                        .filter(restaurant -> pattern.matcher(restaurant.name()).find())
                        .toList();
            }
            case "sort_by_rate" ->
                restaurants.stream()
                        .sorted(Comparator.comparing(ReviewService::getAverageOverallRate).reversed())
                        .toList();

            case "clear" -> RestaurantService.getAllRestaurants();
            default -> restaurants;
        };

        request.getSession().setAttribute("restaurants", newRestaurants);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/restaurants.jsp");
        requestDispatcher.forward(request, response);
    }
}
