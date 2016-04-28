package droidahmed.com.jm3eia.api;

import java.util.ArrayList;
import java.util.List;

public class productList {

	private List<FavoriteItem> list = new ArrayList<FavoriteItem>();
	private String Result;

	public List<FavoriteItem> getList() {
		return list;
	}

	public void setList(List<FavoriteItem> list) {
		this.list = list;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

}
