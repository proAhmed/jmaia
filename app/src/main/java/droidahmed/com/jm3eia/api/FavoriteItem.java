package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class FavoriteItem {

	private String ID;
	private String Title;
	private String Contents;
	private String Picture;
	private List<String> Pictures = new ArrayList<String>();
	private String Price;
	private String Mobile;
	private String NewMobile;
	private String Viewed;
	private String CreatedDate;
	private String FavDate;
	private String CityName;
	private String CategoryName;
	private String UserName;
	private String User;
	private String AllowComments;

	public FavoriteItem(FavoriteItem favoriteItem) {
		ID = favoriteItem.getID();
		Title = favoriteItem.getTitle();
		Contents = favoriteItem.getContents();
		Picture = favoriteItem.getPicture();
		Pictures = favoriteItem.getPictures();
		Price = favoriteItem.Price;
		Mobile = favoriteItem.getMobile();
		Viewed = favoriteItem.getViewed();
		CreatedDate = favoriteItem.getCreatedDate();
		FavDate = favoriteItem.getFavDate();
		CityName = favoriteItem.getCityName();
		CategoryName = favoriteItem.getCategoryName();
		UserName = favoriteItem.getUserName();
		User = favoriteItem.getUser();
		AllowComments = favoriteItem.getAllowComments();
	}

	public FavoriteItem(String iD, String title, String contents,
			String picture, List<String> pictures, String price, String mobile,
			String viewed, String createdDate, String favDate, String cityName,
			String categoryName, String userName, String user,
			String AllowComments) {
		super();
		ID = iD;
		Title = title;
		Contents = contents;
		Picture = picture;
		Pictures = pictures;
		Price = price;
		Mobile = mobile;
		Viewed = viewed;
		CreatedDate = createdDate;
		FavDate = favDate;
		CityName = cityName;
		CategoryName = categoryName;
		UserName = userName;
		User = user;
		this.AllowComments = AllowComments;
	}

	public String getNewMobile() {
		return NewMobile;
	}

	public void setNewMobile(String newMobile) {
		NewMobile = newMobile;
	}

	public String getAllowComments() {
		return AllowComments;
	}

	public void setAllowComments(String allowComments) {
		AllowComments = allowComments;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	/**
	 * 
	 * @return The ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * 
	 * @param ID
	 *            The ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * 
	 * @return The Title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * 
	 * @param Title
	 *            The Title
	 */
	public void setTitle(String Title) {
		this.Title = Title;
	}

	/**
	 * 
	 * @return The Contents
	 */
	public String getContents() {
		return Contents;
	}

	/**
	 * 
	 * @param Contents
	 *            The Contents
	 */
	public void setContents(String Contents) {
		this.Contents = Contents;
	}

	/**
	 * 
	 * @return The Picture
	 */
	public String getPicture() {
		return Picture;
	}

	/**
	 * 
	 * @param Picture
	 *            The Picture
	 */
	public void setPicture(String Picture) {
		this.Picture = Picture;
	}

	/**
	 * 
	 * @return The Pictures
	 */
	public List<String> getPictures() {
		return Pictures;
	}

	/**
	 * 
	 * @param Pictures
	 *            The Pictures
	 */
	public void setPictures(List<String> Pictures) {
		this.Pictures = Pictures;
	}

	/**
	 * 
	 * @return The Price
	 */
	public String getPrice() {
		return Price;
	}

	/**
	 * 
	 * @param Price
	 *            The Price
	 */
	public void setPrice(String Price) {
		this.Price = Price;
	}

	/**
	 * 
	 * @return The Mobile
	 */
	public String getMobile() {
		return Mobile;
	}

	/**
	 * 
	 * @param Mobile
	 *            The Mobile
	 */
	public void setMobile(String Mobile) {
		this.Mobile = Mobile;
	}

	/**
	 * 
	 * @return The Viewed
	 */
	public String getViewed() {
		return Viewed;
	}

	/**
	 * 
	 * @param Viewed
	 *            The Viewed
	 */
	public void setViewed(String Viewed) {
		this.Viewed = Viewed;
	}

	/**
	 * 
	 * @return The CreatedDate
	 */
	public String getCreatedDate() {
		return CreatedDate;
	}

	/**
	 * 
	 * @param CreatedDate
	 *            The CreatedDate
	 */
	public void setCreatedDate(String CreatedDate) {
		this.CreatedDate = CreatedDate;
	}

	/**
	 * 
	 * @return The FavDate
	 */
	public String getFavDate() {
		return FavDate;
	}

	/**
	 * 
	 * @param FavDate
	 *            The FavDate
	 */
	public void setFavDate(String FavDate) {
		this.FavDate = FavDate;
	}

	/**
	 * 
	 * @return The CityName
	 */
	public String getCityName() {
		return CityName;
	}

	/**
	 * 
	 * @param CityName
	 *            The CityName
	 */
	public void setCityName(String CityName) {
		this.CityName = CityName;
	}

	/**
	 * 
	 * @return The CategoryName
	 */
	public String getCategoryName() {
		return CategoryName;
	}

	/**
	 * 
	 * @param CategoryName
	 *            The CategoryName
	 */
	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
	}

}
