package test1;


public class ObjectDemo {

  public static void main(String[] args){
    
    long org = 1L;
    Object orgId = org;
    
    System.out.println((long) orgId);
    
    Object orgId1 = 1;
    
    System.out.println((long) orgId1);
    
    ActivationInfo invalidActivationInfo = new ActivationInfo("10112121212", "--", "India", "INVALID SERIAL #", 1, "");
    System.out.println(invalidActivationInfo);
    ActivationInfo reference = invalidActivationInfo;
    
    invalidActivationInfo.setCountry("US");
    
    System.out.println(invalidActivationInfo);
    System.out.println(reference);
      
  }
  
}
