package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class UserDResult {
	private String ID;
	private String User;
	private String Category;
	private String City;
	private String Title;
	private String Alias;
	private String Contents;
	private String Description;
	private String Keywords;
	private String Price;
	private String Picture;
	private List<String> Pictures = new ArrayList<String>();
	private String Mobile;
	private String Viewed;
	private String CreatedDate;
	private String ModifiedDate;
	private String FinishedDate;
	private String Featured;
	private String Approved;
	private String State;
	private String CityName;
	private String CategoryName;
	private String CategoryAlias;
	private String UserName;
	private String CommentsCount;
	private String SinceDate;

	public UserDResult(UserDResult userDResult) {
		ID = userDResult.getID();
		User = userDResult.getUser();
		Category = userDResult.getCategory();
		City = userDResult.getCity();
		Title = userDResult.getTitle();
		Alias = userDResult.getAlias();
		Contents = userDResult.getContents();
		Description = userDResult.getDescription();
		Keywords = userDResult.getKeywords();
		Price = userDResult.getPrice();
		Picture = userDResult.getPicture();
		Pictures = userDResult.getPictures();
		Mobile = userDResult.getMobile();
		Viewed = userDResult.getViewed();
		CreatedDate = userDResult.getCreatedDate();
		ModifiedDate = userDResult.getModifiedDate();
		FinishedDate = userDResult.getFinishedDate();
		Featured = userDResult.getFeatured();
		Approved = userDResult.getApproved();
		State = userDResult.getState();
		CityName = userDResult.getCityName();
		CategoryName = userDResult.getCategoryName();
		CategoryAlias = userDResult.getCategoryAlias();
		UserName = userDResult.getUserName();
		CommentsCount = userDResult.getCommentsCount();
		SinceDate = userDResult.getSinceDate();
	}

	public UserDResult(String iD, String user, String category, String city,
			String title, String alias, String contents, String description,
			String keywords, String price, String picture,
			List<String> pictures, String mobile, String viewed,
			String createdDate, String modifiedDate, String finishedDate,
			String featured, String approved, String state, String cityName,
			String categoryName, String categoryAlias, String userName,
			String commentsCount, String SinceDate) {
		super();
		ID = iD;
		User = user;
		Category = category;
		City = city;
		Title = title;
		Alias = alias;
		Contents = contents;
		Description = description;
		Keywords = keywords;
		Price = price;
		Picture = picture;
		Pictures = pictures;
		Mobile = mobile;
		Viewed = viewed;
		CreatedDate = createdDate;
		ModifiedDate = modifiedDate;
		FinishedDate = finishedDate;
		Featured = featured;
		Approved = approved;
		State = state;
		CityName = cityName;
		CategoryName = categoryName;
		CategoryAlias = categoryAlias;
		UserName = userName;
		CommentsCount = commentsCount;
		this.SinceDate = SinceDate;
	}

	public String getSinceDate() {
		return SinceDate;
	}

	public void setSinceDate(String sinceDate) {
		SinceDate = sinceDate;
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
	 * @return The User
	 */
	public String getUser() {
		return User;
	}

	/**
	 * 
	 * @param User
	 *            The User
	 */
	public void setUser(String User) {
		this.User = User;
	}

	/**
	 * 
	 * @return The Category
	 */
	public String getCategory() {
		return Category;
	}

	/**
	 * 
	 * @param Category
	 *            The Category
	 */
	public void setCategory(String Category) {
		this.Category = Category;
	}

	/**
	 * 
	 * @return The City
	 */
	public String getCity() {
		return City;
	}

	/**
	 * 
	 * @param City
	 *            The City
	 */
	public void setCity(String City) {
		this.City = City;
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
	 * @return The Alias
	 */
	public String getAlias() {
		return Alias;
	}

	/**
	 * 
	 * @param Alias
	 *            The Alias
	 */
	public void setAlias(String Alias) {
		this.Alias = Alias;
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
	 * @return The Description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * 
	 * @param Description
	 *            The Description
	 */
	public void setDescription(String Description) {
		this.Description = Description;
	}

	/**
	 * 
	 * @return The Keywords
	 */
	public String getKeywords() {
		return Keywords;
	}

	/**
	 * 
	 * @param Keywords
	 *            The Keywords
	 */
	public void setKeywords(String Keywords) {
		this.Keywords = Keywords;
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
	 * @return The ModifiedDate
	 */
	public String getModifiedDate() {
		return ModifiedDate;
	}

	/**
	 * 
	 * @param ModifiedDate
	 *            The ModifiedDate
	 */
	public void setModifiedDate(String ModifiedDate) {
		this.ModifiedDate = ModifiedDate;
	}

	/**
	 * 
	 * @return The FinishedDate
	 */
	public String getFinishedDate() {
		return FinishedDate;
	}

	/**
	 * 
	 * @param FinishedDate
	 *            The FinishedDate
	 */
	public void setFinishedDate(String FinishedDate) {
		this.FinishedDate = FinishedDate;
	}

	/**
	 * 
	 * @return The Featured
	 */
	public String getFeatured() {
		return Featured;
	}

	/**
	 * 
	 * @param Featured
	 *            The Featured
	 */
	public void setFeatured(String Featured) {
		this.Featured = Featured;
	}

	/**
	 * 
	 * @return The Approved
	 */
	public String getApproved() {
		return Approved;
	}

	/**
	 * 
	 * @param Approved
	 *            The Approved
	 */
	public void setApproved(String Approved) {
		this.Approved = Approved;
	}

	/**
	 * 
	 * @return The State
	 */
	public String getState() {
		return State;
	}

	/**
	 * 
	 * @param State
	 *            The State
	 */
	public void setState(String State) {
		this.State = State;
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
	 * @return The CategoryAlias
	 */
	public String getCategoryAlias() {
		return CategoryAlias;
	}

	/**
	 * 
	 * @param CategoryAlias
	 *            The CategoryAlias
	 */
	public void setCategoryAlias(String CategoryAlias) {
		this.CategoryAlias = CategoryAlias;
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

}
