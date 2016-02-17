package com.sandeep.XMLRPCDemo;

import java.io.IOException;

import org.apache.xmlrpc.*;
import org.apache.xmlrpc.webserver.WebServer;

public class XMLRPCServer {
  
  public static void main(String[] args) {
    System.out.println("Attempting to start RPC Server...");
    
    WebServer server = new WebServer(80);
    
    server.acceptClient("127.0.0.1");
    
    try {
      server.start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println("Started successfully.");
    System.out.println("Accepting requests. (Halt program to stop.)");
    
  }
}
