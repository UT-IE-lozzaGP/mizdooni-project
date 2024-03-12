package org.lozza.control.restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lozza.business.entry.user.Manager;
import org.lozza.business.entry.user.User;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.exceptions.RestaurantServiceException;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

@WebServlet("/restaurants/*")
public class RestaurantServlet extends HttpServlet {

   private static final Logger logger = LogManager.getLogger(RestaurantServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user instanceof Manager) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        try {
            String id = Arrays.stream(request.getPathInfo().split("/"))
                    .filter(part -> !part.isEmpty())
                    .findFirst().orElseThrow();

            logger.debug("id: " + id);

            request.getSession().setAttribute("restaurant", RestaurantService.getRestaurantById(id));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/restaurant.jsp");
            requestDispatcher.forward(request, response);
        } catch (NumberFormatException | RestaurantServiceException | NoSuchElementException e) {
            response.sendError(404);
        }
    }
}
