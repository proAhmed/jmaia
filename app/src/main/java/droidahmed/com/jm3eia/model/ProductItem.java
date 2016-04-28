package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 3/12/2016.
 */
public class ProductItem  {
  private String img;
  private  String tvTitle;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public ProductItem(String img, String tvTitle) {
        this.img = img;
        this.tvTitle = tvTitle;
    }
}
