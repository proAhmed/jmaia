package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class SinglePage {
    private int  ID;
    private String CreatedDate;
    private String ModifiedDate;
    private int Viewed;
    private int SortingOrder;
    private int State;
    private int PageID;
    private int LanguageID;
    private String Title;
    private String Alias;
    private String Contents;
    private String Keywords;
    private String Description;

    public SinglePage() {
    }

    public SinglePage(int ID, String createdDate, String modifiedDate, int viewed, int sortingOrder,
                      int state, int pageID, int languageID, String title, String alias, String contents,
                      String keywords, String description) {
        this.ID = ID;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        Viewed = viewed;
        SortingOrder = sortingOrder;
        State = state;
        PageID = pageID;
        LanguageID = languageID;
        Title = title;
        Alias = alias;
        Contents = contents;
        Keywords = keywords;
        Description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getPageID() {
        return PageID;
    }

    public void setPageID(int pageID) {
        PageID = pageID;
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

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
