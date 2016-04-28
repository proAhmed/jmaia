package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class ProductListItem {

	private List<DResult> dResults = new ArrayList<DResult>();

	/**
	 * 
	 * @return The dResults
	 */
	public List<DResult> getDResults() {
		return dResults;
	}

	/**
	 * 
	 * @param dResults
	 *            The dResults
	 */
	public void setDResults(List<DResult> dResults) {
		this.dResults = dResults;
	}

}
