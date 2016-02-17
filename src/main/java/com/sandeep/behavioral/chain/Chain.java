package com.sandeep.behavioral.chain;

public interface Chain {
  
  abstract void setNext(Chain nextInChain);
  abstract void process(Number request);
  
}
