package droidahmed.com.jm3eia.model;

import java.util.ArrayList;

/**
 * Created by ahmed on 5/7/2016.
 */
public class CheckOutData {
    private String Message;
    private int OrderID;
    private String OrderDate;
    private ArrayList<CartItem>Products;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public ArrayList<CartItem> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<CartItem> products) {
        Products = products;
    }
}
