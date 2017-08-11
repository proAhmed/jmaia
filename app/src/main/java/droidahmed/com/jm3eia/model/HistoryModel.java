package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 1/21/2017.
 */
public class HistoryModel {

  private int  ID;
  private int UserID;
  private Object  VisitorData;
  private int  Payment_Method;
  private String TotalPrice;
  private String CreatedDate;
  private String ModifiedDate;
  private String OrderDate;
  private String State;
  private int ProductsCount;

  public HistoryModel() {
  }

  public HistoryModel(int ID, int userID, Object visitorData, int payment_Method,
                      String totalPrice, String createdDate, String modifiedDate,
                      String orderDate, String state, int productsCount) {
    this.ID = ID;
    UserID = userID;
    VisitorData = visitorData;
    Payment_Method = payment_Method;
    TotalPrice = totalPrice;
    CreatedDate = createdDate;
    ModifiedDate = modifiedDate;
    OrderDate = orderDate;
    State = state;
    ProductsCount = productsCount;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public int getUserID() {
    return UserID;
  }

  public void setUserID(int userID) {
    UserID = userID;
  }

  public Object getVisitorData() {
    return VisitorData;
  }

  public void setVisitorData(Object visitorData) {
    VisitorData = visitorData;
  }

  public int getPayment_Method() {
    return Payment_Method;
  }

  public void setPayment_Method(int payment_Method) {
    Payment_Method = payment_Method;
  }

  public String getTotalPrice() {
    return TotalPrice;
  }

  public void setTotalPrice(String totalPrice) {
    TotalPrice = totalPrice;
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

  public String getOrderDate() {
    return OrderDate;
  }

  public void setOrderDate(String orderDate) {
    OrderDate = orderDate;
  }

  public String getState() {
    return State;
  }

  public void setState(String state) {
    State = state;
  }

  public int getProductsCount() {
    return ProductsCount;
  }

  public void setProductsCount(int productsCount) {
    ProductsCount = productsCount;
  }
}
