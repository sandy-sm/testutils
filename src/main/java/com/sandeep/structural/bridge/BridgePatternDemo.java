package com.sandeep.structural.bridge;

import com.sandeep.structural.bridge.abstraction.Bike;
import com.sandeep.structural.bridge.abstraction.Car;
import com.sandeep.structural.bridge.abstraction.Vehicle;
import com.sandeep.structural.bridge.implementor.Assemble;
import com.sandeep.structural.bridge.implementor.Produce;

//Reference:  http://javapapers.com/design-patterns/bridge-design-pattern/
public class BridgePatternDemo {
  
  public static void main(String[] args) {

    Vehicle vehicle1 = new Car(new Produce(), new Assemble());
    vehicle1.manufacture();
    Vehicle vehicle2 = new Bike(new Produce(), new Assemble());
    vehicle2.manufacture();

  }
}
