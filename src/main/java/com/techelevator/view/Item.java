package com.techelevator.view;

public abstract class Item {
    String slot;
    String name;
    double price;
    String type;

    public Item(String slot, String name, double price, String type) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() { //overriding to string method
        return String.format( "||%2s||%-19s||$%-4.2f||%-6s||",getSlot() , getName() ,getPrice(), getType()); //used string formatter to have a cleaner menu
    }


    abstract void itemMessage();
}

