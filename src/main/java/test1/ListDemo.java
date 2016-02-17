/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import com.mimosa.nms.model.user.UserCountryPhone;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sandeep
 */
public class ListDemo {
 
  public static void main(String[] args) {
    
    List<UserCountryPhone> lstUcps = new ArrayList<>();
    
    UserCountryPhone ucp1 = new UserCountryPhone();
    ucp1.setCountry("US");
    ucp1.setCountryName("United States");
    ucp1.setOrganizationId(2);
    ucp1.setUserId(2);
    
    System.out.println("contains: "+ lstUcps.contains(ucp1));
    lstUcps.add(ucp1);
    
    
    UserCountryPhone ucp2 = new UserCountryPhone();
    ucp2.setCountry("US");
    ucp2.setCountryName("United States");
    ucp2.setOrganizationId(2);
    ucp2.setUserId(2);
    
    System.out.println("contains: "+ lstUcps.contains(ucp2));
    //lstUcps.add(ucp2);
    
    
    
    
  }
}
