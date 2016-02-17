/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sandeep.RMIDemo;

import java.rmi.Naming;


/**
 *
 * @author sandeep
 */
public class RmiServer{

  public static void main(String[] args) {
    try{
      String serverIp = System.getProperty("server.ip");
      
      if(serverIp == null) {
        serverIp = "localhost";
      }
      
      LoginInterface loginInterfaceStub = new LoginInterfaceImpl();
      Naming.rebind("rmi://"+ serverIp + ":5959/test", loginInterfaceStub);
    } catch(Exception e)  {
      System.out.println("Error Occured: "+ e.getMessage());
    }
  }
  
}
