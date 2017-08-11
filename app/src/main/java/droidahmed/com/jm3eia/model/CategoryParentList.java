package droidahmed.com.jm3eia.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ahmed on 4/28/2016.
 */
public class CategoryParentList implements Serializable {
    public int head,child,subChild;
  public CategoryParent categoryParent;
    public CategoryParent categoryParentChild;

    public ArrayList<CategoryParentList>lists;

    public CategoryParentList() {
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public CategoryParent getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(CategoryParent categoryParent) {
        this.categoryParent = categoryParent;
    }

    public CategoryParent getCategoryParentChild() {
        return categoryParentChild;
    }

    public void setCategoryParentChild(CategoryParent categoryParentChild) {
        this.categoryParentChild = categoryParentChild;
    }

    public ArrayList<CategoryParentList> getLists() {
        return lists;
    }

    public void setLists(ArrayList<CategoryParentList> lists) {
        this.lists = lists;
    }

    public CategoryParentList(int head) {
        this.head = head;
    }
    public CategoryParentList(int head, int child,CategoryParent categoryParentChild) {
        this.head = head;
        this.child = child;

        this.categoryParent = categoryParentChild;
    }
    public CategoryParentList(int head, CategoryParent categoryParent) {
        this.head = head;
        this.categoryParent = categoryParent;
    }

    public int getSubChild() {
        return subChild;
    }

    public void setSubChild(int subChild) {
        this.subChild = subChild;
    }
}
