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
            int productId = 0;
            int supplierId = 0;
            int categoryId = 0;
            int unitPrice = 0;
            int unitsInStock = 0;
            int unitsOnOrder = 0;
            int reorderLevel = 0;
            int discontinued = 0;
            try{
                productId = Integer.parseInt(req.getParameter("product_id"));
                supplierId = Integer.parseInt(req.getParameter("supplier_id"));
                categoryId = Integer.parseInt(req.getParameter("category_id"));
                unitPrice = Integer.parseInt(req.getParameter("unit_price"));
                unitsInStock = Integer.parseInt(req.getParameter("units_in_stock"));
                unitsOnOrder = Integer.parseInt(req.getParameter("units_on_order"));
                reorderLevel = Integer.parseInt(req.getParameter("reorder_level"));
                discontinued = Integer.parseInt(req.getParameter("discontinued"));
            }
            catch (NumberFormatException ex) {
                req.setAttribute("error", "Please use only numbers for: Product ID, Unit price, Units in stock, Units on order, Reorder Level, Discontinued");
                req.getRequestDispatcher("add_products.jsp").forward(req, resp);
            }
            String productName = req.getParameter("product_name");
            String quantityPerUnit = req.getParameter("quantity_per_unit");
            dbService.addProduct(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
            req.getRequestDispatcher("add_products.jsp").forward(req, resp);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
