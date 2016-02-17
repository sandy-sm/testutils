package test1;

import org.apache.commons.beanutils.converters.ByteArrayConverter;
import org.apache.commons.lang3.ArrayUtils;

public class ByteArrayDemo {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String str = "This is sample string";
    byte[] b= str.getBytes();
    
    System.out.println(b.length);
    
    Byte[] logBytes = ArrayUtils.toObject(b);
    
    System.out.println(logBytes.length);
    
    byte[] newBytes= ArrayUtils.toPrimitive(logBytes);
    
    System.out.println(newBytes.length);
  }

}
