package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class DResult {

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
	private String CityName;
	private String CategoryName;
	private String UserName;
	private String CommentsCount;
	private String User;
	private String SinceDate;
	private String AllowComments;

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

	/**
	 * 
	 * @return The UserName
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * 
	 * @param UserName
	 *            The UserName
	 */
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	/**
	 * 
	 * @return The CommentsCount
	 */
	public String getCommentsCount() {
		return CommentsCount;
	}

	/**
	 * 
	 * @param CommentsCount
	 *            The CommentsCount
	 */
	public void setCommentsCount(String CommentsCount) {
		this.CommentsCount = CommentsCount;
	}

	public String getSinceDate() {
		return SinceDate;
	}

	public void setSinceDate(String sinceDate) {
		SinceDate = sinceDate;
	}

}
