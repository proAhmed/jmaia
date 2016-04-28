package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class InputChangePassword {
    private String OldPassword;
    private String NewPassword;
    private String rNewPassword;

    public InputChangePassword() {
    }

    public InputChangePassword(String oldPassword, String newPassword, String rNewPassword) {
        OldPassword = oldPassword;
        NewPassword = newPassword;
        this.rNewPassword = rNewPassword;
    }

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
}
