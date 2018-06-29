package com.codecool.web.service;

import com.codecool.web.dao.SupplierProductsDB;
import com.codecool.web.model.SupplierProducts;
import java.sql.SQLException;
import java.util.List;

public class SupplierProductsService {

    private SupplierProductsDB db;

    public SupplierProductsService(SupplierProductsDB db) {
        this.db = db;
    }

    public List<SupplierProducts> supplierProducts(int userId) throws SQLException {
        List<SupplierProducts> result = db.getSupplierProducts(userId);
        return result;
    }

    public List<SupplierProducts> supplierProductsFilter(String filter, int userId) throws SQLException {
        List<SupplierProducts> result = db.getSupplierProducts(filter, userId);
        return result;
    }

    public List<SupplierProducts> productDetails(int userId, int productId, String productName) throws SQLException {
        List<SupplierProducts> result = db.getProductDetails(userId, productId, productName);
        return result;
    }

}
