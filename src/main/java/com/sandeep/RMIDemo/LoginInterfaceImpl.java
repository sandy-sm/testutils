/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sandeep.RMIDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandeep
 */
public class LoginInterfaceImpl extends UnicastRemoteObject implements LoginInterface{

  LoginInterfaceImpl() throws RemoteException  {
   super();
  }
  
  @Override
  public String login(String username, String password) throws RemoteException {
    String response = null;
    String path = System.getProperty("credentials.path");
    System.out.println("path: "+ path);
    
    File file = new File(path);
    boolean userFound = false;
    ArrayList users = new ArrayList();
    
    
		try {
      if(!file.isFile()) {
        throw new RuntimeException("Incorrect file specified");
      }
      
			String currentRecord;
			BufferedReader br = new BufferedReader(new FileReader(file));
      
			while ((currentRecord = br.readLine()) != null) {
        String[] credentials = currentRecord.split("/");
        String dbusername = credentials[0];
        String dbPassword = credentials[1];
        
        
        if(dbusername.equalsIgnoreCase(username)
                && dbPassword.equalsIgnoreCase(password)) {
          response = "\nWelcome "+ username + "!";
          userFound = true;
        }
        
        users.add(dbusername);
			}
      
      if(!userFound) {
        throw new RuntimeException("Incorrect username/password");
      } 
      
      if(username.equalsIgnoreCase("admin")) {
        response += "\n\nList of users registerd: \n";
        response += asReadableString((String[])users.toArray(new String[users.size()]), "\n");
      }
      
		} catch (IOException e) {
      response = "Error Occured :"+ e.getMessage();
		} catch (RuntimeException re) {
      response = re.getMessage();
    } catch (Exception e) {
      response = e.getMessage();
    }
    
    return response;
  }

  @Override
  public String register(String username, String password) throws RemoteException {
    String response = null;
    String path = System.getProperty("credentials.path");
    File file = new File(path);

		try {
      if(!file.isFile()) {
        throw new RuntimeException("Incorrect file specified");
      }
			String currentRecord;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((currentRecord = br.readLine()) != null) {
        String[] credentials = currentRecord.split("/");
        String dbusername = credentials[0];
        if(dbusername.equalsIgnoreCase(username)) {
          System.out.println("User already exists with name : "+ username);
          throw new RuntimeException("User already Exists. Try a different one");
        }
			}
      
      //Write to file new user
      FileWriter fileWriter = new FileWriter(file, true);
      try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
        bufferedWriter.write(username+"/"+password);
        bufferedWriter.newLine();
      }
      
      response = "Registration Successfull";
		} catch (IOException e) {
      System.out.println("Error Occured while reading file :"+ e.getMessage());
      response = "Error Occured while reading file :"+ e.getMessage();
		} catch (RuntimeException re) {
      response = re.getMessage();
    }
    
    return response;
  }

 
  private String asReadableString(String[] strings, String separator) {
      StringBuilder output = new StringBuilder("");
      int count = 0;
      for (String str : strings) {
          count++;
          output.append(str);
          if (count != strings.length) {
              output.append(separator);
          }
      }
      return output.toString();

  }
}
