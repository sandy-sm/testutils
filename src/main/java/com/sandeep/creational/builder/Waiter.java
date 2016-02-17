package com.sandeep.creational.builder;

/*Director*/
public class Waiter {
  
  private PizzaBuilder pizzaBuilder;
  
  public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
    this.pizzaBuilder = pizzaBuilder;
  }
  
  public Pizza getPizza() {
    return pizzaBuilder.getPizza();
  }
  
  public void constructPizza() {
    pizzaBuilder.createNewPizza();
    pizzaBuilder.buildDough();
    pizzaBuilder.buildSauce();
    pizzaBuilder.buildTopping();
  }
  
  
}

