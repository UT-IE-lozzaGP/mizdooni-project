package org.mizdooni.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mizdooni.business.entry.user.Client;
import org.mizdooni.business.entry.user.Manager;

import java.io.IOException;

@WebServlet("/")
public class RootServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RootServlet.class);

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {
            Object user = request.getSession().getAttribute("user");

            if (user == null) {
                logger.debug("redirecting to login page");
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            if (user instanceof Client) {
                logger.debug("dispatching client home page");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/client_home.jsp");
                requestDispatcher.forward(request, response);
                return;
            } else if (user instanceof Manager) {
                logger.debug("dispatching manager home page");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manager_home.jsp");
                requestDispatcher.forward(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error" +
                    "?message=" + e.getMessage());
        }
    }
}
