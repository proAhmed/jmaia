package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed radwan on 8/9/2017.
 */

public class OrderAllowed {

    private int sAllowOrders;
    private String sPhone;

    public OrderAllowed() {
    }

    public int getsAllowOrders() {
        return sAllowOrders;
    }

    public void setsAllowOrders(int sAllowOrders) {
        this.sAllowOrders = sAllowOrders;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }
}
