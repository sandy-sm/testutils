package com.sandeep.creational.builder;

public class Client {

  public static void main(String[] args) {
    //initiate director
    Waiter waiter = new Waiter();
    
    //Builder Type
    PizzaBuilder hawaain_pizzabuilder = new HawaainPizzaBuilder();
    PizzaBuilder spicy_pizzabuilder = new SpicyPizzaBuilder();
    
    //Builder
    waiter.setPizzaBuilder(spicy_pizzabuilder);
    waiter.constructPizza();
    
    //Get Builder result
    Pizza pizza = waiter.getPizza();
    System.out.println(pizza);
  }

}
