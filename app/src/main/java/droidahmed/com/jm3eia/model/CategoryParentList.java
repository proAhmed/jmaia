package droidahmed.com.jm3eia.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ahmed on 4/28/2016.
 */
public class CategoryParentList implements Serializable {
    public int head;
  public CategoryParent categoryParent;
    public CategoryParent categoryParentChild;

    public ArrayList<CategoryParentList>lists;

    public CategoryParentList() {
    }

    public CategoryParentList(int head) {
        this.head = head;
    }
    public CategoryParentList(int head, int child,CategoryParent categoryParentChild) {
        this.head = head;
        this.categoryParent = categoryParentChild;
    }
    public CategoryParentList(int head, CategoryParent categoryParent) {
        this.head = head;
        this.categoryParent = categoryParent;
    }




}
