package com.techelevator.view;

public class Gum extends Item {
    public Gum(String slot, String name, double price, String type) {
        super(slot, name, price, type);
    }


    @Override
    void itemMessage() {
        System.out.println("Chew Chew, Yum!");
    }
}
