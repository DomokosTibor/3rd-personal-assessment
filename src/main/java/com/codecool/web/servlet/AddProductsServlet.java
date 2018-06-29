package com.codecool.web.servlet;

import com.codecool.web.dao.AddProductsDB;
import com.codecool.web.service.AddProductsService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/AddProductsServlet")
public class AddProductsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
//            AddProductsDB db = new AddProductsDB(connection);
//            AddProductsService dbService = new AddProductsService(db);
//            int userId = Integer.parseInt(req.getParameter("user_id"));
            req.setAttribute("user_id",Integer.parseInt(req.getParameter("user_id")));
            req.getRequestDispatcher("add_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            AddProductsDB db = new AddProductsDB(connection);
            AddProductsService dbService = new AddProductsService(db);
            int productId = Integer.parseInt(req.getParameter("product_id"));
            String productName = req.getParameter("product_name");
            int supplierId = Integer.parseInt(req.getParameter("supplier_id"));
            int categoryId = Integer.parseInt(req.getParameter("category_id"));
            String quantityPerUnit = req.getParameter("quantity_per_unit");
            int unitPrice = Integer.parseInt(req.getParameter("unit_price"));
            int unitsInStock = Integer.parseInt(req.getParameter("units_in_stock"));
            int unitsOnOrder = Integer.parseInt(req.getParameter("units_on_order"));
            int reorderLevel = Integer.parseInt(req.getParameter("reorder_level"));
            int discontinued = Integer.parseInt(req.getParameter("discontinued"));
//            req.setAttribute("product",dbService.addProduct(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued));
            dbService.addProduct(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
            req.getRequestDispatcher("add_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
