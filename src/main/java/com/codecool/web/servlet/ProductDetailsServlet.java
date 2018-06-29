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

@WebServlet("/protected/ProductDetailsServlet")
public class ProductDetailsServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            SupplierProductsDB db = new SupplierProductsDB(connection);
            SupplierProductsService dbService = new SupplierProductsService(db);
            int userId = Integer.parseInt(req.getParameter("user_id"));

            int productId = 0;
            String productName = req.getParameter("product_name");

            if (req.getParameter("product_id").equals("") && productName.equals("")) {
                req.setAttribute("error", "Please enter a Product ID or a Product Name.");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
            }
            else if (!req.getParameter("product_id").equals("")) {
                try{
                    productId = Integer.parseInt(req.getParameter("product_id"));
                }
                catch (NumberFormatException e) {
                    req.setAttribute("error", "Please use only numbers for Product ID");
                    req.getRequestDispatcher("profile.jsp").forward(req, resp);
                }
            }

            req.setAttribute("product",dbService.productDetails(userId, productId, productName));
            req.getRequestDispatcher("product_details.jsp").forward(req, resp);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
