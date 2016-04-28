package droidahmed.com.jm3eia.api;

public class ChangePasswordModel {

	private String OldPassword;
	private String NewPassword;
	private String rNewPassword;
	private String Result;

	public String getOldPassword() {
		return OldPassword;
	}

	public void setOldPassword(String oldPassword) {
		OldPassword = oldPassword;
	}

	public String getNewPassword() {
		return NewPassword;
	}

	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}

	public String getrNewPassword() {
		return rNewPassword;
	}

	public void setrNewPassword(String rNewPassword) {
		this.rNewPassword = rNewPassword;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

}
