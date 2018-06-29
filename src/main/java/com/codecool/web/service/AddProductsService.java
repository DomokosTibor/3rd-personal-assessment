package com.codecool.web.service;

import com.codecool.web.dao.AddProductsDB;
import com.codecool.web.model.SupplierProducts;
import java.sql.SQLException;
import java.util.List;

public class AddProductsService {

    private AddProductsDB db;

    public AddProductsService(AddProductsDB db) {
        this.db = db;
    }

    public List<SupplierProducts> ap(int userId) throws SQLException {
        List<SupplierProducts> result = db.addProduct(userId);
        return result;
    }

    public List<SupplierProducts> addProduct(int productId, String productName, int supplierId, int categoryId, int quantityPerUnit, int unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel, int discontinued) throws SQLException {
        List<SupplierProducts> result = db.addProduct(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued);
        return result;
    }
}
