package com.codecool.web.model;

public class AllProducts {

    private int productId;
    private String productName;
    private double unitPrice;
    private int unitsInStock;
    private String categoryName;
    private String supplierName;

    public AllProducts(int productId, String productName, double unitPrice, int unitsInStock, String categoryName, String supplierName) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
