package droidahmed.com.jm3eia.controller;

import droidahmed.com.jm3eia.model.CartQuantity;

/**
 * Created by ahmed on 3/1/2016.
 */
public interface OnCartListener {
    void onAddCart(CartQuantity cartQuantity, int num,boolean watch,double price);

}
