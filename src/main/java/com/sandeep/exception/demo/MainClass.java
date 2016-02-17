/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sandeep.exception.demo;

/**
 *
 * @author sandeep
 */
public class MainClass {
  
  
  public static void main(String[] args) {

    Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandling("Test"));
    
    String s= null;
    
    System.out.println("Uncaught handler: "+ Thread.getDefaultUncaughtExceptionHandler().toString());
    System.out.println("uncaught exception handler:"+ Thread.currentThread().getUncaughtExceptionHandler().toString());
    
    s.contains("a");
  }
}
