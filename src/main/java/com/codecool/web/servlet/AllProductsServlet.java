package com.codecool.web.servlet;

import com.codecool.web.dao.AllProductsDB;
import com.codecool.web.service.AllProductsService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/AllProductsServlet")
public class AllProductsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            AllProductsDB db = new AllProductsDB(connection);
            AllProductsService dbService = new AllProductsService(db);
            req.setAttribute("product",dbService.allProducts());
            req.getRequestDispatcher("all_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            AllProductsDB db = new AllProductsDB(connection);
            AllProductsService dbService = new AllProductsService(db);
            String filter = req.getParameter("filter");
            req.setAttribute("product",dbService.allProductsFilter(filter));
            req.getRequestDispatcher("all_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
