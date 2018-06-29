package com.codecool.web.dao;

import com.codecool.web.model.AllProducts;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllProductsDB {

    private Connection connection;

    public AllProductsDB(Connection connection) {
        this.connection = connection;
    }

    private AllProducts createAllProducts(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        double unitPrice = resultSet.getDouble("unit_price");
        int unitsInStock = resultSet.getInt("units_in_stock");
        String categoryName = resultSet.getString("category_name");
        String companyName = resultSet.getString("company_name");
        return new AllProducts(productId, productName, unitPrice, unitsInStock, categoryName, companyName);
    }

    public List<AllProducts> getAllProducts() throws SQLException {
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name, suppliers.company_name " +
            "FROM products " +
            "JOIN categories ON categories.category_id = products.category_id " +
            "JOIN suppliers ON suppliers.supplier_id = products.supplier_id " +
            "ORDER BY product_id ASC;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<AllProducts> tempProducts = new ArrayList<>();
            while (resultSet.next()) {
                tempProducts.add(createAllProducts(resultSet));
            }
            return tempProducts;
        }
    }

    public List<AllProducts> getAllProducts(String filter) throws SQLException {
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock, categories.category_name, suppliers.company_name " +
            "FROM products " +
            "JOIN categories ON categories.category_id = products.category_id " +
            "JOIN suppliers ON suppliers.supplier_id = products.supplier_id " +
            "WHERE product_name LIKE ? " +
            "ORDER BY product_id ASC;";
        List<AllProducts> filteredProducts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,"%" + filter + "%");
            System.out.println(filter);
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    filteredProducts.add(createAllProducts(resultSet));
                }
            }
        }
        return filteredProducts;
    }

}
