package com.sandeep.structural.bridge.abstraction;

import com.sandeep.structural.bridge.implementor.Workshop;

public class Bike extends Vehicle {
  
  public Bike(Workshop workShop1, Workshop workShop2) {
    super(workShop1, workShop2);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void manufacture() {
    System.out.println("Bike");
    workShop1.work();
    workShop2.work();
  }
  
}
