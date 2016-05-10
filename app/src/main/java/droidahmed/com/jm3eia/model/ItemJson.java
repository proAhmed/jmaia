package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/8/2016.
 */
public class ItemJson  {
    private int idItem;
    private double quantityItem;
    private String timeItem;

    public ItemJson(int idItem, double quantityItem, String timeItem) {
        this.idItem = idItem;
        this.quantityItem = quantityItem;
        this.timeItem = timeItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public double getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(double quantityItem) {
        this.quantityItem = quantityItem;
    }

    public String getTimeItem() {
        return timeItem;
    }

    public void setTimeItem(String timeItem) {
        this.timeItem = timeItem;
    }

    @Override
    public String toString() {
        return "ItemJson{" +
                "idItem=" + idItem +
                ", quantityItem=" + quantityItem +
                ", timeItem='" + timeItem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemJson itemJson = (ItemJson) o;

        return idItem == itemJson.idItem;

    }

    @Override
    public int hashCode() {
        return idItem;
    }
}
