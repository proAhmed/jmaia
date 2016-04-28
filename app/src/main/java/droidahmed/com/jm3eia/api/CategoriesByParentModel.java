package droidahmed.com.jm3eia.api;

public class CategoriesByParentModel {

	private String ID;
	private String Name;
	private String ParentID;
	private String Alias;
	private String Level;
	private int IsParent;
	private boolean TopLevel;
	private String SortingOrder;
	private String State;

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

	/**
	 * 
	 * @return The ParentID
	 */
	public String getParentID() {
		return ParentID;
	}

	/**
	 * 
	 * @param ParentID
	 *            The ParentID
	 */
	public void setParentID(String ParentID) {
		this.ParentID = ParentID;
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
	 * @return The Level
	 */
	public String getLevel() {
		return Level;
	}

	/**
	 * 
	 * @param Level
	 *            The Level
	 */
	public void setLevel(String Level) {
		this.Level = Level;
	}

	/**
	 * 
	 * @return The IsParent
	 */
	public int isIsParent() {
		return IsParent;
	}

	/**
	 * 
	 * @param IsParent
	 *            The IsParent
	 */
	public void setIsParent(int IsParent) {
		this.IsParent = IsParent;
	}

	/**
	 * 
	 * @return The TopLevel
	 */
	public boolean isTopLevel() {
		return TopLevel;
	}

	/**
	 * 
	 * @param TopLevel
	 *            The TopLevel
	 */
	public void setTopLevel(boolean TopLevel) {
		this.TopLevel = TopLevel;
	}

	/**
	 * 
	 * @return The SortingOrder
	 */
	public String getSortingOrder() {
		return SortingOrder;
	}

	/**
	 * 
	 * @param SortingOrder
	 *            The SortingOrder
	 */
	public void setSortingOrder(String SortingOrder) {
		this.SortingOrder = SortingOrder;
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
