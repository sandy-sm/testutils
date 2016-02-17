package test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class LineChecker {

  public static void main(String []args){
    String str = "abc,B5";
    System.out.println(str.matches("(\\d+),(\\w+)"));
  }
  
//  @RequestMapping(value = "/supportLogDemo", method = RequestMethod.GET)
//  public void fetchLogDownloda(HttpServletRequest request, HttpServletResponse response) throws IOException{
//      String filePath = "/home/sandeep/supportlog/1010101010.tar";
//      File fileToDownload = new File(filePath);
//      InputStream inputStream = new FileInputStream(fileToDownload);
//      
//      // set content attributes for the response
//      response.setContentType("application/octet-stream");
//      response.setContentLength((int) fileToDownload.length());
//
//      // set headers for the response
//      String headerKey = "Content-Disposition";
//      //String headerValue = String.format("attachment; filename=\"%s\"",serialNumber+".tar");
//      String headerValue = String.format("attachment; filename=1010101010.tar");
//      response.setHeader(headerKey, headerValue);
//
//      IOUtils.copy(inputStream, response.getOutputStream());
//      inputStream.close();
//  }
}
