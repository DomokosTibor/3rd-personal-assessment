package com.codecool.web.dao;

import com.codecool.web.model.SupplierProducts;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierProductsDB {

    private Connection connection;

    public SupplierProductsDB(Connection connection) {
        this.connection = connection;
    }

    private SupplierProducts createSupplierProducts(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        double unitPrice = resultSet.getDouble("unit_price");
        int unitsInStock = resultSet.getInt("units_in_stock");
        String categoryName = resultSet.getString("category_name");
        return new SupplierProducts(productId, productName, unitPrice, unitsInStock, categoryName);
    }

    public List<SupplierProducts> getSupplierProducts(int user_id) throws SQLException {
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name " +
            "FROM products " +
            "JOIN categories ON categories.category_id = products.category_id " +
            "WHERE supplier_id=? " +
            "ORDER BY product_id ASC;";
        List<SupplierProducts> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,user_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(createSupplierProducts(resultSet));
                }
            }
        }
        return products;
    }

    public List<SupplierProducts> getSupplierProducts(String filter, int user_id) throws SQLException {
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name " +
            "FROM products " +
            "JOIN categories ON categories.category_id = products.category_id " +
            "WHERE product_name LIKE ? " +
            "AND supplier_id=? " +
            "ORDER BY product_id ASC;";
        List<SupplierProducts> filteredProducts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,"%" + filter + "%");
            statement.setInt(2,user_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    filteredProducts.add(createSupplierProducts(resultSet));
                }
            }
        }
        return filteredProducts;
    }

    public List<SupplierProducts> getProductDetails(int userId, int productId, String productName) throws SQLException {
        List<SupplierProducts> productDetails = new ArrayList<>();
        if (productName.equals("")) {
            String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name " +
                "FROM products " +
                "JOIN categories ON categories.category_id = products.category_id " +
                "WHERE supplier_id=? " +
                "AND product_id=? " +
                "ORDER BY product_id ASC;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setInt(2, productId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        productDetails.add(createSupplierProducts(resultSet));
                    }
                }
            }
        }
        else {
            String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name " +
                "FROM products " +
                "JOIN categories ON categories.category_id = products.category_id " +
                "WHERE supplier_id=? " +
                "AND product_name LIKE ? " +
                "ORDER BY product_id ASC;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setString(2, "%" + productName + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        productDetails.add(createSupplierProducts(resultSet));
                    }
                }
            }
        }
        return productDetails;
    }

    public void addProduct(int productId, String productName, int supplierId, int categoryId, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) throws SQLException {
        String sql = "INSERT INTO products (product_id, product_name, supplier_id, category_id, quantity_per_unit, unit_price, units_in_stock, units_on_order, reorder_level, discontinued) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setInt(3, supplierId);
            statement.setInt(4, categoryId);
            statement.setString(5, quantityPerUnit);
            statement.setDouble(6, unitPrice);
            statement.setInt(7, unitsInStock);
            statement.setInt(8, unitsOnOrder);
            statement.setInt(9, reorderLevel);
            statement.setInt(10, discontinued);
            statement.executeUpdate();
        }
    }

}
