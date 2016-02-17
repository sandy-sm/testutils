package test1;

import java.io.File;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;

public class AmazonS3ClientApp {

  public static AmazonS3 getCustomAmazonS3Connection(String accessKeyId,String secretKey)throws Exception {
    AWSCredentials amazonS3Credentials = new BasicAWSCredentials(accessKeyId,secretKey);
    AmazonS3Client s3Client = new AmazonS3Client(amazonS3Credentials);
    s3Client.setEndpoint("http://localhost:4567");
    s3Client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
    return s3Client;
  }
  
  public Boolean isBucketExists(AmazonS3 client,String bucketName)throws Exception {
    return client.doesBucketExist(bucketName);
  }
  
  public Bucket createBucket(AmazonS3Client client,String bucketName)throws Exception {
    Bucket newBucket = null;
    if(isBucketExists(client, bucketName)) {
      newBucket =  client.createBucket(bucketName);
    }
    return newBucket;
  }
  
  public PutObjectResult uploadFilesToBucket(AmazonS3 client,String bucketName,String key,File file)throws Exception {

    if(client == null) {
      throw new Exception("AmazonS3Client is not available or null...");
    }
    
    if(file == null) {
      throw new Exception("file input is null");
    }
      
    PutObjectResult result = client.putObject(bucketName,key,file);
    return result;
  }
  
  public static void main(String[] args) throws Exception {
    System.out.println("start upload ");
    
    String bucketName =  "mfd-dev";
    String keyPath = "02-01-2016";
    
    File file = new File("/home/sandeep/Documents/china_2.csv");
    System.out.println(file.length()+ " bytes of file");
    
    AmazonS3 amazonS3 = getCustomAmazonS3Connection("Fake", "Fake");
    PutObjectResult result = new AmazonS3ClientApp().uploadFilesToBucket(amazonS3, bucketName, keyPath, file);
    System.out.println("end upload : ");
    
    
    System.out.println("Start get: ");
    ObjectListing obj = amazonS3.listObjects(bucketName, keyPath);
    System.out.println("Get: "+ obj.getObjectSummaries().size());
  }

}
