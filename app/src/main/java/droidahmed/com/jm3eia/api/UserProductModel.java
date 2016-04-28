package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class UserProductModel {

	private List<UserDResult> dResults = new ArrayList<UserDResult>();

	/**
	 * 
	 * @return The dResults
	 */
	public List<UserDResult> getDResults() {
		return dResults;
	}

	/**
	 * 
	 * @param dResults
	 *            The dResults
	 */
	public void setDResults(List<UserDResult> dResults) {
		this.dResults = dResults;
	}
}
