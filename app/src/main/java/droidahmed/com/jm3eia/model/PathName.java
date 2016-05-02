package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/28/2016.
 */
public class PathName {

  private int ID;
  private int ParentID;
  private String Picture;
  private String CreatedDate;
  private String ModifiedDate;
  private int SortingOrder;
  private int Featured;
  private int State;
  private int CategoryID;
  private int LanguageID;
  private String Name;
  private String Alias;
  private String Description;
  private String Keywords;
  private int ItemsCount;
  private boolean IsParent;

    public PathName() {
    }

    public PathName(int ID, int parentID, String picture, String createdDate, String modifiedDate,
                    int sortingOrder, int featured, int state, int categoryID, int languageID,
                    String name, String alias, String description, String keywords,
                    int itemsCount, boolean isParent) {
        this.ID = ID;
        ParentID = parentID;
        Picture = picture;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        SortingOrder = sortingOrder;
        Featured = featured;
        State = state;
        CategoryID = categoryID;
        LanguageID = languageID;
        Name = name;
        Alias = alias;
        Description = description;
        Keywords = keywords;
        ItemsCount = itemsCount;
        IsParent = isParent;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public int getSortingOrder() {
        return SortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        SortingOrder = sortingOrder;
    }

    public int getFeatured() {
        return Featured;
    }

    public void setFeatured(int featured) {
        Featured = featured;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getLanguageID() {
        return LanguageID;
    }

    public void setLanguageID(int languageID) {
        LanguageID = languageID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public int getItemsCount() {
        return ItemsCount;
    }

    public void setItemsCount(int itemsCount) {
        ItemsCount = itemsCount;
    }

    public boolean isParent() {
        return IsParent;
    }

    public void setIsParent(boolean isParent) {
        IsParent = isParent;
    }

    @Override
    public String toString() {
        return "PathName{" +
                "ID=" + ID +
                ", ParentID=" + ParentID +
                ", Picture='" + Picture + '\'' +
                ", CreatedDate='" + CreatedDate + '\'' +
                ", ModifiedDate='" + ModifiedDate + '\'' +
                ", SortingOrder=" + SortingOrder +
                ", Featured=" + Featured +
                ", State=" + State +
                ", CategoryID=" + CategoryID +
                ", LanguageID=" + LanguageID +
                ", Name='" + Name + '\'' +
                ", Alias='" + Alias + '\'' +
                ", Description='" + Description + '\'' +
                ", Keywords='" + Keywords + '\'' +
                ", ItemsCount=" + ItemsCount +
                ", IsParent=" + IsParent +
                '}';
    }
}
