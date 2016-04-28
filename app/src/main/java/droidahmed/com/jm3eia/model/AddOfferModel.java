package droidahmed.com.jm3eia.model;

public class AddOfferModel {

	private String Title;
	private String Category;
	private String City;
	private String Price;
	private String Result;
	private String Contents;

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	@Override
	public String toString() {
		return "AddOfferModel [Title=" + Title + ", Category=" + Category
				+ ", City=" + City + ", Price=" + Price + ", Result=" + Result
				+ ", Contents=" + Contents + "]";
	}

}
