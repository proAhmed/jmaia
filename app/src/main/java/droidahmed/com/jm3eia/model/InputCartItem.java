package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/12/2016.
 */
public class InputCartItem {
   private int ID;
   private String Quantity;
   private String CreatedDate;

    public InputCartItem(){

    }

    public InputCartItem(int ID, String quantity, String createdDate) {
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
