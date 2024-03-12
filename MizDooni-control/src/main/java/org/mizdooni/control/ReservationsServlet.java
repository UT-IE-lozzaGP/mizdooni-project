package org.mizdooni.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mizdooni.business.entry.Restaurant;
import org.mizdooni.business.entry.Table;
import org.mizdooni.business.entry.user.Client;
import org.mizdooni.business.entry.user.Manager;
import org.mizdooni.business.entry.user.User;
import org.mizdooni.business.services.*;

import java.io.IOException;

@WebServlet("/reservations")
public class ReservationsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ReservationsServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user instanceof Manager) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reservations.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "cancel_reservation" -> handleCancelReservation(request, response);
            case "reserve_table" -> handleReserveTable(request, response);
            default -> {}
        }
    }

    protected void handleCancelReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String clientUsername = request.getParameter("client_username");
            String reservationNumber = request.getParameter("reservation_number");

            logger.debug("client username: " + clientUsername);
            logger.debug("reservation number: " + reservationNumber);

            Client client = UserService.getClientByForce(clientUsername);

            ReservationService.cancelReservation(
                    client,
                    Integer.parseInt(reservationNumber)
            );

            response.sendRedirect(request.getContextPath() + "/reservations");
        } catch(Exception e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }
    }

    protected void handleReserveTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String clientUsername = request.getParameter("client_username");
            String tableNumber = request.getParameter("table_number");
            String restaurantId = request.getParameter("restaurant_id");
            String datetime = request.getParameter("date_time");

            logger.debug("client username: " + clientUsername);
            logger.debug("table number: " + tableNumber);
            logger.debug("restaurant id: " + restaurantId);
            logger.debug("datetime: " + datetime);

            Client client = UserService.getClientByForce(clientUsername);
            Restaurant restaurant = RestaurantService.getRestaurantById(restaurantId);
            Table table = TableService.getTableByForce(
                    restaurant,
                    Integer.parseInt(tableNumber));

            ReservationService.reserveTable(
                    client,
                    table,
                    UtilsService.getDateTime(datetime)
            );

            response.sendRedirect(request.getContextPath() + "/reservations");
        } catch(Exception e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }
    }
}
