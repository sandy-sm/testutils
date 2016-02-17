package test1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import antlr.Utils;

import com.mimosa.nms.model.action.Result;
import com.mimosa.nms.model.device.Device;
import com.mimosa.nms.services.action.ActionResponse;
import com.mimosa.nms.services.action.notifier.DeviceAlertData;
import com.mimosa.nms.services.action.notifier.DeviceAlertPublisher;
import com.mimosa.nms.services.action.notifier.DeviceAlertData.Notifier;


public class StringDemo {

  public static String getModel(String serial, String pattern) {
    
    String model = null;
    for(String modelMap: pattern.split(";")) {
      String[] productSerial = modelMap.split(":");
      String modelName = productSerial[0];
      String serialPrefixNumber = productSerial[1];
      
      for(String serialPrefix : serialPrefixNumber.split(",")) {
        boolean foundModel = serial.substring(0, Math.min(3, serialPrefix.length())).equalsIgnoreCase(serialPrefix);
        if(foundModel) {
          return modelName;
        }
      }
    }
    
    return model;
  }
  
  private static List<ActionResponse> getActionResponses(JsonNode tree) {
    List<ActionResponse> responses = new ArrayList<>();
    try {
      Iterator<JsonNode> iter = tree.path("responses").getElements();
      while (iter.hasNext()) {
        JsonNode node = iter.next();
        ActionResponse actionResponse = new ObjectMapper().readValue(node, ActionResponse.class);
        responses.add(actionResponse);
      }
    } catch (IOException exception) {
      System.out.println(exception);
    }

    return responses;
  }
  
  private static String readFromStream(InputStream in) throws IOException {
    String output;
    try (Scanner scanner = new Scanner(in, "UTF-8")) {
      output = (in.available() > 0) ? scanner.useDelimiter("\\Z").next() : null;
      in.close();
    }
    return output == null ? null : output.trim();
  }
  
