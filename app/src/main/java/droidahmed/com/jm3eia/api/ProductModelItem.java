package droidahmed.com.jm3eia.api;

public class ProductModelItem {

	private String title;
	private String containt;
	private String location;
	private String date;
	private String imageURL;
	private String id;

	public ProductModelItem(String id, String title, String containt,
			String location, String date, String imageURL) {
		super();
		this.id = id;
		this.title = title;
		this.containt = containt;
		this.location = location;
		this.date = date;
		this.imageURL = imageURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContaint() {
		return containt;
	}

	public void setContaint(String containt) {
		this.containt = containt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
