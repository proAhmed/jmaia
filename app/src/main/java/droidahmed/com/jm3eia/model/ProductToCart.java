package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/5/2016.
 */
public class ProductToCart {
    private String success;
    private String error;
    private String data;

    public ProductToCart() {
    }

    public ProductToCart(String success, String error, String data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
