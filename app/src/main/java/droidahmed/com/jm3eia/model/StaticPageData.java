package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/18/2016.
 */
public class StaticPageData {
    private int ID;
    private String Contents;
    private String Title;

    public StaticPageData(int ID, String title, String contents) {
        this.ID = ID;
        Title = title;
        Contents = contents;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
