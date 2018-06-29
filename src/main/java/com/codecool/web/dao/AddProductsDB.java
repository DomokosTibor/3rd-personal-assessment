package com.codecool.web.dao;

import com.codecool.web.model.SupplierProducts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddProductsDB {

    private Connection connection;

    public AddProductsDB(Connection connection) {
        this.connection = connection;
    }

    private SupplierProducts createSupplierProduct(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");

        double unitPrice = resultSet.getDouble("unit_price");
        int unitsInStock = resultSet.getInt("units_in_stock");
        String categoryName = resultSet.getString("category_name");
        return new SupplierProducts(productId, productName, unitPrice, unitsInStock, categoryName);
    }

    public List<SupplierProducts> addProduct(int user_id) throws SQLException {
        String sql = "";
        List<SupplierProducts> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,user_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(createSupplierProduct(resultSet));
                }
            }
        }
        return products;
    }

    public List<SupplierProducts> addProduct(int productId, String productName, int supplierId, int categoryId, int quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) throws SQLException {
        String sql = "INSERT INTO products (product_id, product_name, supplier_id, category_id, quantity_per_unit, unit_price, units_in_stock, units_on_order, reorder_level, discontinued) " +
            "VALUES (?, '?', ?, ?, '?', ?, ?, ?, ?, ?);";
        List<SupplierProducts> newProduct = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setInt(3, supplierId);
            statement.setInt(4, categoryId);
            statement.setInt(5, quantityPerUnit);
            statement.setDouble(6, unitPrice);
            statement.setInt(7, unitsInStock);
            statement.setInt(8, unitsOnOrder);
            statement.setInt(9, reorderLevel);
            statement.setInt(10, discontinued);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    newProduct.add(createSupplierProduct(resultSet));
                }
            }
        }
        return newProduct;
    }

}
