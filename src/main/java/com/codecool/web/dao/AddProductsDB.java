package com.codecool.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductsDB {

//    private Connection connection;
//
//    public AddProductsDB(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void addProduct(int productId, String productName, int supplierId, int categoryId, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) throws SQLException {
//        String sql = "INSERT INTO products (product_id, product_name, supplier_id, category_id, quantity_per_unit, unit_price, units_in_stock, units_on_order, reorder_level, discontinued) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, productId);
//            statement.setString(2, productName);
//            statement.setInt(3, supplierId);
//            statement.setInt(4, categoryId);
//            statement.setString(5, quantityPerUnit);
//            statement.setDouble(6, unitPrice);
//            statement.setInt(7, unitsInStock);
//            statement.setInt(8, unitsOnOrder);
//            statement.setInt(9, reorderLevel);
//            statement.setInt(10, discontinued);
//            statement.executeUpdate();
//        }
//    }
}
