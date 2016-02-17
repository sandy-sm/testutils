package com.sandeep.creational.builder;

public class SpicyPizzaBuilder extends PizzaBuilder {

  @Override
  public void buildDough() {
    super.pizza.setDough("pan baked");
  }

  @Override
  public void buildSauce() {
    super.pizza.setSauce("hot");
  }

  @Override
  public void buildTopping() {
    super.pizza.setToppings("pepperoni+salami");
  }
  
}
