package com.techelevator;

public class Drinks extends Snacks{
    public Drinks(String location, String name, double price, String type) {
        super(location, name, price, type);

    }
    @Override
    public String printOut() {
        return "Glug Glug, Yum!";
    }
}
