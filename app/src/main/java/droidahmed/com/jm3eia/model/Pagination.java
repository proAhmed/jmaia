package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 7/20/2016.
 */
public class Pagination {
    private String Title;

    public Pagination(String url, String title) {
         Title = title;
    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                 ", Title='" + Title + '\'' +
                '}';
    }
}
