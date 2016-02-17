package test1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.*;



public class MapDemo {

  
  public static void main(String[] args) {
    
    HashMap<String, String> stringMap = new HashMap<String, String>();
    
    stringMap.put("1", "ONE");
    stringMap.put("1", "DIGIT_ONE");
    
    
    org.apache.commons.collections.FastHashMap stringMultiMap= new FastHashMap(stringMap);
    
    
    Set<String> keys = stringMultiMap.keySet();
    System.out.println(keys);
    
    for(String key : keys) {
      System.out.println(stringMultiMap.get(key));
    }
  }
}
