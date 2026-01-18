package main.model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private int stock;
    private String category;
    private String imagePath;

    public Product(int productId, String name, double price, int stock, String category, String imagePath) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imagePath = imagePath;
    
    }

    // Getters and Setters

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }







}
