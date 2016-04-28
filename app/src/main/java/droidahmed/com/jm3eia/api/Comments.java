package droidahmed.com.jm3eia.api;

public class Comments {

	private String ID;
	private String Contents;
	private String CreatedDate;
	private String UserName;
	private String Picture;

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

}
