package com.techelevator.view;

public class Chip extends Item {


    public Chip(String slot, String name, double price, String type) {
        super(slot, name, price, type);
    }

    @Override
    void itemMessage() {
        System.out.println("Crunch Crunch, Yum!");
    }
}
