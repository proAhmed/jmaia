package droidahmed.com.jm3eia.api;

public class CategoriesModel {

	private String ID;
	private String Name;
	private boolean IsParent;

	public boolean isIsParent() {
		return true;
	}

	public void setIsParent(boolean isParent) {
		IsParent = isParent;
	}

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
	 * @return The Name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 
	 * @param Name
	 *            The Name
	 */
	public void setName(String Name) {
		this.Name = Name;
	}

}
