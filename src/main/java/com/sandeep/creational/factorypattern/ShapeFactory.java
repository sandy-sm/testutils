package com.sandeep.creational.factorypattern;

public class ShapeFactory {
  
  public static Shape getShape(String clazz){
    if(clazz == null)
      return null;
    
    if(clazz.equalsIgnoreCase("CIRCLE")){
      return new Circle();
      
   } else if(clazz.equalsIgnoreCase("RECTANGLE")){
      return new Rectangle();
      
   } else if(clazz.equalsIgnoreCase("SQUARE")){
      return new Square();
   }
   
   return null;
  }

}
