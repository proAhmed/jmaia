package droidahmed.com.jm3eia.api;

public class AvaterModels {

	private String ID;
	private String url;
	private String name;

	public AvaterModels() {
		super();
	}

	public AvaterModels(String iD, String url, String name) {
		super();
		ID = iD;
		this.url = url;
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
