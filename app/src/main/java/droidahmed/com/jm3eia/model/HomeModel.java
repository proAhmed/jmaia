package droidahmed.com.jm3eia.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmed on 4/3/2016.
 */
public class HomeModel implements Parcelable {
    private int ID ;
    private int CategoryID;
    private int BrandID;
    private String OldPrice;
    private String Price;
    private String Quantity;
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

    public HomeModel() {
    }

    public HomeModel(int ID, int categoryID, int brandID, String oldPrice, String price, String quantity,
                     String picture, String sliderPictures, String createdDate, String modifiedDate,
                     int viewed, int featured, int state, int productID, int languageID, String name,
                     String alias, String contents, String description, String keywords) {
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
    }

    public HomeModel(int ID, int categoryID, int brandID, String oldPrice, String price, String quantity,
                     String picture, String sliderPictures, String createdDate, String modifiedDate,
                     int viewed, int featured, int state, int productID, int languageID) {
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
    }

    protected HomeModel(Parcel in) {
        ID = in.readInt();
        CategoryID = in.readInt();
        BrandID = in.readInt();
        OldPrice = in.readString();
        Price = in.readString();
        Quantity = in.readString();
        Picture = in.readString();
        SliderPictures = in.readString();
        CreatedDate = in.readString();
        ModifiedDate = in.readString();
        Viewed = in.readInt();
        Featured = in.readInt();
        State = in.readInt();
        ProductID = in.readInt();
        LanguageID = in.readInt();
        Name = in.readString();
        Alias = in.readString();
        Contents = in.readString();
        Description = in.readString();
        Keywords = in.readString();
    }

    public static final Creator<HomeModel> CREATOR = new Creator<HomeModel>() {
        @Override
        public HomeModel createFromParcel(Parcel in) {
            return new HomeModel(in);
        }

        @Override
        public HomeModel[] newArray(int size) {
            return new HomeModel[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public String getOldPrice() {
        return OldPrice;
    }

    public void setOldPrice(String oldPrice) {
        OldPrice = oldPrice;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeInt(CategoryID);
        dest.writeInt(BrandID);
        dest.writeString(OldPrice);
        dest.writeString(Price);
        dest.writeString(Quantity);
        dest.writeString(Picture);
        dest.writeString(SliderPictures);
        dest.writeString(CreatedDate);
        dest.writeString(ModifiedDate);
        dest.writeInt(Viewed);
        dest.writeInt(Featured);
        dest.writeInt(State);
        dest.writeInt(ProductID);
        dest.writeInt(LanguageID);
        dest.writeString(Name);
        dest.writeString(Alias);
        dest.writeString(Contents);
        dest.writeString(Description);
        dest.writeString(Keywords);
    }

}
