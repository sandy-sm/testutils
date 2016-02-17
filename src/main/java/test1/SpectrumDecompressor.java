package test1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SpectrumDecompressor {
  
  static String FILE_ORIGINAL_LOCATION = "/home/sandeep/Documents/mimosa-docs/spectrum-decompress-2578/A5_original.bin";
  
  static String FILE_COMPRESSED_LOCATION = "/home/sandeep/Documents/mimosa-docs/spectrum-decompress-2578/SpectrumData_1449204919044.bin";
  
  static String FILE_DECOMPRESSED_LOCATION = "/home/sandeep/Documents/mimosa-docs/spectrum-decompress-2578/A5_decompressed_out.bin";
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    /*
    1. Get decompressed PDF spectrum file
     
    2. Compress data in file (A5_out.bin) 
    
    3. decompress data 
    
    4. Verify (1) and (3)
    */
    
    try {
      
      //1. Compress data in file
      //InputStream inputStream = new FileInputStream(new File(FILE_ORIGINAL_LOCATION));
      //byte[] bytes_original = IOUtils.toByteArray(inputStream);
      
      //compressAndSave(bytes_original);
      
      
      //2. decompress data
      InputStream compressedInputStream = new FileInputStream(new File(FILE_COMPRESSED_LOCATION));
      byte[] decompressedBytes = IOUtils.toByteArray(compressedInputStream);
      
      deCompressAndSave(decompressedBytes);
      
    } catch (IOException | DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    

  }
  

  /**
   * Compress A5 Spectrum data
   * 
   * Algorithm
   * 1) Get PDF element
   * 2) Decode Base 64 
   * 3) Zlib Compression
   * 4) Encode Base 64
   * 
   * @param bytes
   * @throws IOException 
   * @throws JsonProcessingException 
   * 
   */
  private static void compressAndSave(byte[] bytes) throws IOException {
    //1) Get PDF Element
    System.out.println("1. Get PDF Element");
    JsonNode spectrumNode = new ObjectMapper().readTree(bytes);
    
    String pdfContent = spectrumNode.get("PDF").asText();
    
    //2) Decode Base 64
    System.out.println("Decoding pdfContent with Base64 of size: "+ pdfContent.length());
    byte[] decoded = DatatypeConverter.parseBase64Binary(pdfContent);
    
    //3) Compress
    System.out.println("Compressing...");
    byte[] output = compressDeflate(decoded);
    
    System.out.println("Original: " + decoded.length);
    System.out.println("Compressed: " + output.length);
    System.out.println("IsCompressed: "+ (output.length < decoded.length));
    
    //4) Encode Base64 PDF element and overwrite in JSON.
    spectrumNode = ((ObjectNode)spectrumNode).put("PDF", DatatypeConverter.printBase64Binary(output).getBytes("UTF-8"));
    
    File file = new File(FILE_COMPRESSED_LOCATION);
    new ObjectMapper().writeValue(file, spectrumNode);
  }
  
  /**
   * Decompress A5 Spectrum data
   * 
   * Algorithm
   * 1) Get PDF element
   * 2) Decode Base 64 
   * 3) Zlib Decompression
   * 4) Encode Base 64
   * 
   * @param bytes
   * @throws IOException 
   * @throws DataFormatException 
   * @throws JsonProcessingException 
   * 
   */
  private static void deCompressAndSave(byte[] bytes) throws IOException, DataFormatException {
    //1) Get PDF Element
    System.out.println("1. Get PDF Element");
    JsonNode spectrumNode = new ObjectMapper().readTree(bytes);
    
    String pdfContent = spectrumNode.get("PDF").asText();
    
    //2) Decode Base 64
    System.out.println("Decoding pdfContent with Base64 of size: "+ pdfContent.length());
    byte[] decoded = DatatypeConverter.parseBase64Binary(pdfContent);
    
    //3) Decompress 
    System.out.println("Decompressing...");
    byte[] output = decompressInflate(decoded);
    
    System.out.println("Original: " + decoded.length);
    System.out.println("Decompressed: " + output.length);
    
    //4) Encode Base64 PDF element and overwrite in JSON.
    spectrumNode = ((ObjectNode)spectrumNode).put("PDF", DatatypeConverter.printBase64Binary(output));
    
    System.out.println("Spectrum data: "+ spectrumNode.toString().getBytes().length);
    File file = new File(FILE_DECOMPRESSED_LOCATION);
    new ObjectMapper().writeValue(file, spectrumNode);
  }
  
  public static byte[] compressDeflate(byte[] bytesToCompress)
  {   
    Deflater deflater = new Deflater();
    deflater.setInput(bytesToCompress);
    deflater.finish();
    
    byte[] bytesCompressed = new byte[bytesToCompress.length];

    int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);

    byte[] returnValues = new byte[numberOfBytesAfterCompression];

    System.arraycopy
    (
      bytesCompressed,
      0,
      returnValues,
      0,
      numberOfBytesAfterCompression
    );

    return returnValues;
  }

  public static byte[] decompressInflate(byte[] bytesToDecompress) throws DataFormatException, IOException
  {   
    Inflater inflater = new Inflater();
    inflater.setInput(bytesToDecompress);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    while (!inflater.finished()) {
        int count = inflater.inflate(buffer);
        outputStream.write(buffer, 0, count);
    }
    outputStream.close();
    byte[] output = outputStream.toByteArray();

    inflater.end();

    System.out.println("Original: " + bytesToDecompress.length);
    System.out.println("Uncompressed: " + output.length);
    return output;
  }

}
