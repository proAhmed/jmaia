package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 4/4/2016.
 */
public class SignIn {
    private int ID;
    private String FullName;
    private String UserName;
    private String Picture;
    private String Email;
    private int Mobile;
    private int IsActive;
    private String CreatedDate;
    private String ModifiedDate;
    private String LastLogIn;
    private String LastPassChanged;
    private int State;
    private String Zone;
    private String Widget;
    private String Street;
    private String Gada;
    private String House;
    private String AuthUserName;
    private String AuthPassword;

    public SignIn() {
    }

    public SignIn(int ID, String fullName, String userName, String picture,
                  String email, int mobile, int isActive, String createdDate, String modifiedDate,
                  String lastLogIn, String lastPassChanged, int state, String zone, String widget,
                  String street, String gada, String house, String authUserName, String authPassword) {
        this.ID = ID;
        FullName = fullName;
        UserName = userName;
        Picture = picture;
        Email = email;
        Mobile = mobile;
        IsActive = isActive;
        CreatedDate = createdDate;
        ModifiedDate = modifiedDate;
        LastLogIn = lastLogIn;
        LastPassChanged = lastPassChanged;
        State = state;
        Zone = zone;
        Widget = widget;
        Street = street;
        Gada = gada;
        House = house;
        AuthUserName = authUserName;
        AuthPassword = authPassword;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMobile() {
        return Mobile;
    }

    public void setMobile(int mobile) {
        Mobile = mobile;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getLastLogIn() {
        return LastLogIn;
    }

    public void setLastLogIn(String lastLogIn) {
        LastLogIn = lastLogIn;
    }

    public String getLastPassChanged() {
        return LastPassChanged;
    }

    public void setLastPassChanged(String lastPassChanged) {
        LastPassChanged = lastPassChanged;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
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
