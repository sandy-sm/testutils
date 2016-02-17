/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sandeep.RMIDemo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author sandeep
 */
public class RmiClient {
  
  public static void main(String[] args) {
    try {
      String serverIp = System.getProperty("server.ip");
      
      if(serverIp == null) {
        serverIp = "localhost";
      }
      LoginInterface loginInterface = (LoginInterface) Naming.lookup("rmi://"+ serverIp +":5959/test");
      
      String input;
      System.out.println("Application");              
      
      do {
        System.out.println("\n\nOperations:");
        System.out.println("1. Login");
        System.out.println("2. Register as new user");
        System.out.println("exit: Exit");
        
        Scanner scan = new Scanner(System.in);
        input = scan.next();
          
        switch(input) {
          case "1" :  System.out.println("Username: ");
                      String username = scan.next();
                      System.out.println("Password: ");
                      String password = scan.next();
                      String output = loginInterface.login(username, password);
                      System.out.println(output);
                      break;
            
          case "2" :  System.out.println("Enter username");
                      String newUser = scan.next();
                      System.out.println("Enter password:");
                      String newPassword = scan.next();
                      String registerOutput = loginInterface.register(newUser, newPassword);
                      System.out.println(registerOutput);
                      break;
          default: input = "exit";
                    break;
        }
      
      } while(!input.equalsIgnoreCase("exit"));
    } catch (NotBoundException ex) {
      System.out.println("Remote Not bound");
    } catch (MalformedURLException ex) {
      System.out.println("Incorrect Server URL");
    } catch (RemoteException ex) {
      System.out.println("Error in connecting with Server. Please try again..");
    }
  }
}
