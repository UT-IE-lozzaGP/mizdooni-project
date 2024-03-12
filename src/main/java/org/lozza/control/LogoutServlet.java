package org.lozza.control;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.getSession().setAttribute("user", null);
        response.sendRedirect(request.getContextPath() + "/success");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doPost(request, response);
    }
}
