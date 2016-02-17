package com.sandeep.creational.singleton;

public class SingletonClass {

  private static SingletonClass singleton = null;
  
  private SingletonClass () {}
  
  public static SingletonClass getInstance() {
    if(null == singleton) {
      singleton = new SingletonClass();
    } 
    
    return singleton;
  }
  
  public void printme(String text) {
    System.out.println("Printing: "+text);
  }
  
  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
}
