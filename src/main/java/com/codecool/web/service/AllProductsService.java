package com.codecool.web.service;

import com.codecool.web.dao.AllProductsDB;
import com.codecool.web.model.AllProducts;
import java.sql.SQLException;
import java.util.List;

public class AllProductsService {

    private AllProductsDB db;

    public AllProductsService(AllProductsDB db) {
        this.db = db;
    }

    public List<AllProducts> allProducts() throws SQLException {
        List<AllProducts> result = db.getAllProducts();
        return result;
    }

    public List<AllProducts> allProductsFilter(String filter) throws SQLException {
        List<AllProducts> result = db.getAllProducts(filter);
        return result;
    }
}
