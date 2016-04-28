package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class AdModels {

	private String Category;
	private String City;
	private String Title;
	private String Contents;
	private String Keywords;
	private String Mobile;
	private String Price;
	private String Picture;
	private List<String> Pictures = new ArrayList<String>();
	private String State;

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
}
