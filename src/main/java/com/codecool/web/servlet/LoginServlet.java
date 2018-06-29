package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public final class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            LoginService loginService = new SimpleLoginService(userDao);
            int userId = 0;
            try{
                userId = Integer.parseInt(req.getParameter("id"));
            }
            catch (NumberFormatException ex) {
                req.setAttribute("error", "Only numbers allowed!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
            String userRole = req.getParameter("role");

            User user = loginService.loginUser(userId, userRole);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect("protected/profile");
        }
        catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
