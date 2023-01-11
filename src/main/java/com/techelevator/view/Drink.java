package com.techelevator.view;

public class Drink extends Item {


    public Drink(String slot, String name, double price, String type) {
        super(slot, name, price, type);
    }

    @Override
    void itemMessage() {
        System.out.println("Glug Glug, Yum!");
    }
}