  public static void main(String[] args) throws Exception {
    
    String sample = null;
    
    sample.concat(2+",");
    
    System.out.println(sample);
    
    String key = "device_info#SerialNumber";
    
    System.out.println(key.replaceAll("#[a-zA-Z]+#", "#"));
    
    System.out.println("MAC: " +  "20:b5:c6:00:9b:f1".replaceAll(":", "-").substring(0, 8));
/*    
    String keycode =UnlockKeyCodeGenerator.getKeyCode("1006890588", 369);
    
    String serial_num = "8826890588";
    boolean isMatch = false;
    for(int i =1; i< 999; i++) {
      Process p = Runtime.getRuntime().exec("sh /home/sandeep/Desktop/genUnlockCode.sh " + serial_num + " " + i);
      
      p.waitFor();
      
      String kccli = readFromStream(p.getInputStream());
      String kc =UnlockKeyCodeGenerator.getKeyCode(serial_num, i);
      
      
      if(!kccli.equalsIgnoreCase(kc)){
        System.out.println("country code: "+ i);
        System.out.println(kc + "-"+ kccli);
        isMatch = true;
      }
    }*/
    
   /* if(isMatch) {
      System.out.println("Mismatch found");
    }
    */
    
    List<String> features = Arrays.asList(new String[] {"UserSchedules"}); 
    
    System.out.println(features.contains("UserSchedules"));
    
    List<String> emails = Arrays.asList(new String[] {"UserSchedules", "abc", "xyz"});
    
    String[] emailsArr = (String[]) emails.toArray();
    
    for(String e: emailsArr){
      System.out.println(e);
    }
    
    
    String s3Link = "s3://nms-raw-alpha/Reports/2/snapshot.pdf";
        
    System.out.println(s3Link.replaceAll("s3://(.*?)/", ""));
    
    System.out.println(s3Link);
    System.out.println(Result.OK.ordinal() %3);
    System.out.println(Result.FAILED.ordinal()%3);
    
    System.out.println(Result.WARNING.ordinal() %3);
    System.out.println(Result.CRITICAL.ordinal() %3);
    
    long id = 123;
    Object deviceId = id;
    
    System.out.println((Long)deviceId);
    
    Object stringSample = "Mimosa";
    
    System.out.println(stringSample.toString());
    
    String clientInfo = "127.0.0.1,191.1.1.1,222.2.2.2";
    
    List<String> arrayList = new ArrayList<String>(); 
        
    arrayList.addAll(Arrays.asList(clientInfo.split(",")));
    
    System.out.println(arrayList);
    
    arrayList.add("121.1.1.1");
    
    String sarray = com.mimosa.nms.Utils.asReadableString(arrayList.toArray(new String[0]), ",");
    
    System.out.println("s: "+ sarray);
    
    Device device = new Device();
    
    device.setDeviceType(com.mimosa.nms.model.device.Device.DeviceType.Backhaul);
    device.setManufacturerName("Ubiquiti");
    
    if (!(StringUtils.equalsIgnoreCase(device.getDeviceType().name(), "Backhaul") && StringUtils.equalsIgnoreCase(device.getManufacturerName(), "Mimosa"))) {
      System.out.println("non mimosa only");
    } else {
      System.out.println("mimosa bug");
    }
    
    
    /************************** StatusChange ***********************************/
    
    DeviceAlertData statusChangeEvent = new DeviceAlertData();
    statusChangeEvent.setTimestamp(System.currentTimeMillis());
    statusChangeEvent.setNotifier(Notifier.StatusChange);
    String date = com.mimosa.nms.Utils.convertUTCToLocalDateWithFormat(System.currentTimeMillis(), "Asia/Kolkata", null);
    
    new DeviceAlertPublisher().constructDeviceDetailsForStatusChange(statusChangeEvent, Result.OK, Result.FAILED, (long) deviceId, date);
    
    System.out.println(new ObjectMapper().writeValueAsString(statusChangeEvent));
    String dummyEvent = "{\"timestamp\":1442836434268,\"notifier\":\"StatusChange\",\"additionalData\":{\"timestampByNetworkZone\":\"Sep 21 2015 05:23 PM (IST)\",\"newSeverity\":\"FAILED\",\"deviceId\":123,\"prevSeverity\":\"OK\"}}";
    DeviceAlertData data = new ObjectMapper().readValue(dummyEvent, DeviceAlertData.class);
    
    System.out.println("statuschange: "+ data.toString());
    
    /************************** SeverityChange ***********************************/
    DeviceAlertData severityChangeEvent = new DeviceAlertData();
    severityChangeEvent.setTimestamp(System.currentTimeMillis());
    severityChangeEvent.setNotifier(Notifier.SeverityChange);
    String date2 = com.mimosa.nms.Utils.convertUTCToLocalDateWithFormat(System.currentTimeMillis(), "Asia/Kolkata", null);
    
    

    System.out.println(new ObjectMapper().writeValueAsString(severityChangeEvent));
    
    String dummyEvent2 = "{\"timestamp\":1442837105753,\"notifier\":\"SeverityChange\",\"additionalData\":{\"actionName\":\"Mimosa_B5_Stats\",\"timestampByNetworkZone\":\"Sep 21 2015 05:35 PM (IST)\",\"responses\":[{\"id\":null,\"created\":null,\"modified\":null,\"createdBy\":null,\"modifiedBy\":null,\"organization\":null,\"deviceId\":0,\"friendlyDeviceName\":null,\"actionName\":\"abc\",\"type\":null,\"param\":null,\"result\":null,\"data\":\"xaf\",\"timestamp\":1442837105754,\"additionalData\":{}},{\"id\":null,\"created\":null,\"modified\":null,\"createdBy\":null,\"modifiedBy\":null,\"organization\":null,\"deviceId\":0,\"friendlyDeviceName\":null,\"actionName\":\"abc\",\"type\":null,\"param\":null,\"result\":null,\"data\":\"xaf\",\"timestamp\":1442837105754,\"additionalData\":{}},{\"id\":null,\"created\":null,\"modified\":null,\"createdBy\":null,\"modifiedBy\":null,\"organization\":null,\"deviceId\":0,\"friendlyDeviceName\":null,\"actionName\":\"abc\",\"type\":null,\"param\":null,\"result\":null,\"data\":\"xaf\",\"timestamp\":1442837105754,\"additionalData\":{}},{\"id\":null,\"created\":null,\"modified\":null,\"createdBy\":null,\"modifiedBy\":null,\"organization\":null,\"deviceId\":0,\"friendlyDeviceName\":null,\"actionName\":\"abc\",\"type\":null,\"param\":null,\"result\":null,\"data\":\"xaf\",\"timestamp\":1442837105754,\"additionalData\":{}}],\"deviceId\":6209}}";
    DeviceAlertData data2 = new ObjectMapper().readValue(dummyEvent2, DeviceAlertData.class);

    System.out.println("severitychange: "+ data2.toString());

    System.out.println("responses : "+ data2.getAdditionalData().get("responses").toString());
    
    List<ActionResponse> lst = (ArrayList<ActionResponse>) data2.getAdditionalData().get("responses");
    
    System.out.println(lst);
    
    /*******************/
    String band = "1x20 FD";
    Object object = "StringDemo";
    
    System.out.println("class: "+ (String)object);
    
    if(band!= null && band.charAt(0) != '1' || org.apache.commons.lang.StringUtils.contains(band, "FD")){
      System.out.println(band);
    }
    
    List<String> applicableFeaters = new ArrayList<String>(Arrays.asList(new String[] {"F1","F2"}));
    List<String> checkFeatures = new ArrayList<String>(Arrays.asList(new String[] {"F1","F2"}));
    System.out.println("l2:"+checkFeatures);
    System.out.println("l1:"+applicableFeaters);
    
    checkFeatures.removeAll(applicableFeaters);
    
    if(checkFeatures.size() != 0) {
      throw new Exception("Feature not supported");
    }
    
    String sorts = "a,bd,adsf,asdf,asdfg,c,d";
    for(String s : sorts.split(",")){
      System.out.println(s);
    }
    
    
String queryString = "sso=bm9uY2U9Y2I2ODI1MWVlZmI1MjExZTU4YzAwZmYxMzk1ZjBjMGI%3D%0A&sig=2828aa29899722b35a2f191d34ef9b3ce695e0e6eeec47deb46d588d70c7cb56";
    
    String[] params = queryString.split("&sig=");
    String sso = params[0].replaceAll("sso=", "");
    
    System.out.println(sso);
    System.out.println(params[1]);
    
    
    
    String provider = "design-too2l";
    
    switch(provider) {
    case "discourse" :  System.out.println(provider);
                        break;
    case "design-tool" : System.out.println(provider);
                        break;
    }
    
    
    
    
    
    String pattern = "B5-Lite:102,88;B5c-Lite:402;B5:10,889;B5c:40,882";
    String serial1 = "4024545454";
    
    System.out.println(StringDemo.getModel(serial1, pattern));
    
    String serialStr = "1028888888";
    
    StringBuilder builder = new StringBuilder(serialStr);
    builder.insert(2, '-');
    builder.insert(7, '-');
    
    System.out.println("formatted serial : "+builder.toString());
    
    //X98-NNE-5L9
    String unlockCode = "X98NNE5L9";
    
    builder = new StringBuilder(unlockCode);
    builder.insert(3, '-');
    builder.insert(7, '-');
    
    System.out.println("formatted unlock code: "+ builder.toString());
    
    String line1 = "dange.sandip@gmail.com_1433155115576.json";
    
    System.out.println(line1.replaceAll("_.*", "").trim());
    
    String[] listStr = new String[] {"India Licensed", "India"};
    
    
    List<String> lstContains = new ArrayList<String>();
    
    //lstContains.add("NONE");
    String testContains = "India Licensed";
    
    System.out.println(testContains.contains("Licensed"));
    System.out.println(lstContains.contains("NONE"));
    
    String testBlank = "10  2222  2222";
    
    System.out.println(org.apache.commons.lang3.StringUtils.replaceEachRepeatedly(testBlank, new String[]{" "}, new String[]{""})); 
    System.out.println(testBlank.trim());
    
    String line = "gateway-s3Data/2015/1/1/Phystats/0/0000000307/Phystats_1420848158649.json";
    
    Matcher timeStampMatcher = Pattern.compile("_\\d{13}.").matcher(line);
    
    Matcher eventMatcher = Pattern.compile("/([a-zA-Z]+)/").matcher(line);
    
    if(eventMatcher.find()) {
      System.out.println("event type: "+ eventMatcher.group(0).replaceAll("/", ""));
    }
    
    if(timeStampMatcher.find()) {
      String timeStamp = timeStampMatcher.group(0).replaceAll("_", "").replaceFirst("\\.", "");
      System.out.println(timeStamp);
      long ts = Long.parseLong(timeStamp);
      
      System.out.println(timeStamp);
      System.out.println(ts);
      System.out.println(new Date(ts));
    }
    
    Matcher serialMatcher = Pattern.compile("\\/\\d{10}\\/").matcher(line);
    if(serialMatcher.find()) {
      System.out.println(serialMatcher.group(0).replaceAll("\\/", ""));
    }
    
    StringBuffer strBuff = formatLine(line);
    System.out.println(strBuff.toString());
    
    line = strBuff.toString();
    Matcher matcher = Pattern.compile("(?=\\/0\\d{1}\\/)\\/0\\d{1}\\/").matcher(line);
    StringBuffer strBuff2 = new StringBuffer(line.length());
    while(matcher.find()) {
      String m = matcher.group().replaceAll("\\/", "").replaceFirst("0", "");
      matcher.appendReplacement(strBuff2, "/"+m+"/");
    }
    matcher.appendTail(strBuff2);
    System.out.println(strBuff2.toString());
    
    String str1 = "United Kingdom", str2;
    String substr = "%", regex = " Licensed";
    
    // prints string1
    if(StringUtils.endsWithIgnoreCase(str1, "licensed")) {
      str2 = str1.replaceAll(regex, substr);
    } else {
      str2 = str1.concat("%");
    }
      
      
    System.out.println("String = " + StringUtils.endsWithIgnoreCase(str1, "Licensed"));
    /* replaces each substring of this string that matches the given
    regular expression with the given replacement */
    System.out.println("After Replacing = " + str2);
    
    
    String sprefix = "102";
    String serial = "102132123123";
    String serial2 = "88151212121";
    
    boolean abc = false;
    for(String prefix : sprefix.split(",")) {
      if(serial2.startsWith(prefix)){
        abc = true;
        break;
      }
    }
    
    System.out.println("isB5lite : "+abc);
  }

  private static StringBuffer formatLine(String line) {
    Matcher matcher = Pattern.compile("(?=\\/\\d{1}\\/)\\/\\d{1}\\/").matcher(line);
    StringBuffer strBuff = new StringBuffer(line.length());
    while(matcher.find()) {
      String m = "0".concat(matcher.group().replaceAll("\\/", ""));
      matcher.appendReplacement(strBuff, "/"+m+"/");
    }
    matcher.appendTail(strBuff);
    return strBuff;
  }
}
