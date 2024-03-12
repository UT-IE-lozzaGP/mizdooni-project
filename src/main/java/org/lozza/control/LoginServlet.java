package org.lozza.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lozza.business.entry.user.User;
import org.lozza.business.services.UserService;
import org.lozza.business.services.exceptions.UserServiceException;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        logger.debug("username: " + username);
        logger.debug("password: " + password);

        try {
            User user = UserService.loginAndGetUser(username, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/success");
        } catch (UserServiceException e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }
    }
}
