package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class CartItem {

    private int  ID;
    private int CategoryID;
    private int BrandID;
    private double OldPrice;
    private double Price;
    private int Quantity;
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
    private int cQuantity;

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
