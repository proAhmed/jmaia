package droidahmed.com.jm3eia.model;

import java.io.Serializable;

/**
 * Created by ahmed on 5/2/2016.
 */
public class ProductCart implements Serializable{
    private AllProducts allProducts;
    private int count;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCart(AllProducts allProducts, int count, double price) {
        this.allProducts = allProducts;
        this.count = count;
        this.price = price;
    }

    public ProductCart(AllProducts allProducts, int count) {
        this.allProducts = allProducts;
        this.count = count;
    }

    public AllProducts getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(AllProducts allProducts) {
        this.allProducts = allProducts;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductCart{" +
                "allProducts=" + allProducts +
                ", count=" + count +
                '}';
    }
}
