package test1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.mimosa.nms.services.metadata.MACVendorCacheService;


public class FileDemo {
  
    static String FILE_LOCATION_OUI = "/home/sandeep/Documents/mimosa-docs/a5-clients-tab/oui.txt";
    static String FILE_LOCATION_OUI_JSON = "/home/sandeep/Documents/mimosa-docs/a5-clients-tab/oui.json";

      public static void main(String[] argv) throws TranscoderException, IOException {
        //InputStream compressedInputStream = new FileInputStream(new File(FILE_LOCATION_OUI));
        //byte[] decompressedBytes = IOUtils.toByteArray(compressedInputStream);
        
        List<String> lines = java.nio.file.Files.readAllLines(Paths.get(FILE_LOCATION_OUI), Charset.forName("UTF-8"));
        List<MACProduct> macProductMap = new ArrayList();  
        
        for(String line : lines) {
          if(StringUtils.contains(line, "(hex)")){
            String macProduct = line.replaceAll("\\(hex\\)", "");   //.replaceAll("\\s+", ",");
            String[] macProducts = macProduct.split("\\s+",2);
            macProductMap.add(new MACProduct(macProducts[0], macProducts[1]));
          }
        }
        
        MACVendorCacheService macVendorCacheService = new MACVendorCacheService();
        macVendorCacheService.reloadMacVendorCache(); 
        String vendor = macVendorCacheService.getVendor("20:B5:C6:00:8E:6A");
        //new ObjectMapper().writeValue(new File(FILE_LOCATION_OUI_JSON), macProductMap);
        
        System.out.println("Vendor: "+vendor);
        System.out.println("Done!");
         /* Transcoder transcoder = new JPEGTranscoder();
          TranscoderInput transcoderInput = new TranscoderInput(new FileInputStream(new File("/home/sandeep/Documents/mimosa-docs/report-template/test1.svg")));
          TranscoderOutput transcoderOutput = new TranscoderOutput(new FileOutputStream(new File("/home/sandeep/Documents/mimosa-docs/report-template/test1.jpeg")));
          
          transcoder.transcode(transcoderInput, transcoderOutput);*/
      }
      
      
      static public class MACProduct implements Serializable{
        String mac;
        String vendor;
        public MACProduct(String mac, String vendor) {
          super();
          this.mac = mac;
          this.vendor = vendor;
        }
        public String getMac() {
          return mac;
        }
        public void setMac(String mac) {
          this.mac = mac;
        }
        public String getVendor() {
          return vendor;
        }
        public void setVendor(String vendor) {
          this.vendor = vendor;
        }
        
      }
}
  