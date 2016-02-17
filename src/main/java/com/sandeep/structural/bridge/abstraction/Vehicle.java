package com.sandeep.structural.bridge.abstraction;

import com.sandeep.structural.bridge.implementor.Workshop;

public abstract class Vehicle {

  protected Workshop workShop1;
  protected Workshop workShop2;

  public Vehicle(Workshop workShop1, Workshop workShop2) {
    this.workShop1 = workShop1;
    this.workShop2 = workShop2;
  }
  
  abstract public void manufacture();
  
}
