package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class SlideShow {
    private int ID;
    private String Picture;
    private String CreatedDate;
    private String ModifiedDate;
    private int SortingOrder;
    private int State;
    private int SliderID;
    private int LanguageID;
    private String Title;
    private String Description;
    private String Url;

    public SlideShow() {
    }

    public SlideShow(int ID, String picture, String createdDate, String modifiedDate,
                     int sortingOrder, int state, int sliderID, int languageID,
                     String title, String description, String url) {
        this.ID = ID;
        Picture = picture;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        SortingOrder = sortingOrder;
        State = state;
        SliderID = sliderID;
        LanguageID = languageID;
        Title = title;
        Description = description;
        Url = url;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getSliderID() {
        return SliderID;
    }

    public void setSliderID(int sliderID) {
        SliderID = sliderID;
    }

    public int getLanguageID() {
        return LanguageID;
    }

    public void setLanguageID(int languageID) {
        LanguageID = languageID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
