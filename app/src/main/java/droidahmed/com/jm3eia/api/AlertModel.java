package droidahmed.com.jm3eia.api;

public class AlertModel {

	private String ID;
	private String Offer;
	private String Contents;
	private String CreatedDate;
	private String UserName;
	private String Picture;
	private String IsRead;
	private String OfferTitle;

	public String getOfferTitle() {
		return OfferTitle;
	}

	public void setOfferTitle(String offerTitle) {
		OfferTitle = offerTitle;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getOffer() {
		return Offer;
	}

	public void setOffer(String offer) {
		Offer = offer;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public String getIsRead() {
		return IsRead;
	}

	public void setIsRead(String isRead) {
		IsRead = isRead;
	}
}
