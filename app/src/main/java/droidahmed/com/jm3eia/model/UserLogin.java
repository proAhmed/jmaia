package droidahmed.com.jm3eia.model;

import java.io.Serializable;

public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Result;
	private String ID;
	private String FullName;
	private String UserName;
	private String Email;
	private String Picture;
	private String IsActive;
	private String CreatedDate;
	private String LastLogIn;
	private Object LastPassChanged;
	private String LogInCount;
	private String State;
	private String Mobile;
	private String AuthPassword;
	private String Zone;
	private String Widget;
	private String Street;
	private String House;
	private String Gada;

	public String getZone() {
		return Zone;
	}

	public void setZone(String zone) {
		Zone = zone;
	}

	public String getWidget() {
		return Widget;
	}

	public void setWidget(String widget) {
		Widget = widget;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getHouse() {
		return House;
	}

	public void setHouse(String house) {
		House = house;
	}

	public String getGada() {
		return Gada;
	}

	public void setGada(String gada) {
		Gada = gada;
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
	 * @return The FullName
	 */
	public String getFullName() {
		return FullName;
	}

	/**
	 * 
	 * @param FullName
	 *            The FullName
	 */
	public void setFullName(String FullName) {
		this.FullName = FullName;
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
	 * @return The Email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * 
	 * @param Email
	 *            The Email
	 */
	public void setEmail(String Email) {
		this.Email = Email;
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
	 * @return The IsActive
	 */
	public String getIsActive() {
		return IsActive;
	}

	/**
	 * 
	 * @param IsActive
	 *            The IsActive
	 */
	public void setIsActive(String IsActive) {
		this.IsActive = IsActive;
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
	 * @return The LastLogIn
	 */
	public String getLastLogIn() {
		return LastLogIn;
	}

	/**
	 * 
	 * @param LastLogIn
	 *            The LastLogIn
	 */
	public void setLastLogIn(String LastLogIn) {
		this.LastLogIn = LastLogIn;
	}

	/**
	 * 
	 * @return The LastPassChanged
	 */
	public Object getLastPassChanged() {
		return LastPassChanged;
	}

	/**
	 * 
	 * @param LastPassChanged
	 *            The LastPassChanged
	 */
	public void setLastPassChanged(Object LastPassChanged) {
		this.LastPassChanged = LastPassChanged;
	}

	/**
	 * 
	 * @return The LogInCount
	 */
	public String getLogInCount() {
		return LogInCount;
	}

	/**
	 * 
	 * @param LogInCount
	 *            The LogInCount
	 */
	public void setLogInCount(String LogInCount) {
		this.LogInCount = LogInCount;
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

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getAuthPassword() {
		return AuthPassword;
	}

	public void setAuthPassword(String AuthPassword) {
		AuthPassword = AuthPassword;
	}

}
