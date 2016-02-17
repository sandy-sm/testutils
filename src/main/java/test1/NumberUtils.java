package test1;

import com.mimosa.nms.Utils;

public class NumberUtils {

  public static void main(String... args) {
    Object obj = 816043779.74;
    long txBytes = ((Double) obj).longValue();
    
    System.out.println(txBytes);
    if(args.length> 0 && args[0].equalsIgnoreCase("a")){
      return;
    } 
    
    String txRate = Utils.withNumberSuffix(txBytes * 8);
    
    System.out.println(txRate);
    
    Integer checkMinusValue = -211111;
    System.out.println(checkMinusValue);
    
    int checkm = -211111;
    System.out.println(checkm);
  }
}
