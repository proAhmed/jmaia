package droidahmed.com.jm3eia.model;

import java.io.Serializable;

/**
 * Created by ahmed on 4/4/2016.
 */
public class AllProducts implements Serializable{
    private int ID;
    private double Code;
    private double CategoryID;
    private double BrandID;
     private double Price;
    private double Quantity;
    private String Picture;
    private String SliderPictures;
    private String CreatedDate;
    private String ModifiedDate;
    private int Viewed;
    private int Featured;
    private int State;
    private int ProductID;
    private int LanguageID;
    private String Name;
    private String Alias;
    private String Contents;
    private String Description;
    private String Keywords;
    private String CategoryName;
    private String BrandName;

    public AllProducts() {
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public AllProducts(int ID, double code, int categoryID, int brandID, double price, double quantity, String picture, String sliderPictures, String createdDate, String modifiedDate, int viewed, int featured, int state, int productID, int languageID, String name, String alias, String contents, String description, String keywords, String categoryName, String brandName) {
        this.ID = ID;
        Code = code;
        CategoryID = categoryID;
        BrandID = brandID;
        Price = price;
        Quantity = quantity;
        Picture = picture;
        SliderPictures = sliderPictures;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        Viewed = viewed;
        Featured = featured;
        State = state;
        ProductID = productID;
        LanguageID = languageID;
        Name = name;
        Alias = alias;
        Contents = contents;
        Description = description;
        Keywords = keywords;
        CategoryName = categoryName;
        BrandName = brandName;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public AllProducts(int ID, int code, int categoryID, int brandID, double price, int quantity, String picture,
                       String sliderPictures, String createdDate, String modifiedDate, int viewed, int featured,
                       int state, int productID, int languageID, String name, String alias, String contents,
                       String description, String keywords) {
        this.ID = ID;
        Code = code;
        CategoryID = categoryID;
        BrandID = brandID;
        Price = price;
        Quantity = quantity;
        Picture = picture;
        SliderPictures = sliderPictures;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        Viewed = viewed;
        Featured = featured;
        State = state;
        ProductID = productID;
        LanguageID = languageID;
        Name = name;
        Alias = alias;
        Contents = contents;
        Description = description;
        Keywords = keywords;
    }

    public AllProducts(int ID, int categoryID, int brandID, double price, int quantity,
                       String picture, String sliderPictures, String createdDate,String modifiedDate,
                       int viewed, int featured, int state,int productID, int languageID, String name,
                       String alias,String contents, String description, String keywords ) {
        this.ID = ID;
        CategoryID = categoryID;
        BrandID = brandID;
        Price = price;
        Quantity = quantity;
        Picture = picture;
        SliderPictures = sliderPictures;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        Viewed = viewed;
        Featured = featured;
        State = state;
        ProductID = productID;
        LanguageID = languageID;
        Name = name;
        Alias = alias;
        Contents = contents;
        Description = description;
        Keywords = keywords;

    }

    public double getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(double categoryID) {
        CategoryID = categoryID;
    }

    public double getBrandID() {
        return BrandID;
    }

    public void setBrandID(double brandID) {
        BrandID = brandID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getSliderPictures() {
        return SliderPictures;
    }

    public void setSliderPictures(String sliderPictures) {
        SliderPictures = sliderPictures;
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

    public int getViewed() {
        return Viewed;
    }

    public void setViewed(int viewed) {
        Viewed = viewed;
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

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
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

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllProducts that = (AllProducts) o;

        if (ID != that.ID) return false;
        if (Double.compare(that.Code, Code) != 0) return false;
        if (CategoryID != that.CategoryID) return false;
        if (BrandID != that.BrandID) return false;
        if (Double.compare(that.Price, Price) != 0) return false;
        if (Quantity != that.Quantity) return false;
        if (Viewed != that.Viewed) return false;
        if (Featured != that.Featured) return false;
        if (State != that.State) return false;
        if (ProductID != that.ProductID) return false;
        if (LanguageID != that.LanguageID) return false;
        if (Picture != null ? !Picture.equals(that.Picture) : that.Picture != null) return false;
        if (SliderPictures != null ? !SliderPictures.equals(that.SliderPictures) : that.SliderPictures != null)
            return false;
        if (CreatedDate != null ? !CreatedDate.equals(that.CreatedDate) : that.CreatedDate != null)
            return false;
        if (ModifiedDate != null ? !ModifiedDate.equals(that.ModifiedDate) : that.ModifiedDate != null)
            return false;
        if (Name != null ? !Name.equals(that.Name) : that.Name != null) return false;
        if (Alias != null ? !Alias.equals(that.Alias) : that.Alias != null) return false;
        if (Contents != null ? !Contents.equals(that.Contents) : that.Contents != null)
            return false;
        if (Description != null ? !Description.equals(that.Description) : that.Description != null)
            return false;
        if (Keywords != null ? !Keywords.equals(that.Keywords) : that.Keywords != null)
            return false;
        if (CategoryName != null ? !CategoryName.equals(that.CategoryName) : that.CategoryName != null)
            return false;
        return !(BrandName != null ? !BrandName.equals(that.BrandName) : that.BrandName != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ID;
        temp = Double.doubleToLongBits(Code);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(CategoryID);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(BrandID);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (Picture != null ? Picture.hashCode() : 0);
        result = 31 * result + (SliderPictures != null ? SliderPictures.hashCode() : 0);
        result = 31 * result + (CreatedDate != null ? CreatedDate.hashCode() : 0);
        result = 31 * result + (ModifiedDate != null ? ModifiedDate.hashCode() : 0);
        result = 31 * result + Viewed;
        result = 31 * result + Featured;
        result = 31 * result + State;
        result = 31 * result + ProductID;
        result = 31 * result + LanguageID;
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (Alias != null ? Alias.hashCode() : 0);
        result = 31 * result + (Contents != null ? Contents.hashCode() : 0);
        result = 31 * result + (Description != null ? Description.hashCode() : 0);
        result = 31 * result + (Keywords != null ? Keywords.hashCode() : 0);
        result = 31 * result + (CategoryName != null ? CategoryName.hashCode() : 0);
        result = 31 * result + (BrandName != null ? BrandName.hashCode() : 0);
        return result;
    }
}
