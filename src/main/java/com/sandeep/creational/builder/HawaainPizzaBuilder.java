package com.sandeep.creational.builder;

public class HawaainPizzaBuilder extends PizzaBuilder {

  @Override
  public void buildDough() {
    super.pizza.setDough("cross");
  }

  @Override
  public void buildSauce() {
    super.pizza.setSauce("mild");
  }

  @Override
  public void buildTopping() {
    super.pizza.setToppings("ham+pineaaple");
  }

}
