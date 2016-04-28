package droidahmed.com.jm3eia.model;

import java.util.List;

public class AddOfferInput {

	private String AuthUserName;
	private String AuthPassword;
	private String CategoryID;
	private String CityID;
	private String Title;
	private String Contents;
	private String Mobile;
	private String Picture;
	private List<String> Pictures;
	private String AllowComments;
	private String offerID;

	public AddOfferInput(String authUserName, String authPassword,
			String categoryID, String cityID, String title, String contents,
			String mobile, String picture, List<String> pictures,
			String AllowComments) {
		super();
		AuthUserName = authUserName;
		AuthPassword = authPassword;
		CategoryID = categoryID;
		CityID = cityID;
		Title = title;
		Contents = contents;
		Mobile = mobile;
		Picture = picture;
		Pictures = pictures;
		this.AllowComments = AllowComments;
	}

	public AddOfferInput(String authUserName, String authPassword,
			String contents, String picture, List<String> pictures,
			String offerID, String title) {
		super();
		AuthUserName = authUserName;
		AuthPassword = authPassword;
		Contents = contents;
		Pictures = pictures;
		this.Picture = picture;
		this.offerID = offerID;
		Title = title;
	}

	public String getOfferID() {
		return offerID;
	}

	public void setOfferID(String offerID) {
		this.offerID = offerID;
	}

	public String getAllowComments() {
		return AllowComments;
	}

	public void setAllowComments(String allowComments) {
		AllowComments = allowComments;
	}

	public String getAuthUserName() {
		return AuthUserName;
	}

	public void setAuthUserName(String authUserName) {
		AuthUserName = authUserName;
	}

	public String getAuthPassword() {
		return AuthPassword;
	}

	public void setAuthPassword(String authPassword) {
		AuthPassword = authPassword;
	}

	public String getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}

	public String getCityID() {
		return CityID;
	}

	public void setCityID(String cityID) {
		CityID = cityID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public List<String> getPictures() {
		return Pictures;
	}

	public void setPictures(List<String> pictures) {
		Pictures = pictures;
	}

	@Override
	public String toString() {
		return "AddOfferInput [Title=" + Title + ", Contents=" + Contents
				+ ", Mobile=" + Mobile + "]";
	}

}
