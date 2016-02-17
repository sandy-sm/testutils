package com.sandeep.creational.singleton;

public class Client {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    SingletonClass singleton = SingletonClass.getInstance();
    singleton.printme("This is singleton!");
  }

}
