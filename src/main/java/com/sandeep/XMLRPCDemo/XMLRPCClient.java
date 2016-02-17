package com.sandeep.XMLRPCDemo;


import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;

public class XMLRPCClient {
  
  
  public static void main(String[] args){
    try { 
      XmlRpcClient server = new XmlRpcClient();
      
      Vector<Integer> params = new Vector<Integer>();
      
      params.addElement(new Integer(17));
      params.addElement(new Integer(13));

      Object result = server.execute("sample.sum", params);

      int sum = ((Integer) result).intValue();
      System.out.println("The sum is: "+ sum);
      
    } catch(Exception e) {
      System.out.println("JavaClient: "+ e);
    }
  }
}
