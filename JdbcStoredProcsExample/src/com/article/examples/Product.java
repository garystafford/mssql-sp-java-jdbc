package com.article.examples;

/**
 * Represent a Product in the Adventure Works database
 * @author Gary A. Stafford
 */
public class Product {

    private String Product;
    private String ProductNumber;
    private String Color;
    private String Size;
    private String Model;

    /**
     *
     * @param product
     * @param color
     * @param productNumber
     * @param size
     * @param model
     */
    public Product(String product, String productNumber,
            String color, String size, String model) {
        this.Product = product;
        this.ProductNumber = productNumber;
        this.Color = color;
        this.Size = size;
        this.Model = model;
    }

    /**
     * @return the Model
     */
    public String getModel() {
        return Model;
    }

    /**
     * @param Model the Model to set
     */
    public void setModel(String Model) {
        this.Model = Model;
    }

    /**
     * @return the Product
     */
    public String getProduct() {
        return Product;
    }

    /**
     * @param Product the Product to set
     */
    public void setProduct(String Product) {
        this.Product = Product;
    }

    /**
     * @return the ProductNumber
     */
    public String getProductNumber() {
        return ProductNumber;
    }

    /**
     * @param ProductNumber the ProductNumber to set
     */
    public void setProductNumber(String ProductNumber) {
        this.ProductNumber = ProductNumber;
    }

    /**
     * @return the Color
     */
    public String getColor() {
        return Color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(String Color) {
        this.Color = Color;
    }

    /**
     * @return the Size
     */
    public String getSize() {
        return Size;
    }

    /**
     * @param Size the Size to set
     */
    public void setSize(String Size) {
        this.Size = Size;
    }
}
