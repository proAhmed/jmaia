package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class InputCartItems {
    private int ID;
    private int Quantity;
    private String CreatedDate;

    public InputCartItems() {
    }

    public InputCartItems(int ID, int quantity, String createdDate) {
        this.ID = ID;
        Quantity = quantity;
        CreatedDate = createdDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
