package com.techelevator.view;

public class Candy extends Item {


    public Candy(String slot, String name, double price, String type) {
        super(slot, name, price, type);
    }

    @Override
    void itemMessage() {
        System.out.println("Munch Munch, Yum!");
    }
}
