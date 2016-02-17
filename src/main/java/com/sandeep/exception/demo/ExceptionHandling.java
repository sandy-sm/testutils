package com.sandeep.exception.demo;

/**
 *
 * @author sandeep
 */
public class ExceptionHandling extends ThreadGroup {

  public ExceptionHandling(String name) {
    super(name);
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("Caught: uncaugh exception: "+ e.getMessage());
  }
  
  
  
}
