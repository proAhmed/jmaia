package droidahmed.com.jm3eia.model;

/**
 * Created by Hong Thai.
 */
public class SlidingMenuItem {

    private int imageResource;
    private String menuItemName;
    private int positions;

    public SlidingMenuItem(int image, String name) {
        this.imageResource = image;
        this.menuItemName = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public SlidingMenuItem(int imageResource, String menuItemName, int positions) {
        this.imageResource = imageResource;
        this.menuItemName = menuItemName;
        this.positions = positions;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int position) {
        this.positions = position;
    }
}
