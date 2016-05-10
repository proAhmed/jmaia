package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class UpdateUser {

    private String FullName;
    private String UserName;
     private String Email;
    private String Mobile;
     private String Zone;
    private String Widget;
    private String Street;
    private String Gada;
    private String House;
    private String AuthUserName;
    private String AuthPassword;

    public UpdateUser(String fullName, String userName, String email, String mobile, String zone,
                      String widget, String street, String gada, String house, String authUserName,
                      String authPassword) {
        FullName = fullName;
        UserName = userName;
        Email = email;
        Mobile = mobile;
        Zone = zone;
        Widget = widget;
        Street = street;
        Gada = gada;
        House = house;
        AuthUserName = authUserName;
        AuthPassword = authPassword;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getWidget() {
        return Widget;
    }

    public void setWidget(String widget) {
        Widget = widget;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getGada() {
        return Gada;
    }

    public void setGada(String gada) {
        Gada = gada;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getAuthUserName() {
        return AuthUserName;
    }

    public void setAuthUserName(String authUserName) {
        AuthUserName = authUserName;
    }

    public String getAuthPassword() {
        return AuthPassword;
    }

    public void setAuthPassword(String authPassword) {
        AuthPassword = authPassword;
    }
}
