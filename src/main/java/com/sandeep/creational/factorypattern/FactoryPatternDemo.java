package com.sandeep.creational.factorypattern;

public class FactoryPatternDemo {

  public static void main(String[] args) {
    Shape s = ShapeFactory.getShape("Circle");
    s.draw();
    s = ShapeFactory.getShape("rectangle");
    s.draw();
    s = ShapeFactory.getShape("square");
    s.draw();
  }
}
