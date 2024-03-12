package org.lozza.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lozza.business.entry.utils.Address;
import org.lozza.business.services.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegisterServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("address.country");
        String city = request.getParameter("address.city");
        String street = request.getParameter("address.street");

        logger.debug("role: " + role);
        logger.debug("username: " + username);
        logger.debug("email: " + email);
        logger.debug("password: " + password);
        logger.debug("address.country: " + country);
        logger.debug("address.city: " + city);
        logger.debug("address.street: " + street);

        Address address = new Address(country, city, street);

        try {
            UserService.addUser(role, username, password, email, address);
            response.sendRedirect(request.getContextPath() + "/success");
        } catch(Exception e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }
    }
}
