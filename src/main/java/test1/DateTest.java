package test1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTest {
  
  public static void main(String... args) {
    System.out.println("usage: java DateTest {timezone} {dateFormat}..");
    DateTimeFormatter formatter = DateTimeFormat.forPattern(args[1]);
    
    long utcTimestamp = System.currentTimeMillis();
    
    DateTime dateTime = new DateTime(utcTimestamp);
    DateTime localTime = new DateTime(dateTime, DateTimeZone.forID(args[0].split(",")[0]));
    
    System.out.println(DateTimeZone.forID(args[0].split(",")[0]));
    System.out.println(localTime);
    
    String formattedDate= new SimpleDateFormat(args[1]).format(localTime.toDate());
    
    System.out.println(formattedDate);
  }
}
