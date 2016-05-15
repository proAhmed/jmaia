package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/15/2016.
 */
public class CartCheck {
    private int cartId;
    private int add;
    private int enter;

    public CartCheck() {
    }

    public CartCheck(int cartId, int add, int enter) {
        this.cartId = cartId;
        this.add = add;
        this.enter = enter;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }

    @Override
    public String toString() {
        return "CartCheck{" +
                "cartId=" + cartId +
                ", add=" + add +
                ", enter=" + enter +
                '}';
    }
}
