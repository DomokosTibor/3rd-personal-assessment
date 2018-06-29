package com.codecool.web.servlet;

import com.codecool.web.dao.SupplierProductsDB;
import com.codecool.web.service.SupplierProductsService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/SupplierProductsServlet")
public class SupplierProductsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            SupplierProductsDB db = new SupplierProductsDB(connection);
            SupplierProductsService dbService = new SupplierProductsService(db);
            int userId = Integer.parseInt(req.getParameter("user_id"));
            req.setAttribute("product",dbService.supplierProducts(userId));
            req.getRequestDispatcher("supplier_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            SupplierProductsDB db = new SupplierProductsDB(connection);
            SupplierProductsService dbService = new SupplierProductsService(db);
            String filter = req.getParameter("filter");
            int userId = Integer.parseInt(req.getParameter("user_id"));
            req.setAttribute("product",dbService.supplierProductsFilter(filter, userId));
            req.getRequestDispatcher("supplier_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
