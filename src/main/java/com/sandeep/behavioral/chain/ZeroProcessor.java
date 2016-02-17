package com.sandeep.behavioral.chain;

public class ZeroProcessor implements Chain{
  
  private Chain nextInChain;

  @Override
  public void setNext(Chain nextInChain) {
    this.nextInChain = nextInChain;
  }

  @Override
  public void process(Number request) {
    if(request.getNumber() == 0) {
      System.out.println("ZeroProcessor");
    } else {
      nextInChain.process(request);
    }
    
  }
  
   

}
