package droidahmed.com.jm3eia.api;

public class PayedModel {

	private String BankUserName;
	private String BankName;
	private String BankNumber;

	public String getBankUserName() {
		return BankUserName;
	}

	public void setBankUserName(String bankUserName) {
		BankUserName = bankUserName;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		this.BankName = bankName;
	}

	public String getBankNumber() {
		return BankNumber;
	}

	public void setBankNumber(String bankNumber) {
		BankNumber = bankNumber;
	}

}
