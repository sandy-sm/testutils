package test1;

import com.mimosa.nms.model.action.Result;
import com.mimosa.nms.model.user.UIDDetails.UIDType;




public class EnumUtils {

  
  public static void main(String[] args){
    Result prevSeverity = Result.OK;
    
    Result newSeverity = Result.FAILED;
    
    if(prevSeverity != newSeverity) {
      System.out.println("new severity: "+ newSeverity);
    }
    
    System.out.println("a"+UIDType.User_Invite_Registration.ordinal());
    
    String model = "B5Lite";
    System.out.println(org.apache.commons.lang3.EnumUtils.isValidEnum(Products.class, model) ? Products.valueOf(model).getLabel() : model);
  }
}
