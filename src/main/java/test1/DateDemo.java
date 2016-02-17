package test1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.mimosa.nms.Utils;
import com.mimosa.nms.common.NmsConstants;

public class DateDemo {

  public static void main(String[] args) throws ParseException {
    
    final String PATTERN = "dd/MMM/yyyy hh:mm a (z)";
    long utcTimestamp = System.currentTimeMillis();
    
    String formatedDateTime = Utils.convertUTCToLocalDateWithFormat(utcTimestamp, "Australia/Brisbane", PATTERN);
    
    System.out.println(formatedDateTime);
    System.out.println(TimeZone.getDefault().getID());
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    Calendar cal = Calendar.getInstance();
    // get the time in milliseconds
    System.out.println("Current time is :" + new Date(1446013224315L));
    // set time to 5000 ms after january 1 1970
    cal.setTimeInMillis(1438713000000L);

    // print the new time
    
    
    System.out.println("After setting Time:  " + cal.getTime());
    
    Date cDate = new Date(1438713000000L);
    System.out.println(cDate);
    
    Calendar sc = Calendar.getInstance();
    sc.setTimeInMillis(1439980934000L);
    
    System.out.println(sc.getTime());
    
    
    Date startDate = sc.getTime();
    
    System.out.println(startDate);
    
    //sc.add(Calendar.DAY_OF_MONTH, 441);
    
    System.out.println(sc.getTime());
    
    sc.add(Calendar.DAY_OF_YEAR, 365);
    
    System.out.println(sc.getTime());
    
    String tz = DateTimeZone.forID("America/Los_Angeles").getShortName(System.currentTimeMillis());
    
    System.out.println(tz);
    
    String timezoneIdAsia= "Asia/Kolkata";
    String timezoneeIdAmerica = "America/Los_Angeles";

//    DateTimeZone timezone = DateTimeZone.forID(timezoneId.split(",")[0]);   //used to parse {timezoneId},{timezoneCode},{timzoneoffset}
//    DateTime dateTime = new DateTime(new Date(utcTimestamp));
//    DateTime localTime = dateTime.toDateTime(timezone);
//    
//    TimeZone.setDefault(TimeZone.getTimeZone(timezone.getID()));
//    Date localDateTime = localTime.toLocalDateTime().toDateTime(timezone).toDate();
    
    DateTimeZone timezone = DateTimeZone.forID(timezoneeIdAmerica);   //used to parse {timezoneId},{timezoneCode},{timzoneoffset}
    DateTime dateTime = new DateTime(new Date(System.currentTimeMillis()));
    
    DateTime localTime = new DateTime(dateTime, DateTimeZone.forID(timezoneeIdAmerica));
    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm a (z)");
    
    System.out.println(formatter.print(localTime));
    System.out.println(TimeZone.getDefault().getID());
    System.out.println("currentDate :" + new Date(System.currentTimeMillis()));
    
    String pattern[] =  new String[]{"MM/dd/yyyy hh:mm a (z)", 
        "dd/MM/yyyy hh:mm a (z)",
        "MM/dd/yy hh:mm a (z)",
        "dd/MM/yy hh:mm a (z)",
        "MM-dd-yyyy hh:mm a (z)",
        "dd-MM-yyyy hh:mm a (z)",
        "MM-dd-yy hh:mm a (z)",
        "dd-MM-yy hh:mm a (z)",
        "dd.MM.yyyy hh:mm a (z)",
        "yyyy-MM-dd hh:mm a (z)",
        "MMM dd yyyy hh:mm a (z)",
        "dd/MMM/yyyy hh:mm a (z)",
        "yyyy/MMM/dd hh:mm a (z)",
        "M/d/yyyy hh:mm a (z)",
        "M/d/yy hh:mm a (z)",
        "d/M/yyyy hh:mm a (z)",
        "d/M/yy hh:mm a (z)",
        "yy/M/d hh:mm a (z)",
        "yyyy/M/d hh:mm a (z)",
        "yy/MM/dd hh:mm a (z)",
        "yyyy/MM/dd hh:mm a (z)"
        };
    
    for(String p : pattern) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p);
      String date = simpleDateFormat.format(new Date());
      System.out.println("format: " + date);
    }
    
    
    Date today = new Date();
    //displaying this date on IST timezone
    DateFormat df = new SimpleDateFormat("MM-dd-yy (z ZZZZ)");
    df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
    String IST = df.format(today);
    System.out.println("Date in Indian Timezone (IST) : " + IST);
  
    //dispalying date on PST timezone
    df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
    String PST = df.format(today);
    System.out.println("Date in PST Timezone : " + PST);
    
    
    String mfgDate = "2015-07-02 21:42:24 (UTC +0000)";
    //2015-12-07 03:59:36 (UTC +0000)
    
    SimpleDateFormat mfgformat = new SimpleDateFormat(NmsConstants.DATEFORMAT_FROM_DEVICE); //"yyyy-MM-dd HH:mm:ss (z ZZZZ)"
    Date newDate = mfgformat.parse(mfgDate);
    
    
    System.out.println("rebot: "+newDate);
    System.out.println(new SimpleDateFormat("MM/dd/yyyy @ h:mm a z").format(newDate));
    
    String date1 = "2015/1/15";
    
    String date2 = "2015/2/11";
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d");
    Date startDateObj = format.parse(date1);
    Date endDateObj = format.parse(date2);
    
    if(endDateObj.before(startDateObj)){
      System.out.println("Invalid date range: start date should be before end date");
      System.exit(0);
    }
    
    System.out.println("startDate: "+ startDateObj + "\t endDate: "+ endDateObj);
    
    Calendar startDateCalendar = Calendar.getInstance();
    Calendar endDateCalendar = Calendar.getInstance();
    
    startDateCalendar.setTime(startDateObj);
    endDateCalendar.setTime(endDateObj);
    
    long days = new Duration(endDateCalendar.getTimeInMillis()).minus(startDateCalendar.getTimeInMillis()).getStandardDays();
    
    System.out.println("total days: "+ days);
    int index = 0;
    while(startDateCalendar.compareTo(endDateCalendar)<= 0) {
      System.out.println(format.format(startDateCalendar.getTime()));
      startDateCalendar.add(Calendar.DAY_OF_MONTH, 1);
      System.out.println("percentage: "+(float)++index/days *100+"%");
    }
    System.out.println(endDateCalendar);
    
    
  }

}
