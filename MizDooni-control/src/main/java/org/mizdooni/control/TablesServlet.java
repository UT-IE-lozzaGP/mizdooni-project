package org.mizdooni.control;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mizdooni.business.entry.user.Client;
import org.mizdooni.business.entry.user.Manager;
import org.mizdooni.business.entry.user.User;
import org.mizdooni.business.services.RestaurantService;
import org.mizdooni.business.services.TableService;

import java.io.IOException;

@WebServlet("/tables")
public class TablesServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(TablesServlet.class);
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String tableNumber = request.getParameter("table_number");
            String seatsNumber = request.getParameter("seats_number");
            String restaurantName = request.getParameter("restaurant_name");
            User user = (User) request.getSession().getAttribute("user");
            if (user == null || user instanceof Client) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
            Manager manager = (Manager) user;

            logger.debug("username: " + manager.username());
            logger.debug("restaurantName: " + restaurantName);
            logger.debug("tableNumber: " + tableNumber);
            logger.debug("seatsNumber: " + seatsNumber);

            TableService.addTable(
                    RestaurantService.getRestaurantByForce(restaurantName),
                    manager,
                    Integer.parseInt(tableNumber),
                    Integer.parseInt(seatsNumber)
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }
    }
}
