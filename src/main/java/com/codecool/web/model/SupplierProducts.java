package com.codecool.web.model;

public class SupplierProducts {

    private int productId;
    private String productName;
    private int supplier_id;
    private int category_id;
    private String categoryName;
    private int quantity_per_unit;
    private double unitPrice;
    private int unitsInStock;
    private int units_on_order;
    private int reorder_level;
    private int discontinued;


    public SupplierProducts(int productId, String productName, double unitPrice, int unitsInStock, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.categoryName = categoryName;
    }

//    public SupplierProducts(int productId, String productName, int supplier_id, int category_id, String categoryName, int quantity_per_unit, double unitPrice, int unitsInStock, int units_on_order, int reorder_level, int discontinued) {
//        this.productId = productId;
//        this.productName = productName;
//        this.supplier_id = supplier_id;
//        this.category_id = category_id;
//        this.categoryName = categoryName;
//        this.quantity_per_unit = quantity_per_unit;
//        this.unitPrice = unitPrice;
//        this.unitsInStock = unitsInStock;
//        this.units_on_order = units_on_order;
//        this.reorder_level = reorder_level;
//        this.discontinued = discontinued;
//    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getQuantity_per_unit() {
        return quantity_per_unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public int getUnits_on_order() {
        return units_on_order;
    }

    public int getReorder_level() {
        return reorder_level;
    }

    public int getDiscontinued() {
        return discontinued;
    }
}
