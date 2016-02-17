package test1;

public class ActivationInfo {
  private String serialNumber;
  private String product;
  private String country;
  private String unlockCode;
  private long orgId;
  private String username;
  
  public ActivationInfo(String serialNumber, String product, String country, String unlockCode, long orgId, String userName) {
    this.serialNumber = serialNumber;
    this.product = product;
    this.country = country;
    this.unlockCode = unlockCode;
    this.orgId = orgId;
    this.username = userName;
  }
  
  public String getSerialNumber() {
    return serialNumber;
  }

  public String getProduct() {
    return product;
  }

  public String getCountry() {
    return country;
  }

  public String getUnlockCode() {
    return unlockCode;
  }

  public long getOrgId() {
    return orgId;
  }

  public String getUsername() {
    return username;
  }

  
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setUnlockCode(String unlockCode) {
    this.unlockCode = unlockCode;
  }

  public void setOrgId(long orgId) {
    this.orgId = orgId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "ActivationInfo [serialNumber=" + serialNumber + ", product=" + product + ", country=" + country
        + ", unlockCode=" + unlockCode + ", orgId=" + orgId + ", username=" + username + "]";
  }

}
