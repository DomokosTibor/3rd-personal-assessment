package com.codecool.web.servlet;

//import com.codecool.web.dao.AddProductsDB;
import com.codecool.web.dao.SupplierProductsDB;
//import com.codecool.web.service.AddProductsService;
import com.codecool.web.service.SupplierProductsService;
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
            SupplierProductsDB db = new SupplierProductsDB(connection);
            SupplierProductsService dbService = new SupplierProductsService(db);

            if (!req.getParameter("product_id").equals("") &&
                !req.getParameter("product_name").equals("") &&
                !req.getParameter("discontinued").equals("")) {

                try {
                    double unitPrice = 0;
                    int unitsInStock = 0;
                    int unitsOnOrder = 0;
                    int reorderLevel = 0;

                    /* required fields */
                    int categoryId = Integer.parseInt(req.getParameter("category_id"));
                    int productId = Integer.parseInt(req.getParameter("product_id"));
                    int discontinued = Integer.parseInt(req.getParameter("discontinued"));
                    /* NOT required fields */
                    if (!req.getParameter("unit_price").equals("")) {
                        unitPrice = Integer.parseInt(req.getParameter("unit_price"));
                    }
                    if (!req.getParameter("units_in_stock").equals("")) {
                        unitsInStock = Integer.parseInt(req.getParameter("units_in_stock"));
                    }
                    if (!req.getParameter("units_on_order").equals("")) {
                        unitsOnOrder = Integer.parseInt(req.getParameter("units_on_order"));
                    }
                    if (!req.getParameter("reorder_level").equals("")) {
                        reorderLevel = Integer.parseInt(req.getParameter("reorder_level"));
                    }

                    int supplierId = Integer.parseInt(req.getParameter("supplier_id"));
                    String productName = req.getParameter("product_name");
                    String quantityPerUnit = req.getParameter("quantity_per_unit");

                    req.setAttribute("successful", "Product added successfully");

                    req.setAttribute("user_id", Integer.parseInt(req.getParameter("supplier_id")));
                    dbService.addProduct(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
                    req.getRequestDispatcher("add_products.jsp").forward(req, resp);
                }
                catch (NumberFormatException ex) {
                    req.setAttribute("user_id", Integer.parseInt(req.getParameter("supplier_id")));
                    req.setAttribute("error", "Please use only numbers for: Product ID, Unit price, Units in stock, Units on order, Reorder Level, Discontinued");
                    req.getRequestDispatcher("add_products.jsp").forward(req, resp);
                }
            }
            else {
                req.setAttribute("user_id", Integer.parseInt(req.getParameter("supplier_id")));
                req.setAttribute("error", "Required fields missing.");
                req.getRequestDispatcher("add_products.jsp").forward(req, resp);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
