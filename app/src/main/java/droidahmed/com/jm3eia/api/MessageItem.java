package droidahmed.com.jm3eia.api;

public class MessageItem {

	private String ID;
	private String FromUser;
	private String ToUser;
	private String Message;
	private String CreatedDate;
	private String IsRead;
	private String OfferID;
	private String FromUserName;
	private String SinceDate;

	public String getSinceDate() {
		return SinceDate;
	}

	public void setSinceDate(String sinceDate) {
		SinceDate = sinceDate;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
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
	 * @return The FromUser
	 */
	public String getFromUser() {
		return FromUser;
	}

	/**
	 * 
	 * @param FromUser
	 *            The FromUser
	 */
	public void setFromUser(String FromUser) {
		this.FromUser = FromUser;
	}

	/**
	 * 
	 * @return The ToUser
	 */
	public String getToUser() {
		return ToUser;
	}

	/**
	 * 
	 * @param ToUser
	 *            The ToUser
	 */
	public void setToUser(String ToUser) {
		this.ToUser = ToUser;
	}

	/**
	 * 
	 * @return The Message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * 
	 * @param Message
	 *            The Message
	 */
	public void setMessage(String Message) {
		this.Message = Message;
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
	 * @return The IsRead
	 */
	public String getIsRead() {
		return IsRead;
	}

	/**
	 * 
	 * @param IsRead
	 *            The IsRead
	 */
	public void setIsRead(String IsRead) {
		this.IsRead = IsRead;
	}

	/**
	 * 
	 * @return The OfferID
	 */
	public String getOfferID() {
		return OfferID;
	}

	/**
	 * 
	 * @param OfferID
	 *            The OfferID
	 */
	public void setOfferID(String OfferID) {
		this.OfferID = OfferID;
	}

}
