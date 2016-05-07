package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class CartItem {

    private int  ID;
    private double Code;
    private double CategoryID;
    private double BrandID;
    private double OldPrice;
    private double Price;
    private double Quantity;
    private String Picture;
    private String SliderPictures;
    private String CreatedDate;
    private String ModifiedDate;
    private int Viewed;
    private int Featured;
    private int State;
    private double ProductID;
    private double LanguageID;
    private String Name;
    private String Alias;
    private String Contents;
    private String Description;
    private String Keywords;
    private String CategoryName;
    private int cQuantity;

    public int getcQuantity() {
        return cQuantity;
    }

    public CartItem(int ID, int code, int categoryID, int brandID, double oldPrice, double price,
                    int quantity, String picture, String sliderPictures, String createdDate, String modifiedDate,
                    int viewed, int featured, int state, int productID, int languageID, String name, String alias,
                    String contents, String description, String keywords, String categoryName, int cQuantity) {
        this.ID = ID;
        Code = code;
        CategoryID = categoryID;
        BrandID = brandID;
        OldPrice = oldPrice;
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
        this.cQuantity = cQuantity;
    }

    public void setcQuantity(int cQuantity) {
        this.cQuantity = cQuantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getCode() {
        return Code;
    }

    public void setCode(double code) {
        Code = code;
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

    public double getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(double oldPrice) {
        OldPrice = oldPrice;
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

    public double getProductID() {
        return ProductID;
    }

    public void setProductID(double productID) {
        ProductID = productID;
    }

    public double getLanguageID() {
        return LanguageID;
    }

    public void setLanguageID(double languageID) {
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }



    public CartItem() {
    }

    public CartItem(int ID, int categoryID, int brandID, double oldPrice, double price, int quantity, String picture,
                    String sliderPictures, String createdDate, String modifiedDate, int viewed, int featured, int state,
                    int productID, int languageID, String name, String alias, String contents, String description,
                    String keywords, String categoryName, int cQuantity) {
        this.ID = ID;
        CategoryID = categoryID;
        BrandID = brandID;
        OldPrice = oldPrice;
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
        this.cQuantity = cQuantity;
    }
}
