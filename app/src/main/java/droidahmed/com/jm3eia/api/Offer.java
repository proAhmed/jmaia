package droidahmed.com.jm3eia.api;

public class Offer {

	private String ID;
	private String Title;
	private String Picture;
	private String Mobile;
	private String Viewed;
	private String CreatedDate;
	private Object ModifiedDate;
	private String FinishedDate;
	private String State;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String Picture) {
		this.Picture = Picture;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String Mobile) {
		this.Mobile = Mobile;
	}

	public String getViewed() {
		return Viewed;
	}

	public void setViewed(String Viewed) {
		this.Viewed = Viewed;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(String CreatedDate) {
		this.CreatedDate = CreatedDate;
	}

	public Object getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(Object ModifiedDate) {
		this.ModifiedDate = ModifiedDate;
	}

	public String getFinishedDate() {
		return FinishedDate;
	}

	public void setFinishedDate(String FinishedDate) {
		this.FinishedDate = FinishedDate;
	}

	public String getState() {
		return State;
	}

	public void setState(String State) {
		this.State = State;
	}
}
