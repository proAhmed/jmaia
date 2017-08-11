package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed radwan on 7/1/2017.
 */

public class Notification {
    private int id;
    private String title;
    private String content;

    public Notification() {
    }

    public Notification(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
