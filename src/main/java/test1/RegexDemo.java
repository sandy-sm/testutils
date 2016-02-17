package test1;

import java.util.regex.Pattern;

public class RegexDemo {

  public static void main(String... args) {
    
    final String _input = "A5:20;B5-Lite:102,88;B5c-Lite:402;B5:10,00;B5c:40;B11:111,112;C5:30";
    
    String extract = _input;
    
    String serial = extract.replaceAll("\\s[^\\d*,\\d*];", "");
    
    System.out.println(serial);
    
  }
}
