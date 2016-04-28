package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 3/1/2016.
 */
public class Product {
//    private String name;
//    private String codeName;
//    private double price;
//    private String availability;
//    private String category;
//    private String image;

    int id;
    String  Code;
    String  CategoryID;
    String   BrandID;
    String  Price;
    String Quantity;
    String Picture;
    String SliderPictures;
    String CreatedDate;
    String ModifiedDate;
    String Viewed;
    String Featured;
    String State;
    String ProductID;
    String LanguageID;
    String Name;
    String Alias;
    String Contents;
    String Description;
    String Keywords;
    public Product() {
    }

    public Product(int id, String code, String categoryID, String brandID, String price,
                   String quantity, String picture, String sliderPictures, String createdDate,
                   String modifiedDate, String viewed, String featured, String state,
                   String productID, String languageID, String name, String alias, String contents,
                   String description, String keywords) {
        this.id = id;
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

    public Product(String categoryID, String code, int id) {
        CategoryID = categoryID;
        Code = code;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String brandID) {
        BrandID = brandID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
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

    public String getViewed() {
        return Viewed;
    }

    public void setViewed(String viewed) {
        Viewed = viewed;
    }

    public String getFeatured() {
        return Featured;
    }

    public void setFeatured(String featured) {
        Featured = featured;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getLanguageID() {
        return LanguageID;
    }

    public void setLanguageID(String languageID) {
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
}
